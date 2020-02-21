package ma.cdgcapital.dgsi.cqrs.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.cdgcapital.dgsi.cqrs.aggregates.AccountType;
import ma.cdgcapital.dgsi.cqrs.aggregates.Status;
import ma.cdgcapital.dgsi.cqrs.events.AccountCreatedEvent;
import ma.cdgcapital.dgsi.cqrs.events.AccountUpgradedEvent;
import ma.cdgcapital.dgsi.cqrs.events.MoneyCreditedEvent;
import ma.cdgcapital.dgsi.cqrs.models.Account;
import ma.cdgcapital.dgsi.cqrs.producers.EventProducer;
import ma.cdgcapital.dgsi.cqrs.repository.AccountRepository;
import ma.cdgcapital.dgsi.cqrs.services.accounts.AccountService;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Accounts", description = "Account Query Endpoint", tags = "Account Queries")
@AllArgsConstructor
@Slf4j
public class AccountModelController {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    public List<Account> listAllAccounts() {
        return this.accountService.listAllAccounts();
    }


    @EventHandler
    public void handleMachinEvent(MoneyCreditedEvent accountCreatedEvent) {

        log.info("Handling Event outside Aggregate");

        Account account = new Account();
        account.setAccountNumber(accountCreatedEvent.id);
        account.setBalance(accountCreatedEvent.creditAmount);

        this.accountRepository.save(account);
    }
}
