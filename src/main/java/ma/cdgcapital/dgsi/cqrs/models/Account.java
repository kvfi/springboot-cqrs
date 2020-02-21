package ma.cdgcapital.dgsi.cqrs.models;

import ma.cdgcapital.dgsi.cqrs.enums.AccountType;
import ma.cdgcapital.dgsi.cqrs.enums.Currency;
import ma.cdgcapital.dgsi.cqrs.enums.Status;
import org.springframework.data.annotation.Id;


public class Account {

    @Id
    private String id;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private Currency currency;
    private Double balance;
    private Boolean isPremium;
    private AccountType accountType;
    private Status status;

    public Account(String accountNumber, Double balance, Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
