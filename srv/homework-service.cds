using de.jumajumo.homework as db from '../db/data-model';

service HomeworkService {
    entity Activity as projection on db.Activity;
    entity ActivityTask as projection on db.ActivityTask
      actions {
        action markAsDone(doneDate: Date);
      }      
    ;
    entity ActivityTaskDone as projection on db.ActivityTaskDone;
}

extend entity HomeworkService.ActivityTaskDone with actions {
  action reopen();
}

annotate HomeworkService.Activity with @odata.draft.enabled;
annotate HomeworkService.Activity with @Capabilities.Insertable: true;
