package ma.cdgcapital.dgsi.cqrs.events;

import ma.cdgcapital.dgsi.cqrs.aggregates.AccountType;

public class AccountUpgradedEvent extends BaseEvent<String> {

    public final AccountType accountType;

    public AccountUpgradedEvent(String id, AccountType accountType) {
        super(id);
        this.accountType = accountType;
    }
}
