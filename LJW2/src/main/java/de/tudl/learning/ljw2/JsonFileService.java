package de.tudl.learning.ljw2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JsonFileService {

    private final ObjectMapper objectMapper;
    private final String filePath;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public JsonFileService(String filePath) {
        validateFilePath(filePath);

        this.objectMapper = new ObjectMapper();
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    private void validateFilePath(String filePath) {
        if (filePath.isBlank()) throw new IllegalArgumentException(
                "Filepath should not be empty."
        );

        if (Files.notExists(Path.of(filePath))) throw new IllegalArgumentException(
                "Filepath is not a valid path."
        );
    }

    public void writeToFile(List<Contact> contacts) {
        try {
            objectMapper.writeValue(new File(filePath), contacts);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public List<Contact> readFromFile() {
        File file = new File(filePath);

        if (!file.exists()) return new ArrayList<>();

        try {
            return objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            backupCorruptedFile(file);
            return new ArrayList<>();
        }
    }

    private void backupCorruptedFile(File file) {
        File backupFile = new File(file.getParent(), "backup_" + file.getName());
        try {
            Files.copy(
                    file.toPath(),
                    backupFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException ignored) {
            logger.info("cannot write file to path at " + file.getAbsolutePath());
        }
    }
}
