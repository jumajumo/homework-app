using de.jumajumo.homework as db from '../db/data-model';

service HomeworkTaskService {
    entity ActivityTask as 
        select from db.ActivityTask {ID, dueDate, parent.title as activity}
        order by dueDate
    actions {
        action markAsDone(doneDate: Date);
    };
}
