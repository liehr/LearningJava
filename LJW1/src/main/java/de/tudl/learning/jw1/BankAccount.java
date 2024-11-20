package de.tudl.learning.jw1;

import java.util.UUID;

public class BankAccount
{
    private final UUID id;
    private final double balance;
    private final AccountHolder accountHolder;

    public BankAccount(UUID id, double balance, AccountHolder accountHolder)
    {
        this.id = (id != null) ? id : UUID.randomUUID();

        validateAccountHolder(accountHolder);

        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public BankAccount(BankAccount original)
    {
        this.id = original.id;
        this.balance = original.balance;
        this.accountHolder = original.accountHolder;
    }

    public BankAccount() {
        this(UUID.randomUUID(), 0.0, new AccountHolder());
    }

    public BankAccount withBalance(double newBalance)
    {
        return new BankAccount(this.id, newBalance, this.accountHolder);
    }

    public BankAccount withAccountHolder(AccountHolder newAccountHolder)
    {
        return new BankAccount(this.id, this.balance, newAccountHolder);
    }

    public UUID getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    private static void validateAccountHolder(AccountHolder accountHolder)
    {
        if (accountHolder == null)
            throw new IllegalArgumentException("Account holder cannot be null!");
    }
}
