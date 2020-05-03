package de.jumajumo.homework.odata.homeworkTaskService;

import java.util.Iterator;

import com.sap.cds.ql.cqn.AnalysisResult;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.ResolvedSegment;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jumajumo.homework.odata.actions.MarkAsDoneEventContext;
import de.jumajumo.homework.service.TaskService;

import cds.gen.homeworktaskservice.ActivityTask_;
import cds.gen.homeworktaskservice.HomeworkTaskService_;

@Component
@ServiceName(HomeworkTaskService_.CDS_NAME)
public class HomeworkTaskServiceHandler implements EventHandler {

    @Autowired
    private TaskService taskService;

    private final CqnAnalyzer analyzer;

    @Autowired
    public HomeworkTaskServiceHandler(final CdsModel model) {
        this.analyzer = CqnAnalyzer.create(model);
    }

    @On(event = "markAsDone", entity = ActivityTask_.CDS_NAME)
    public void marksAsDone(final MarkAsDoneEventContext context) {

        final AnalysisResult ana = analyzer.analyze(context.getCqn());

        final Iterator<ResolvedSegment> iterator = ana.iterator();
        while (iterator.hasNext()) {
            final ResolvedSegment segment = iterator.next();

            if (ActivityTask_.CDS_NAME.equals(segment.entity().getQualifiedName())) {

                final String taskId = segment.keys().get("ID").toString();

                taskService.markAsDone(taskId, context.getDoneDate());

                context.setCompleted();
                break;
            }
        }

        context.setCompleted();
    }

}