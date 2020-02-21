package ma.cdgcapital.dgsi.cqrs.aggregates;

import lombok.extern.slf4j.Slf4j;
import ma.cdgcapital.dgsi.cqrs.commands.CreateAccountCommand;
import ma.cdgcapital.dgsi.cqrs.commands.CreditMoneyCommand;
import ma.cdgcapital.dgsi.cqrs.commands.DebitMoneyCommand;
import ma.cdgcapital.dgsi.cqrs.events.*;
import ma.cdgcapital.dgsi.cqrs.models.Account;
import ma.cdgcapital.dgsi.cqrs.producers.EventProducer;
import ma.cdgcapital.dgsi.cqrs.repository.AccountRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

@Aggregate
@Slf4j
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

    private AccountType accountType;

    private TaskExecutor taskExecutor;

    @Autowired
    private AccountRepository accountRepository;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand, TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance,
                createAccountCommand.currency));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent) {

        log.info("Handling AccountCreatedEvent for Account {}", accountCreatedEvent.id);

        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(Status.CREATED);

        this.accountType = this.accountBalance >= 10000 ? AccountType.PREMIUM : AccountType.NORMAL;

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));

        Account account = new Account();
        account.setAccountNumber(this.id);
        account.setBalance(this.accountBalance);
        account.setAccountType(this.accountType);
        account.setStatus(Status.valueOf(this.status));

        if (this.accountType == AccountType.PREMIUM) {
            AggregateLifecycle.apply(new AccountUpgradedEvent(this.id, AccountType.PREMIUM));
            this.taskExecutor.execute(new EventProducer("vips", this.id));
            account.setPremium(true);
        }

        this.accountRepository.save(account);
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent) {
        this.status = String.valueOf(accountActivatedEvent.status);
        log.info("Handling AccountActivatedEvent for Account {}", accountActivatedEvent.id);
    }

    @CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand) {
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id, creditMoneyCommand.creditAmount,
                creditMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent) {

        if ((this.accountBalance + moneyCreditedEvent.creditAmount) >= 0) {

            log.info("Handling MoneyCreditedEvent for Account {}", moneyCreditedEvent.id);

            AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
        }

        this.accountBalance += moneyCreditedEvent.creditAmount;
    }

    @CommandHandler
    protected void on(DebitMoneyCommand debitMoneyCommand) {
        AggregateLifecycle.apply(
                new MoneyDebitedEvent(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(MoneyDebitedEvent moneyDebitedEvent) {

        if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.debitAmount) < 0) {
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
        }

        this.accountBalance -= moneyDebitedEvent.debitAmount;

    }

    @EventSourcingHandler
    protected void on(AccountHeldEvent accountHeldEvent) {
        this.status = String.valueOf(accountHeldEvent.status);
    }
}
