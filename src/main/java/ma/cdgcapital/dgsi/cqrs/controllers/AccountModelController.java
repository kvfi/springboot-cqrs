package ma.cdgcapital.dgsi.cqrs.controllers;

import io.swagger.annotations.Api;
import ma.cdgcapital.dgsi.cqrs.models.Account;
import ma.cdgcapital.dgsi.cqrs.services.accounts.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Accounts", description = "Account Query Endpoint", tags = "Account Queries")
public class AccountModelController {

    private final Logger logger = LoggerFactory.getLogger(AccountModelController.class);

    private final AccountService accountService;

    public AccountModelController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping
    public List<Account> listAllAccounts() {
        logger.info("Retrieving all accounts.");
        return this.accountService.listAllAccounts();
    }
}
