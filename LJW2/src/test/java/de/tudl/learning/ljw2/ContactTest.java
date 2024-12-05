package de.tudl.learning.ljw2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.stream.Stream;

class ContactTest {
    @Test
    void testContactInitialization() {
        UUID id = UUID.randomUUID();
        String name = "Klaus";
        String phoneNumber = "0873 376461";
        String email = "klaus.peter@test.de";
        Contact contact = new Contact(id, name, phoneNumber, email);

        assertEquals(id, contact.id(), "ID should be equal.");
        assertEquals(name, contact.name(), "Name should be equal.");
        assertEquals(phoneNumber, contact.phoneNumber(), "Phone number should be equal.");
        assertEquals(email, contact.email(), "Email should be equal.");
    }

    @Test
    void testContactCopyConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Klaus";
        String phoneNumber = "0873 376461";
        String email = "klaus.peter@test.de";
        Contact original = new Contact(id, name, phoneNumber, email);

        Contact copy = new Contact(original);

        assertNotSame(original, copy, "Original and copy should not be the same object.");
        assertEquals(original.id(), copy.id(), "ID should not have changed.");
        assertEquals(original.email(), copy.email(), "Email should not have changed.");
        assertEquals(original.name(), copy.name(), "Name should not have changed.");
        assertEquals(original.phoneNumber(), copy.phoneNumber(), "Phone number should not have changed.");
    }

    @ParameterizedTest
    @MethodSource("invalidContactArgumentsProvider")
    void testContactInitializationWithInvalidArguments(
            UUID id,
            String name,
            String phoneNumber,
            String email
    )
    {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(id, name, phoneNumber, email)
        );
    }

    private static Stream<Arguments> invalidContactArgumentsProvider()
    {
        UUID id = UUID.randomUUID();

        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        id, null, "03731042689", "test@test.de" // Name is null
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "", "03731042689", "test@test.de" // Name is empty
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "Peter Klaus", null, "test@test.de" // Phone number is null
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "Peter Klaus", "", "test@test.de" // Phone number is empty
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "Peter Klaus", "ABC123", "test@test.de" // Not a phone number
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "Peter Klaus", "+4987337646", null // Email is null
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "Peter Klaus", "+4978337646", "" // Email is empty
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        id, "Peter Klaus", "+4978337646", "test$test.de" // Not an email
                )
        );
    }

    @Test
    void testWithMethodsCreateNewInstance()
    {
        UUID id = UUID.randomUUID();
        String name = "Klaus";
        String phoneNumber = "0873 376461";
        String email = "klaus.peter@test.de";

        Contact contact = new Contact(id, name, phoneNumber, email);

        Contact updatedContact = contact.withName("New Name");

        assertNotSame(contact, updatedContact, "Contact and updated contact should not be the same object.");
        assertEquals(id, contact.id(), "Original ID should not have changed.");
        assertEquals(name, contact.name(), "Original name should not have changed.");
        assertEquals(phoneNumber, contact.phoneNumber(), "Original phone number should not have changed.");
        assertEquals(email, contact.email(), "Original Email should not have changed.");

        assertEquals(id, updatedContact.id(), "Updated ID should not have changed.");
        assertEquals("New Name", updatedContact.name(), "Updated name should have updated correctly.");
        assertEquals(phoneNumber, updatedContact.phoneNumber(), "Updated phone number should not have changed.");
        assertEquals(email, updatedContact.email(), "Updated email should not have changed.");
    }

    @Test
    void testWithPhoneCreateNewInstance()
    {
        UUID id = UUID.randomUUID();
        String name = "Klaus";
        String phoneNumber = "0873 376461";
        String email = "klaus.peter@test.de";

        Contact contact = new Contact(id, name, phoneNumber, email);

        Contact updatedContact = contact.withPhone("0173 37323261");

        assertNotSame(contact, updatedContact, "Contact and updated contact should not be the same object.");
        assertEquals(id, contact.id(), "Original ID should not have changed.");
        assertEquals(name, contact.name(), "Original name should not have changed.");
        assertEquals(phoneNumber, contact.phoneNumber(), "Original phone number should not have changed.");
        assertEquals(email, contact.email(), "Original Email should not have changed.");

        assertEquals(id, updatedContact.id(), "Updated ID should not have changed.");
        assertEquals(name, updatedContact.name(), "Updated name should not have changed.");
        assertEquals("0173 37323261", updatedContact.phoneNumber(), "Updated phone number should have updated correctly.");
        assertEquals(email, updatedContact.email(), "Updated email should not have changed.");
    }

    @Test
    void testWithEmailCreateNewInstance()
    {
        UUID id = UUID.randomUUID();
        String name = "Klaus";
        String phoneNumber = "0873 376461";
        String email = "klaus.peter@test.de";

        Contact contact = new Contact(id, name, phoneNumber, email);

        Contact updatedContact = contact.withEmail("peter.klaus@test.de");

        assertNotSame(contact, updatedContact, "Contact and updated contact should not be the same object.");
        assertEquals(id, contact.id(), "Original ID should not have changed.");
        assertEquals(name, contact.name(), "Original name should not have changed.");
        assertEquals(phoneNumber, contact.phoneNumber(), "Original phone number should not have changed.");
        assertEquals(email, contact.email(), "Original Email should not have changed.");

        assertEquals(id, updatedContact.id(), "Updated ID should not have changed.");
        assertEquals(name, updatedContact.name(), "Updated name should not have changed.");
        assertEquals(phoneNumber, updatedContact.phoneNumber(), "Updated phone number should not have changed.");
        assertEquals("peter.klaus@test.de", updatedContact.email(), "Updated email should have updated correctly.");
    }

    @Test
    void testImmutabilityWithMultipleChanges()
    {
        UUID id = UUID.randomUUID();
        String name = "Klaus";
        String phoneNumber = "0873 376461";
        String email = "klaus.peter@test.de";

        Contact contact = new Contact(id, name, phoneNumber, email);

        Contact updatedContact = contact
                .withName("Peter")
                .withPhone("0173 28842321")
                .withEmail("peter.klaus@test.de");

        assertNotSame(contact, updatedContact, "Original and updated should not be the same object.");
        assertEquals(id, contact.id(), "Original ID should not have changed.");
        assertEquals(name, contact.name(), "Original name should not have changed.");
        assertEquals(phoneNumber, contact.phoneNumber(), "Original phone number should not have changed.");
        assertEquals(email, contact.email(), "Original Email should not have changed.");

        assertEquals(id, updatedContact.id(), "Updated ID should not have changed.");
        assertEquals("Peter", updatedContact.name(), "Updated name should have updated correctly");
        assertEquals("0173 28842321", updatedContact.phoneNumber(), "Updated phone number should have updated correctly.");
        assertEquals("peter.klaus@test.de", updatedContact.email(), "Updated email should have updated correctly.");
    }
}
