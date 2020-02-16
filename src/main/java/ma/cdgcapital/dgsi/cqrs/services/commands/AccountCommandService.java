package ma.cdgcapital.dgsi.cqrs.services.commands;

import ma.cdgcapital.dgsi.cqrs.dto.commands.AccountCreateDTO;
import ma.cdgcapital.dgsi.cqrs.dto.commands.MoneyCreditDTO;
import ma.cdgcapital.dgsi.cqrs.dto.commands.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);

    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);

    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
