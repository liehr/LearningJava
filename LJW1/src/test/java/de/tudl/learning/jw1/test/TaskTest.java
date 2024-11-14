package de.tudl.learning.jw1.test;

import static org.junit.jupiter.api.Assertions.*;

import de.tudl.learning.jw1.Task;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TaskTest {

    @Test
    void testTaskInitialization() {
        UUID id = UUID.randomUUID();
        String title = "My Test Title";
        String description = "My Test Description";
        LocalDateTime dueDate = LocalDateTime.now();

        Task task = new Task(id, title, description, dueDate);

        assertEquals(id, task.getId());
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(dueDate, task.getDueDate());
    }

    @Test
    void testTaskCopyInitialization() {
        Task task = new Task();
        Task copyTask = new Task(task);

        assertEquals(task.getId(), copyTask.getId());
        assertEquals(task.getDescription(), copyTask.getDescription());
        assertEquals(task.getTitle(), copyTask.getTitle());
        assertEquals(task.getDueDate(), copyTask.getDueDate());
    }

    @ParameterizedTest
    @MethodSource("invalidTaskArgumentsProvider")
    void testTaskInitializationWithInvalidArguments(
            UUID id,
            String title,
            String description,
            LocalDateTime dueDate
    ) {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(id, title, description, dueDate)
        );
    }

    private static Stream<Arguments> invalidTaskArgumentsProvider() {
        UUID id = UUID.randomUUID();

        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        id,
                        null,
                        "My Test Description",
                        LocalDateTime.now().plusDays(1)
                ), // Null title
                org.junit.jupiter.params.provider.Arguments.of(
                        id,
                        "",
                        "My Test Description",
                        LocalDateTime.now().plusDays(1)
                ), // Empty title
                org.junit.jupiter.params.provider.Arguments.of(
                        id,
                        "My Test Title",
                        null,
                        LocalDateTime.now().plusDays(1)
                ), // Null description
                org.junit.jupiter.params.provider.Arguments.of(
                        id,
                        "My Test Title",
                        "",
                        LocalDateTime.now().plusDays(1)
                ), // Empty description
                org.junit.jupiter.params.provider.Arguments.of(
                        id,
                        "My Test Title",
                        "My Test Description",
                        LocalDateTime.now().minusDays(1)
                ), // Past due date
                org.junit.jupiter.params.provider.Arguments.of(
                        id,
                        "My Test Title",
                        "My Test Description",
                        null
                ) // null Due date
        );
    }

    @Test
    void testIdGetterAndSetter() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        UUID newId = UUID.randomUUID();

        task.setId(newId);

        assertEquals(newId, task.getId(), "Id should be updated correctly!");
    }

    @Test
    void testIdSetterNull() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        UUID oldId = task.getId();

        task.setId(null);

        assertEquals(oldId, task.getId(), "Id should not have changed!");
    }

    @Test
    void testIdSetterWithSameValue() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        UUID oldId = task.getId();

        task.setId(oldId);

        assertEquals(oldId, task.getId(), "Id should not have changed!");
    }

    @Test
    void testTitleGetterAndSetter() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        String newTitle = "My New Title";

        task.setTitle(newTitle);

        assertEquals(
                newTitle,
                task.getTitle(),
                "Title should be updated correctly!"
        );
    }

    @Test
    void testTittleSetterNull() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        String oldTitle = task.getTitle();

        task.setTitle(null);

        assertEquals(oldTitle, task.getTitle(), "Title should not have changed!");
    }

    @Test
    void testTitleSetterWithSameValue() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        String oldTitle = task.getTitle();

        task.setTitle(oldTitle);

        assertEquals(oldTitle, task.getTitle(), "Title should not have changed!");
    }

    @Test
    void testDescriptionGetterAndSetter() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        String newDescription = "My New Description!";

        task.setDescription(newDescription);

        assertEquals(
                newDescription,
                task.getDescription(),
                "Description should be updated correctly!"
        );
    }

    @Test
    void testDescriptionSetterNull() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        String oldDescription = task.getDescription();

        task.setDescription(null);

        assertEquals(
                oldDescription,
                task.getDescription(),
                "Description should not have changed!"
        );
    }

    @Test
    void testDescriptionSetterWithSameValue() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        String oldDescription = task.getDescription();

        task.setDescription(oldDescription);

        assertEquals(
                oldDescription,
                task.getDescription(),
                "Description should not have changed!"
        );
    }

    @Test
    void testDueDateGetterAndSetter() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        LocalDateTime newDueDate = LocalDateTime.now().plusDays(10);

        task.setDueDate(newDueDate);

        assertEquals(
                newDueDate,
                task.getDueDate(),
                "Due date should be updated correctly!"
        );
    }

    @Test
    void testDueDateSetterNull() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        LocalDateTime oldDueDate = task.getDueDate();

        task.setDueDate(null);

        assertEquals(
                oldDueDate,
                task.getDueDate(),
                "Due date should not have changed!"
        );
    }

    @Test
    void testDueDateSetterWithSameValue() {
        Task task = new Task(
                UUID.randomUUID(),
                "My Test Title",
                "My Test Description",
                LocalDateTime.now()
        );
        LocalDateTime oldDueDate = task.getDueDate();

        task.setDueDate(oldDueDate);

        assertEquals(
                oldDueDate,
                task.getDueDate(),
                "Due date should not have changed!"
        );
    }

    @Test
    void testTaskEquals() {
        Task taskA = new Task();
        Task taskB = new Task();

        taskB.setId(taskA.getId());
        taskB.setDescription(taskA.getDescription());
        taskB.setTitle(taskA.getTitle());
        taskB.setDueDate(taskA.getDueDate());

        assertEquals(
                taskA,
                taskB,
                "Should be true, because all properties are equal!"
        );
    }
}
