package de.tudl.learning.ljw2;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Represents a contact with a unique ID, name, phone number, and email address.
 * Provides validation for the fields and methods to create modified copies of the contact.
 */
public record Contact(UUID id, String name, String phoneNumber, String email) {
    /**
     * Constructs a new {@code Contact} instance.
     * Validates the provided name, phone number, and email.
     *
     * @param id          the unique identifier for the contact
     * @param name        the name of the contact
     * @param phoneNumber the phone number of the contact
     * @param email       the email address of the contact
     * @throws IllegalArgumentException if any of the fields are invalid
     */
    public Contact {
        validateName(name);
        validatePhoneNumber(phoneNumber);
        validateEmail(email);
    }

    /**
     * Constructs a new {@code Contact} instance by copying another contact.
     *
     * @param original the original {@code Contact} to copy
     */
    public Contact(Contact original) {
        this(original.id, original.name, original.phoneNumber(), original.email());
    }

    /**
     * Creates a new {@code Contact} instance with a modified name.
     *
     * @param newName the new name for the contact
     * @return a new {@code Contact} instance with the updated name
     */
    public Contact withName(String newName) {
        return new Contact(this.id, newName, this.phoneNumber, this.email);
    }

    /**
     * Creates a new {@code Contact} instance with a modified phone number.
     *
     * @param newPhoneNumber the new phone number for the contact
     * @return a new {@code Contact} instance with the updated phone number
     */
    public Contact withPhone(String newPhoneNumber) {
        return new Contact(this.id, this.name, newPhoneNumber, this.email);
    }

    /**
     * Creates a new {@code Contact} instance with a modified email address.
     *
     * @param newEmail the new email address for the contact
     * @return a new {@code Contact} instance with the updated email
     */
    public Contact withEmail(String newEmail) {
        return new Contact(this.id, this.name, this.phoneNumber, newEmail);
    }

    /**
     * Validates the name of the contact.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is null or empty
     */
    private static void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name should not be null!");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name should not be empty!");
        }
    }

    /**
     * Validates the german phone number of the contact.
     *
     * @param phoneNumber the german phone number to validate
     * @throws IllegalArgumentException if the phone number is null, empty, or invalid
     */
    private static void validatePhoneNumber(String phoneNumber) {
        // Matches German phone numbers starting with 0 or +49 followed by the numbers 1-9 and 1-14 digits between 0-9
        String regex = "^((\\+49)|0)[1-9]\\d{1,14}$";

        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number should not be null!");
        }

        if (phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number should not be empty!");
        }

        if (!Pattern.matches(regex, phoneNumber.replaceAll("\\s", ""))) {
            throw new IllegalArgumentException("Phone number must be valid!");
        }
    }

    /**
     * Validates the email address of the contact.
     *
     * @param email the email address to validate
     * @throws IllegalArgumentException if the email is null, empty, or invalid
     */
    private static void validateEmail(String email) {
        // Matches email addresses containing letters, digits, dots, underscores, percent signs, plus signs, and hyphens.
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (email == null) {
            throw new IllegalArgumentException("Email should not be null!");
        }

        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email should not be empty!");
        }

        if (!Pattern.matches(regex, email.trim())) {
            throw new IllegalArgumentException("Email must be valid!");
        }
    }
}
