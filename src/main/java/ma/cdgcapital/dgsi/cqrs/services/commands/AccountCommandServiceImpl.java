package ma.cdgcapital.dgsi.cqrs.services.commands;

import ma.cdgcapital.dgsi.cqrs.commands.CreateAccountCommand;
import ma.cdgcapital.dgsi.cqrs.commands.CreditMoneyCommand;
import ma.cdgcapital.dgsi.cqrs.commands.DebitMoneyCommand;
import ma.cdgcapital.dgsi.cqrs.dto.commands.AccountCreateDTO;
import ma.cdgcapital.dgsi.cqrs.dto.commands.MoneyCreditDTO;
import ma.cdgcapital.dgsi.cqrs.dto.commands.MoneyDebitDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(),
                accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(
                new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(
                new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}
