package ma.cdgcapital.dgsi.cqrs.controllers;

import io.swagger.annotations.Api;
import ma.cdgcapital.dgsi.cqrs.events.AccountActivatedEvent;
import ma.cdgcapital.dgsi.cqrs.events.AccountCreatedEvent;
import ma.cdgcapital.dgsi.cqrs.events.MoneyDebitedEvent;
import ma.cdgcapital.dgsi.cqrs.models.Account;
import ma.cdgcapital.dgsi.cqrs.services.accounts.AccountService;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Accounts", description = "Account Query Endpoint", tags = "Account Queries")
public class AccountModelController {

    private final Logger logger = LoggerFactory.getLogger(AccountModelController.class);

    @Autowired
    private AccountService accountService;


    @GetMapping
    public List<Account> listAllAccounts() {
        logger.info("Retrieving all accounts.");
        return this.accountService.listAllAccounts();
    }


}
