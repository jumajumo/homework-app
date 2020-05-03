package de.jumajumo.homework.service;

import static cds.gen.homeworkservice.HomeworkService_.ACTIVITY;
import static cds.gen.homeworkservice.HomeworkService_.ACTIVITY_TASK;
import static cds.gen.homeworkservice.HomeworkService_.ACTIVITY_TASK_DONE;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnDelete;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CdsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cds.gen.homeworkservice.Activity;
import cds.gen.homeworkservice.ActivityTask;
import cds.gen.homeworkservice.ActivityTaskDone;
import cds.gen.homeworkservice.HomeworkService_;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    @Qualifier(HomeworkService_.CDS_NAME)
    private CdsService service;

    @Autowired
    private DueDateCalculationService dueDateCalculationService;

    public void markAsDone(final String taskId, final LocalDate doneDate) {
        final CqnSelect taskSelect = Select.from(ACTIVITY_TASK).where(o -> o.ID().eq(taskId));
        final ActivityTask task = service.run(taskSelect).single(ActivityTask.class);

        final CqnSelect activitySelect = Select.from(ACTIVITY).where(o -> o.ID().eq(task.getParentId()));
        final Activity activity = service.run(activitySelect).single(Activity.class);

        moveTaskToDone(doneDate, task);
        calculateNewTask(doneDate, activity, task);
    }

    public void reopen(final String doneTaskId) {
        final CqnSelect doneTaskSelect = Select.from(ACTIVITY_TASK_DONE).where(o -> o.ID().eq(doneTaskId));
        final ActivityTaskDone doneTask = service.run(doneTaskSelect).single(ActivityTaskDone.class);

        final Map<String, Object> openTask = new HashMap<>();
        openTask.put("parent_ID", doneTask.getParentId());
        openTask.put("dueDate", doneTask.getDueDate());

        final CqnInsert insertTask = Insert.into(ACTIVITY_TASK).entry(openTask);
        service.run(insertTask);

        final CqnDelete deleteDoneTask = Delete.from(ACTIVITY_TASK_DONE)
                .where(o -> o.ID().eq(doneTask.getId()));
        service.run(deleteDoneTask);
    }

    private void calculateNewTask(final LocalDate doneDate, final Activity activity, final ActivityTask task) {

        final LocalDate calculatedDueDate = dueDateCalculationService.calculate(activity, task.getDueDate(), doneDate);

        final Map<String, Object> newTask = new HashMap<>();
        newTask.put("parent_ID", activity.getId());
        newTask.put("dueDate", calculatedDueDate);

        final CqnInsert insertNewTask = Insert.into(ACTIVITY_TASK).entry(newTask);
        service.run(insertNewTask);
    }

    private void moveTaskToDone(final LocalDate doneDate, final ActivityTask task) {
        final Map<String, Object> doneTask = new HashMap<>();
        doneTask.put("parent_ID", task.getParentId());
        doneTask.put("dueDate", task.getDueDate());
        doneTask.put("doneDate", doneDate);

        final CqnInsert insertDoneTask = Insert.into(ACTIVITY_TASK_DONE).entry(doneTask);
        service.run(insertDoneTask);

        final CqnDelete deleteDueTask = Delete.from(ACTIVITY_TASK).where(o -> o.ID().eq(task.getId()));
        service.run(deleteDueTask);
    }
}