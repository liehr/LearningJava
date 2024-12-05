package de.tudl.learning.ljw2;

import java.util.UUID;
import java.util.regex.Pattern;

public record User(UUID id, String username, String email, String name, String address)
{
    public User
    {
        validateUsername(username);
        validateEmail(email);
        validateName(name);
        validateAddress(address);
    }

    public User(User original)
    {
        this(original.id, original.username, original.email, original.name, original.address);
    }

    public User withUsername(String newUsername)
    {
        validateUsername(newUsername);

        return new User(this.id, newUsername, this.email, this.name, this.address);
    }

    public User withEmail(String newEmail)
    {
        validateEmail(newEmail);

        return new User(this.id, this.username, newEmail, this.name, this.address);
    }

    private void validateUsername(String username) {
        String regx = "^[a-zA-Z0-9]+$";

        if (username == null)
            throw new IllegalArgumentException("Username should not be null!");

        if (username.isBlank())
            throw new IllegalArgumentException("Username should not be empty!");

        if (!Pattern.matches(regx, username))
            throw new IllegalArgumentException("Username should only contain characters and numbers!");
    }

    private static void validateEmail(String email) {
        // Matches email addresses containing letters, digits, dots, underscores, percent signs, plus signs, and hyphens.
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (email == null) {
            throw new IllegalArgumentException("Email should not be null!");
        }

        if (email.isBlank()) {
            throw new IllegalArgumentException("Email should not be empty!");
        }

        if (!Pattern.matches(regex, email.trim())) {
            throw new IllegalArgumentException("Email must be valid!");
        }
    }

    private void validateName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name should not be null!");

        if (name.isBlank())
            throw new IllegalArgumentException("Name should not be empty!");
    }

    private void validateAddress(String address)
    {
        if (address == null)
            throw new IllegalArgumentException("Address should not be null!");

        if (address.isBlank())
            throw new IllegalArgumentException("Address should not be empty!");
    }

}
