package de.tudl.learning.ljw2;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class JsonFileServiceTest {

    private static JsonFileService jsonFileService;
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("test-contacts", ".json");
        jsonFileService = new JsonFileService(tempFile.getAbsolutePath());
    }

    @AfterEach
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testJsonFileServiceInitialization() {
        assertEquals(
                tempFile.getAbsolutePath(),
                jsonFileService.getFilePath(),
                "Filepath should be equal."
        );
    }

    @ParameterizedTest
    @MethodSource("invalidJsonFileServiceArgumentsProvider")
    void testJsonFileServiceInitializationWithInvalidArguments(String filePath) {
        assertThrows(IllegalArgumentException.class, () ->
                new JsonFileService(filePath)
        );
    }

    private static Stream<Arguments> invalidJsonFileServiceArgumentsProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        "" // Empty string
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        " " // Whitespace
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "tasdsadas" // Not a valid path
                )
        );
    }

    @Test
    void testWriteToFile() throws IOException {
        UUID id = UUID.randomUUID();
        String name = "Peter";
        String phoneNumber = "0173 4542312";
        String email = "klaus.peter@test.de";
        Contact contact = new Contact(id, name, phoneNumber, email);

        jsonFileService.writeToFile(List.of(contact));

        ObjectMapper objectMapper = new ObjectMapper();
        Contact[] contacts = objectMapper.readValue(tempFile, Contact[].class);

        assertEquals(1, contacts.length);
        assertEquals(name, contacts[0].name());
        assertEquals(phoneNumber, contacts[0].phoneNumber());
        assertEquals(email, contacts[0].email());
    }

    @Test
    void testReadFromFile() {
        UUID id = UUID.randomUUID();
        String name = "Peter";
        String phoneNumber = "0173 4542312";
        String email = "klaus.peter@test.de";
        Contact contact = new Contact(id, name, phoneNumber, email);

        jsonFileService.writeToFile(List.of(contact));

        List<Contact> contacts = jsonFileService.readFromFile();

        assertEquals(1, contacts.size());
        assertEquals(name, contacts.get(0).name());
        assertEquals(phoneNumber, contacts.get(0).phoneNumber());
        assertEquals(email, contacts.get(0).email());
    }

    @Test
    void testReadFromNonExistentFile() {
        tempFile.delete();

        List<Contact> contacts = jsonFileService.readFromFile();
        assertNotNull(contacts, "Contacts list should not be null");
        assertTrue(
                contacts.isEmpty(),
                "Contacts list should be empty for non-existent file"
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidJsonCases")
    void testReadFromInvalidJsonFile(
            String description,
            String invalidJsonContent
    ) throws IOException {
        Files.writeString(tempFile.toPath(), invalidJsonContent);

        List<Contact> contacts = jsonFileService.readFromFile();

        assertNotNull(contacts, "Contacts list should not be null");
        assertTrue(
                contacts.isEmpty(),
                "Contacts list should be empty for " + description
        );

        File backupFile = new File(
                tempFile.getParent(),
                "backup_" + tempFile.getName()
        );
        assertTrue(
                backupFile.exists(),
                "Backup file should be created for " + description
        );
    }

    private static Stream<Arguments> provideInvalidJsonCases() {
        return Stream.of(
                Arguments.of("corrupt JSON file", "INVALID JSON CONTENT"),
                Arguments.of("empty JSON file", ""),
                Arguments.of(
                        "partial JSON file",
                        "[{ \"id\": \"123\", \"name\": \"Peter\" ]"
                )
        );
    }

    @Test
    void testWriteAndReadLargeFile() {
        List<Contact> largeContactList = IntStream.range(0, 1000)
                .mapToObj(i ->
                        new Contact(
                                UUID.randomUUID(),
                                "Name" + i,
                                "017345" + i,
                                "email" + i + "@test.com"
                        )
                )
                .toList();

        jsonFileService.writeToFile(largeContactList);

        List<Contact> contacts = jsonFileService.readFromFile();

        assertEquals(1000, contacts.size(), "Should read back all 1000 contacts");
        assertEquals("Name0", contacts.get(0).name(), "First contact should match");
        assertEquals(
                "email999@test.com",
                contacts.get(999).email(),
                "Last contact should match"
        );
    }
}