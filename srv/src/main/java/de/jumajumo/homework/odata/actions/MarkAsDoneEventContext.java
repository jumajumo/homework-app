package de.jumajumo.homework.odata.actions;

import java.time.LocalDate;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;

@EventName("markAsDone")
public interface MarkAsDoneEventContext extends EventContext {

    CqnSelect getCqn();
    
    LocalDate getDoneDate();
    void setDoneDate(LocalDate doneDate);

    static MarkAsDoneEventContext create(final String entityName) {
        return EventContext.create(MarkAsDoneEventContext.class, entityName);
    }
}