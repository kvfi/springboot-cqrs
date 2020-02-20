package ma.cdgcapital.dgsi.cqrs.services.accounts;

import ma.cdgcapital.dgsi.cqrs.models.Account;
import ma.cdgcapital.dgsi.cqrs.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> listAllAccounts() {
        return this.accountRepository.findAll();
    }
}
