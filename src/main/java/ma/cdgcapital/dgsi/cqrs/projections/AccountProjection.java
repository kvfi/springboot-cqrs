package ma.cdgcapital.dgsi.cqrs.projections;

import ma.cdgcapital.dgsi.cqrs.controllers.AccountModelController;
import ma.cdgcapital.dgsi.cqrs.enums.Currency;
import ma.cdgcapital.dgsi.cqrs.events.AccountCreatedEvent;
import ma.cdgcapital.dgsi.cqrs.models.Account;
import ma.cdgcapital.dgsi.cqrs.producers.EventProducer;
import ma.cdgcapital.dgsi.cqrs.repository.AccountRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class AccountProjection {

    private final Logger logger = LoggerFactory.getLogger(AccountModelController.class);

    private final AccountRepository accountRepository;

    private final TaskExecutor taskExecutor;

    public AccountProjection(AccountRepository accountRepository, TaskExecutor taskExecutor) {
        this.accountRepository = accountRepository;
        this.taskExecutor = taskExecutor;
    }

    /**
     * Handles {@link AccountCreatedEvent} and persist to repository
     *
     * @param event Event to extract data from
     */
    @EventHandler
    public void handle(AccountCreatedEvent event) {
        logger.info("Handling AccountCreatedEvent for projection.");
        Account account = new Account(event.id, event.accountBalance, Currency.valueOf(event.currency));
        this.accountRepository.save(account);

        if (account.getBalance() > 20000) {
            this.taskExecutor.execute(new EventProducer("vips", account.getAccountNumber()));
        }
    }

}
