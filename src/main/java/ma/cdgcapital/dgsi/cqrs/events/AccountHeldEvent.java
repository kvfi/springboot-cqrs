package ma.cdgcapital.dgsi.cqrs.events;

import ma.cdgcapital.dgsi.cqrs.aggregates.Status;

public class AccountHeldEvent extends BaseEvent<String> {

    public final Status status;

    public AccountHeldEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
