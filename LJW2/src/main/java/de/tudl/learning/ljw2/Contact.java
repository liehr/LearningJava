package de.tudl.learning.ljw2;

import java.util.UUID;
import java.util.regex.Pattern;

public record Contact(UUID id, String name, String phoneNumber, String email) {

    public Contact {
        validateName(name);
        validatePhoneNumber(phoneNumber);
        validateEmail(email);
    }

    public Contact(Contact original) {
        this(original.id, original.name, original.phoneNumber(), original.email);
    }

    public Contact withName(String newName) {
        return new Contact(this.id, newName, this.phoneNumber, this.email);
    }

    public Contact withPhone(String newPhoneNumber) {
        return new Contact(this.id, this.name, newPhoneNumber, this.email);
    }

    public Contact withEmail(String newEmail)
    {
        return new Contact(this.id, this.name, this.phoneNumber, newEmail);
    }

    private static void validateName(String name)
    {
        if (name == null)
            throw new IllegalArgumentException("Name should not be null!");

        if (name.isEmpty())
            throw new IllegalArgumentException("Name should not be empty!");
    }

    private static void validatePhoneNumber(String phoneNumber) {

        // Matches german phone numbers starting with 0 or +49 followed by the numbers 1-9 and 1-14 numbers between 0-9
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

    private static void validateEmail(String email)
    {
        // Matches the email, which can include letters, digits, dots, underscores, percent signs, plus signs, and hyphens.
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (email == null)
            throw new IllegalArgumentException("Email should not be null!");

        if (email.isEmpty())
            throw new IllegalArgumentException("Email should not be empty!");

        if (!Pattern.matches(regex, email.trim()))
            throw new IllegalArgumentException("Email must be valid!");
    }
}
