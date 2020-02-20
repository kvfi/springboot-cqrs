package ma.cdgcapital.dgsi.cqrs.controllers;

import io.swagger.annotations.Api;
import ma.cdgcapital.dgsi.cqrs.models.Account;
import ma.cdgcapital.dgsi.cqrs.services.accounts.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Accounts", description = "Account Query Endpoint", tags = "Account Queries")
public class AccountModelController {

    private final AccountService accountService;

    public AccountModelController(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<Account> listAllAccounts() {
        return this.accountService.listAllAccounts();
    }
}
