package de.tudl.learning.jw1.test;

import static org.junit.jupiter.api.Assertions.*;

import de.tudl.learning.jw1.AccountHolder;
import de.tudl.learning.jw1.BankAccount;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BankAccountTest {

    @Test
    void testBankAccountInitialization() {
        UUID id = UUID.randomUUID();
        double balance = 10.0;
        AccountHolder accountHolder = new AccountHolder(
                id,
                "John",
                "Doe",
                LocalDate.now().minusYears(18)
        );

        BankAccount bankAccount = new BankAccount(id, balance, accountHolder);

        assertEquals(id, bankAccount.getId(), "ID should be equal.");
        assertEquals(balance, bankAccount.getBalance(), "Balance should be equal.");
        assertEquals(
                accountHolder,
                bankAccount.getAccountHolder(),
                "Account holder should be equal."
        );
    }

    @Test
    void testBankAccountCopyConstructor() {
        UUID id = UUID.randomUUID();
        double balance = 10.0;
        AccountHolder accountHolder = new AccountHolder();

        BankAccount original = new BankAccount(id, balance, accountHolder);
        BankAccount copy = new BankAccount(original);

        assertNotSame(original, copy, "Copy should be a different object.");
        assertEquals(original.getId(), copy.getId(), "ID should be equal.");
        assertEquals(
                original.getBalance(),
                copy.getBalance(),
                "Balance should be equal."
        );
        assertEquals(
                original.getAccountHolder(),
                copy.getAccountHolder(),
                "Account holders should be equal."
        );
    }

    @Test
    void testAccountHolderNull() {
        UUID id = UUID.randomUUID();
        double balance = 42.69;

        assertThrows(IllegalArgumentException.class, () ->
                new BankAccount(id, balance, null)
        );
    }

    @Test
    void testWithMethodsCreatesNewInstances() {
        UUID id = UUID.randomUUID();
        double balance = 42.69;
        AccountHolder accountHolder = new AccountHolder();

        BankAccount bankAccount = new BankAccount(id, balance, accountHolder);

        BankAccount updatedBankAccount = bankAccount.withBalance(10.11);

        assertEquals(
                id,
                bankAccount.getId(),
                "Original id should remain unchanged."
        );
        assertEquals(
                balance,
                bankAccount.getBalance(),
                "Original balance should remain unchanged."
        );
        assertEquals(
                accountHolder,
                bankAccount.getAccountHolder(),
                "Original account holder should remain unchanged."
        );

        assertEquals(
                id,
                updatedBankAccount.getId(),
                "Updated id should remain the same."
        );
        assertEquals(
                10.11,
                updatedBankAccount.getBalance(),
                "Updated balance should reflect the change."
        );
        assertEquals(
                accountHolder,
                updatedBankAccount.getAccountHolder(),
                "Updated account holder should remain the same."
        );
    }

    @Test
    void testWithBalanceCreatesNewInstance() {
        UUID id = UUID.randomUUID();
        double balance = 42.69;
        AccountHolder accountHolder = new AccountHolder();

        BankAccount bankAccount = new BankAccount(id, balance, accountHolder);

        BankAccount updatedBankAccount = bankAccount.withBalance(10.11);

        assertEquals(
                balance,
                bankAccount.getBalance(),
                "Original balance should remain unchanged."
        );
        assertEquals(
                10.11,
                updatedBankAccount.getBalance(),
                "Updated balance should reflect the change."
        );
    }

    @Test
    void testWithAccountHolderCreatesNewInstance() {
        UUID id = UUID.randomUUID();
        double balance = 31.23;

        AccountHolder accountHolder = new AccountHolder();

        BankAccount original = new BankAccount(id, balance, accountHolder);

        AccountHolder newAccountHolder = new AccountHolder(
                UUID.randomUUID(),
                "Lisa",
                "Meier",
                LocalDate.now().minusYears(20)
        );
        BankAccount updated = original.withAccountHolder(newAccountHolder);

        assertEquals(
                accountHolder,
                original.getAccountHolder(),
                "Original account holder should remain unchanged."
        );
        assertEquals(
                newAccountHolder,
                updated.getAccountHolder(),
                "Updated account holder should be set correctly."
        );
    }

    @Test
    void testImmutabilityWithMultipleChanges() {
        UUID id = UUID.randomUUID();
        double balance = 1421.42;
        AccountHolder accountHolder = new AccountHolder();

        BankAccount original = new BankAccount(id, balance, accountHolder);

        AccountHolder newAccountHolder = new AccountHolder(
                UUID.randomUUID(),
                "Lisa",
                "Meier",
                LocalDate.now().minusYears(20)
        );

        BankAccount updated = original
                .withBalance(21.22)
                .withAccountHolder(newAccountHolder);

        assertEquals(
                balance,
                original.getBalance(),
                "Original balance should not be modified."
        );

        assertEquals(
                accountHolder,
                original.getAccountHolder(),
                "Original account holder should not be modified."
        );

        assertEquals(id, original.getId(), "Original id should not be modified.");

        assertEquals(21.22, updated.getBalance(), "Updated balance should match.");

        assertEquals(
                newAccountHolder,
                updated.getAccountHolder(),
                "Updated account holder should match."
        );

        assertEquals(id, updated.getId(), "ID should remain the same.");
    }
}
