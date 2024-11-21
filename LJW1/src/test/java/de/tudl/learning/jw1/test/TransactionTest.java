package de.tudl.learning.jw1.test;

import de.tudl.learning.jw1.BankAccount;
import de.tudl.learning.jw1.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.stream.Stream;

class TransactionTest
{
    @Test
    void testTransactionInitialization()
    {
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();
        double amount = 210.0;

        Transaction newTransaction = new Transaction(sender, receiver, amount);

        assertEquals(sender, newTransaction.getSender(), "Sender should be equal.");
        assertEquals(receiver, newTransaction.getReceiver(), "Receiver should be equal.");
        assertEquals(amount, newTransaction.getAmount(), "Amount should be equal.");
    }

    @Test
    void testTransactionCopyConstructor()
    {
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();
        double amount = 210.0;

        Transaction original = new Transaction(sender, receiver, amount);
        Transaction copy = new Transaction(original);

        assertNotSame(original, copy, "Copy should be a different object.");
        assertEquals(original.getId(), copy.getId(), "ID should be equal.");
        assertEquals(original.getSender(), copy.getSender(), "Sender should be equal.");
        assertEquals(original.getReceiver(), copy.getReceiver(), "Receiver should be equal.");
        assertEquals(original.getAmount(), copy.getAmount(), "Amount should be equal.");
    }

    @ParameterizedTest
    @MethodSource("invalidTransactionArgumentsProvider")
    void testTransactionInitializationWithInvalidArguments(
            BankAccount sender,
            BankAccount receiver,
            double amount
    )
    {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction(sender, receiver, amount)
        );
    }

    private static Stream<Arguments> invalidTransactionArgumentsProvider()
    {
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();

        return Stream.of(
                Arguments.of(null, receiver, 200.0),
                Arguments.of(sender, null, 200.0),
                Arguments.of(sender, receiver, 0.0),
                Arguments.of(sender, receiver, -100.2)
        );
    }
}
