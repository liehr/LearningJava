package de.tudl.learning.ljw2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserInitialization() {
        UUID id = UUID.randomUUID();
        String username = "username";
        String email = "myemail@test.de";
        String name = "Peter Klaus";
        String address = "Musterweg 12";

        User user = new User(id, username, email, name, address);

        assertEquals(id, user.id(), "Id should be equal.");
        assertEquals(username, user.username(), "Username should be equal.");
        assertEquals(email, user.email(), "Email should be equal.");
        assertEquals(name, user.name(), "Name should be equal.");
        assertEquals(address, user.address(), "Address should be equal.");
    }

    @Test
    void testUserCopyConstructor() {
        UUID id = UUID.randomUUID();
        String username = "username";
        String email = "myemail@test.de";
        String name = "Peter Klaus";
        String address = "Musterweg 12";

        User original = new User(id, username, email, name, address);

        User copy = new User(original);

        assertNotSame(original, copy, "Copy should be a new user object.");

        assertEquals(original.id(), copy.id(), "Original Id should be equal to copy Id.");
        assertEquals(original.username(), copy.username(), "Original username should be equal to copy username.");
        assertEquals(original.email(), copy.email(), "Original email should be equal to copy email.");
        assertEquals(original.name(), copy.name(), "Original name should be equal to copy name.");
        assertEquals(original.address(), copy.address(), "Original address should be equal copy adress.");
    }

    @ParameterizedTest
    @MethodSource("invalidUserArgumentsProvider")
    void testUserInitializationWithInvalidArguments(
            UUID id,
            String username,
            String email,
            String name,
            String address
    ) {
        assertThrows(IllegalArgumentException.class, () ->
                new User(id, username, email, name, address)
        );
    }

    public static Stream<Arguments> invalidUserArgumentsProvider() {
        UUID id = UUID.randomUUID();

        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        id, null, "test@test.de", "Klaus Peter", "Musterweg 14" // Username is null
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "", "test@test.de", "Klaus Peter", "Musterweg 14" // Username is empty
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, " ", "test@test.de", "Klaus Peter", "Musterweg 14" // Username is whitespace
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "my_user*name!", "test@test.de", "Klaus Peter", "Musterweg 14" // Username contains special character
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "test username", "test@test.de", "Klaus Peter", "Musterweg 14" // Username contains a whitespace
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", null, "Klaus Peter", "Musterweg 14" // Email is null
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "", "Klaus Peter", "Musterweg 14" // Email is empty
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", " ", "Klaus Peter", "Musterweg 14" // Email is whitespace
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test@test_de", "Klaus Peter", "Musterweg 14" // Email format is wrong
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test @test.de", "Klaus Peter", "Musterweg 14" // Email contains white space
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test@test.de", null, "Musterweg 14" // Name is null
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test@test.de", "", "Musterweg 14" // Name is empty
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test@test.de", " ", "Musterweg 14" // Name is whitespace
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test@test.de", "Klaus Peter", null // Address is null
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test@test.de", "Klaus Peter", "" // Address is empty
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "testname123", "test@test.de", "Klaus Peter", " " // Address is whitespace
                )
        );
    }

    @Test
    void testUserUpdateWithUsername()
    {
        UUID id = UUID.randomUUID();
        String username = "username";
        String email = "myemail@test.de";
        String name = "Peter Klaus";
        String address = "Musterweg 12";

        User original = new User(id, username, email, name, address);

        User updated = original.withUsername("testname");

        assertNotSame(original, updated, "Updated should be different object.");

        assertEquals(id, original.id(), "Original id should not have changed.");
        assertEquals(username, original.username(), "Original username should not have changed.");
        assertEquals(email, original.email(), "Original email should not have changed.");
        assertEquals(name, original.name(), "Original name should not have changed.");
        assertEquals(address, original.address(), "Original address should not have changed.");

        assertEquals(original.id(), updated.id(), "Updated id should be original id.");
        assertEquals("testname", updated.username(), "Updated username should have changed correctly.");
        assertEquals(original.email(), updated.email(), "Updated email should be original email.");
        assertEquals(original.name(), updated.name(), "Updated name should be original name.");
        assertEquals(original.address(), updated.address(), "Updated address should be original address.");
    }

    @Test
    void testUserUpdateWithEmail()
    {
        UUID id = UUID.randomUUID();
        String username = "username";
        String email = "myemail@test.de";
        String name = "Peter Klaus";
        String address = "Musterweg 12";

        User original = new User(id, username, email, name, address);

        User updated = original.withEmail("test@test.de");

        assertNotSame(original, updated, "Updated should be different object.");

        assertEquals(id, original.id(), "Original id should not have changed.");
        assertEquals(username, original.username(), "Original username should not have changed.");
        assertEquals(email, original.email(), "Original email should not have changed.");
        assertEquals(name, original.name(), "Original name should not have changed.");
        assertEquals(address, original.address(), "Original address should not have changed.");

        assertEquals(original.id(), updated.id(), "Updated id should be original id.");
        assertEquals(original.username(), updated.username(), "Updated username should be original username.");
        assertEquals("test@test.de", updated.email(), "Updated email should have changed correctly.");
        assertEquals(original.name(), updated.name(), "Updated name should be original name.");
        assertEquals(original.address(), updated.address(), "Updated address should be original address.");
    }
}
