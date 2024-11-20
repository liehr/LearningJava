package de.tudl.learning.jw1.test;

import static org.junit.jupiter.api.Assertions.*;

import de.tudl.learning.jw1.AccountHolder;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AccountHolderTest {

    @Test
    void testAccountHolderInitialization() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDate birthday = LocalDate.parse("1994-12-01");

        AccountHolder holder = new AccountHolder(id, name, surname, birthday);

        assertEquals(id, holder.getId(), "ID should be equal.");
        assertEquals(name, holder.getName(), "Name should be equal.");
        assertEquals(surname, holder.getSurname(), "Surname should be equal.");
        assertEquals(birthday, holder.getBirthday(), "Birthday should be equal.");
    }

    @Test
    void testAccountHolderCopyConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Alice";
        String surname = "Wonderland";
        LocalDate birthday = LocalDate.parse("1992-05-15");

        AccountHolder original = new AccountHolder(id, name, surname, birthday);
        AccountHolder copy = new AccountHolder(original);

        assertNotSame(original, copy, "Copy should be a different object.");
        assertEquals(original.getId(), copy.getId(), "ID should be equal.");
        assertEquals(original.getName(), copy.getName(), "Name should be equal.");
        assertEquals(
                original.getSurname(),
                copy.getSurname(),
                "Surname should be equal."
        );
        assertEquals(
                original.getBirthday(),
                copy.getBirthday(),
                "Birthday should be equal."
        );
    }

    @ParameterizedTest
    @MethodSource("invalidTaskArgumentsProvider")
    void testTaskInitializationWithInvalidArguments(
            UUID id,
            String name,
            String surname,
            LocalDate birthday
    ) {
        assertThrows(IllegalArgumentException.class, () ->
                new AccountHolder(id, name, surname, birthday)
        );
    }

    private static Stream<Arguments> invalidTaskArgumentsProvider() {
        UUID id = UUID.randomUUID();

        return Stream.of(
                Arguments.of(id, null, "My Test Surname", LocalDate.now().minusYears(18)),
                Arguments.of(id, "", "My Test Surname", LocalDate.now().minusYears(18)),
                Arguments.of(id, "My Test Name", null, LocalDate.now().minusYears(18)),
                Arguments.of(id, "My Test Name", "", LocalDate.now().minusYears(18)),
                Arguments.of(id, "My Test Name", "My Test Surname", null)
        );
    }

    @Test
    void testImmutability() {
        UUID id = UUID.randomUUID();
        String name = "Immutable";
        String surname = "Test";
        LocalDate birthday = LocalDate.parse("2000-01-01");

        AccountHolder holder = new AccountHolder(id, name, surname, birthday);

        assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException();
        });

        assertEquals(id, holder.getId());
        assertEquals(name, holder.getName());
        assertEquals(surname, holder.getSurname());
        assertEquals(birthday, holder.getBirthday());
    }

    @Test
    void testWithBirthdayCreatesNewInstance() {
        UUID id = UUID.randomUUID();
        String name = "Test";
        String surname = "User";
        LocalDate birthday = LocalDate.parse("2000-01-01");

        AccountHolder original = new AccountHolder(id, name, surname, birthday);

        LocalDate newBirthday = LocalDate.parse("1990-01-01");
        AccountHolder updated = new AccountHolder(id, name, surname, newBirthday);

        assertEquals(
                birthday,
                original.getBirthday(),
                "Original birthday should remain unchanged."
        );

        assertEquals(
                newBirthday,
                updated.getBirthday(),
                "Updated birthday should be set correctly."
        );
    }

    @Test
    void testWithNameCreatesNewInstance() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDate birthday = LocalDate.of(1990, 1, 1);

        AccountHolder original = new AccountHolder(id, name, surname, birthday);

        String newName = "Jonathan";
        AccountHolder updated = original.withName(newName);

        assertEquals(
                name,
                original.getName(),
                "Original name should not be modified."
        );
        assertEquals(
                surname,
                original.getSurname(),
                "Original surname should remain the same."
        );
        assertEquals(
                birthday,
                original.getBirthday(),
                "Original birthday should remain the same."
        );
        assertEquals(id, original.getId(), "Original ID should remain the same.");

        assertEquals(
                newName,
                updated.getName(),
                "Updated name should match the new name."
        );
        assertEquals(
                surname,
                updated.getSurname(),
                "Updated surname should remain the same."
        );
        assertEquals(
                birthday,
                updated.getBirthday(),
                "Updated birthday should remain the same."
        );
        assertEquals(id, updated.getId(), "Updated ID should remain the same.");

        assertNotSame(
                original,
                updated,
                "Updated object should be a new instance."
        );
    }

    @Test
    void testWithSurnameCreatesNewInstance() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDate birthday = LocalDate.of(1990, 1, 1);

        AccountHolder original = new AccountHolder(id, name, surname, birthday);

        String newSurname = "Smith";
        AccountHolder updated = original.withSurname(newSurname);

        assertEquals(
                name,
                original.getName(),
                "Original name should remain the same."
        );
        assertEquals(
                surname,
                original.getSurname(),
                "Original surname should not be modified."
        );
        assertEquals(
                birthday,
                original.getBirthday(),
                "Original birthday should remain the same."
        );
        assertEquals(id, original.getId(), "Original ID should remain the same.");

        assertEquals(
                name,
                updated.getName(),
                "Updated name should remain the same."
        );
        assertEquals(
                newSurname,
                updated.getSurname(),
                "Updated surname should match the new surname."
        );
        assertEquals(
                birthday,
                updated.getBirthday(),
                "Updated birthday should remain the same."
        );
        assertEquals(id, updated.getId(), "Updated ID should remain the same.");

        assertNotSame(
                original,
                updated,
                "Updated object should be a new instance."
        );
    }

    @Test
    void testImmutabilityWithMultipleChanges() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDate birthday = LocalDate.of(1990, 1, 1);

        AccountHolder original = new AccountHolder(id, name, surname, birthday);

        AccountHolder updated = original
                .withName("Jonathan")
                .withSurname("Smith")
                .withBirthday(LocalDate.of(1985, 5, 15));

        assertEquals(
                name,
                original.getName(),
                "Original name should not be modified."
        );
        assertEquals(
                surname,
                original.getSurname(),
                "Original surname should not be modified."
        );
        assertEquals(
                birthday,
                original.getBirthday(),
                "Original birthday should not be modified."
        );
        assertEquals(id, original.getId(), "Original ID should not be modified.");

        assertEquals("Jonathan", updated.getName(), "Updated name should match.");
        assertEquals(
                "Smith",
                updated.getSurname(),
                "Updated surname should match."
        );
        assertEquals(
                LocalDate.of(1985, 5, 15),
                updated.getBirthday(),
                "Updated birthday should match."
        );
        assertEquals(id, updated.getId(), "ID should remain the same.");
    }

    @Test
    void testWithNameNullThrowsException() {
        AccountHolder holder = new AccountHolder(
                UUID.randomUUID(),
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> holder.withName(null),
                "Name should not be null."
        );
    }
}
