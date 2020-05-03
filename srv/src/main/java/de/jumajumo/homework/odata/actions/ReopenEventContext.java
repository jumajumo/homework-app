package de.jumajumo.homework.odata.actions;

import java.util.Map;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;

@EventName("reopen")
public interface ReopenEventContext extends EventContext {

    CqnSelect getCqn();
	Iterable<Map<String, Object>> getCqnValueSets();
	void setCqnValueSets(Iterable<Map<String, Object>> valueSets);

    static ReopenEventContext create(final String entityName) {
        return EventContext.create(ReopenEventContext.class, entityName);
    }
}