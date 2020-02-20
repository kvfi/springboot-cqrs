package ma.cdgcapital.dgsi.cqrs.services.commands;

import ma.cdgcapital.dgsi.cqrs.dto.commands.AccountCreateDTO;
import ma.cdgcapital.dgsi.cqrs.dto.commands.MoneyCreditDTO;
import ma.cdgcapital.dgsi.cqrs.dto.commands.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {

    CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);

    CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);

    CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
