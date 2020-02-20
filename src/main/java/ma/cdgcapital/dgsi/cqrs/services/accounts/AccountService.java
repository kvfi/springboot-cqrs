package ma.cdgcapital.dgsi.cqrs.services.accounts;

import ma.cdgcapital.dgsi.cqrs.models.Account;

import java.util.List;

public interface AccountService {

    List<Account> listAllAccounts();
}
