package de.tudl.learning.jw1;

import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final BankAccount sender;
    private final BankAccount receiver;
    private final double amount;

    public Transaction(BankAccount sender, BankAccount receiver, double amount)
    {
        this.id = UUID.randomUUID();

        validateBankAccount(sender, "Sender");
        validateBankAccount(receiver, "Receiver");
        validateAmount(amount);

        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Transaction(Transaction original)
    {
        this.id = original.id;
        this.sender = original.sender;
        this.receiver = original.receiver;
        this.amount = original.amount;
    }

    public UUID getId() {
        return id;
    }

    public BankAccount getSender() {
        return sender;
    }

    public BankAccount getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    private void validateBankAccount(BankAccount field, String fieldName) {
        if (field == null)
            throw new IllegalArgumentException(fieldName + "should not be null!");
    }

    private void validateAmount(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount should not be negative!");

        if (amount == 0)
            throw new IllegalArgumentException("Amount should not be 0");
    }
}
