package ma.cdgcapital.dgsi.cqrs.events;

import ma.cdgcapital.dgsi.cqrs.enums.Status;

public class AccountActivatedEvent extends BaseEvent<String> {

    public final Status status;

    public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
