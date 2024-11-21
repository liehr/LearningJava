package de.tudl.learning.jw1;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents an account holder with immutable fields for unique identifier (UUID),
 * name, surname, and birthday. The class ensures immutability by not exposing
 * setter methods and providing "with" methods to create updated instances with
 * modified values.
 * <p>
 * This class is designed to follow best practices for immutability, ensuring
 * thread-safety and predictable behavior in multithreaded applications.
 * </p>
 */
public class AccountHolder {

    private final UUID id;
    private final String name;
    private final String surname;
    private final LocalDate birthday;

    /**
     * Constructs an AccountHolder instance with the specified attributes.
     *
     * @param id       the unique identifier for the account holder. If null, a new UUID will be generated.
     * @param name     the name of the account holder. Cannot be null or empty.
     * @param surname  the surname of the account holder. Cannot be null or empty.
     * @param birthday the date of birth of the account holder. Cannot be null, in the future, or today's date.
     * @throws IllegalArgumentException if name, surname, or birthday are invalid.
     */
    public AccountHolder(
            UUID id,
            String name,
            String surname,
            LocalDate birthday
    ) {
        this.id = (id != null) ? id : UUID.randomUUID();
        validateString(name, "Name");
        validateString(surname, "Surname");
        validateBirthday(birthday);

        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    /**
     * Constructs a default AccountHolder instance with pre-defined values:
     * - A randomly generated UUID.
     * - Default name: "Name".
     * - Default surname: "Surname".
     * - Default birthday: 18 years before the current date.
     */
    public AccountHolder() {
        this(UUID.randomUUID(), "Name", "Surname", LocalDate.now().minusYears(18));
    }

    /**
     * Creates a new AccountHolder instance with the specified name, retaining other attributes.
     *
     * @param newName the new name for the account holder. Cannot be null or empty.
     * @return a new AccountHolder instance with the updated name.
     * @throws IllegalArgumentException if the new name is null or empty.
     */
    public AccountHolder withName(String newName) {
        validateString(newName, "Name");
        return new AccountHolder(this.id, newName, this.surname, this.birthday);
    }

    /**
     * Creates a new AccountHolder instance with the specified surname, retaining other attributes.
     *
     * @param newSurname the new surname for the account holder. Cannot be null or empty.
     * @return a new AccountHolder instance with the updated surname.
     * @throws IllegalArgumentException if the new surname is null or empty.
     */
    public AccountHolder withSurname(String newSurname) {
        validateString(newSurname, "Surname");
        return new AccountHolder(this.id, this.name, newSurname, this.birthday);
    }

    /**
     * Creates a new AccountHolder instance with the specified birthday, retaining other attributes.
     *
     * @param newBirthday the new date of birth for the account holder.
     *                    Cannot be null, in the future, or today's date.
     * @return a new AccountHolder instance with the updated birthday.
     * @throws IllegalArgumentException if the new birthday is invalid.
     */
    public AccountHolder withBirthday(LocalDate newBirthday) {
        validateBirthday(newBirthday);
        return new AccountHolder(this.id, this.name, this.surname, newBirthday);
    }

    /**
     * Creates a new AccountHolder instance by copying the attributes of the provided instance.
     *
     * @param original the AccountHolder instance to copy.
     */
    public AccountHolder(AccountHolder original) {
        this.id = original.id;
        this.name = original.name;
        this.surname = original.surname;
        this.birthday = original.birthday;
    }

    /**
     * Returns the unique identifier (UUID) of the account holder.
     *
     * @return the UUID of the account holder.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the name of the account holder.
     *
     * @return the name of the account holder.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the account holder.
     *
     * @return the surname of the account holder.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the date of birth of the account holder.
     *
     * @return the date of birth of the account holder.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Validates that a string is neither null nor empty.
     *
     * @param field     the string to validate.
     * @param fieldName the name of the field being validated, used in error messages.
     * @throws IllegalArgumentException if the string is null or empty.
     */
    private static void validateString(String field, String fieldName) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException(
                    fieldName + " should not be null or empty."
            );
        }
    }

    /**
     * Validates that the provided birthday is valid.
     * The birthday cannot be null, in the future, or today's date.
     *
     * @param birthday the birthday to validate.
     * @throws IllegalArgumentException if the birthday is invalid.
     */
    private static void validateBirthday(LocalDate birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException("Birthday should not be null!");
        }
        if (birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birthday cannot be in the future!");
        }
        if (birthday.equals(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Birthday cannot be the current date!"
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountHolder that = (AccountHolder) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday);
    }
}
