package de.tudl.learning.jw1.test;

import static org.junit.jupiter.api.Assertions.*;

import de.tudl.learning.jw1.Task;
import de.tudl.learning.jw1.Todo;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testTodoInitialization() {
        List<Task> tasks = new ArrayList<>();

        Todo todo = new Todo(tasks);

        assertEquals(tasks, todo.getTasks());
    }

    @Test
    void testTodoWithTasksNull() {
        assertThrows(IllegalArgumentException.class, () -> new Todo(null));
    }

    @Test
    void testTasksGetterAndSetter() {
        Todo todo = new Todo();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());

        todo.setTasks(tasks);

        assertEquals(tasks, todo.getTasks(), "Tasks should be updated correctly!");
    }

    @Test
    void testTasksSetterEmptyList() {
        Todo todo = new Todo();
        List<Task> tasks = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> todo.setTasks(tasks));
    }

    @Test
    void testTaskSetterNull() {
        Todo todo = new Todo();
        List<Task> oldTasks = todo.getTasks();

        todo.setTasks(null);

        assertEquals(oldTasks, todo.getTasks(), "Tasks should not have changed!");
    }

    @Test
    void testTaskSetterWithSameValue() {
        // When setting a new Task List at least one element must be present!
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Todo todo = new Todo(tasks);

        List<Task> oldTask = todo.getTasks();

        todo.setTasks(oldTask);
        assertEquals(oldTask, todo.getTasks(), "Tasks should not have changed!");
    }

    @Test
    void testGetTasksReturnsCopy() {
        Todo todo = new Todo();
        Task task = new Task();
        todo.addTask(task);

        List<Task> tasksCopy = todo.getTasks();

        tasksCopy.clear();

        assertEquals(
                1,
                todo.getTasks().size(),
                "Clearing the returned list should not affect the internal tasks list"
        );
        assertEquals(
                task,
                todo.getTasks().get(0),
                "The original task should still be present in the internal tasks list"
        );
    }

    @Test
    void testAddTask() {
        Todo todo = new Todo();
        Task newTask = new Task();

        todo.addTask(newTask);

        List<Task> tasks = todo.getTasks();

        assertEquals(
                newTask,
                tasks.get(tasks.size() - 1),
                "Tasks should be added!"
        );
    }

    @Test
    void testAddTaskNull() {
        Todo todo = new Todo();

        assertThrows(
                IllegalArgumentException.class,
                () -> todo.addTask(null),
                "Task should not be null!"
        );
    }

    @Test
    void testAddTaskValuePresent() {
        // When setting a new Task List at least one element must be present!
        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        tasks.add(task);
        Todo todo = new Todo(tasks);

        int listSize = todo.getTasks().size();

        todo.addTask(task);
        assertEquals(
                todo.getTasks().size(),
                listSize,
                "Do not add same task twice!"
        );
    }

    @Test
    void testGetTask() {
        Todo todo = new Todo();
        Task task = new Task();
        todo.addTask(task);

        Task getTask = todo.getTask(task.getId());

        assertEquals(getTask, task, "Should be the added task!");
    }

    @Test
    void testGetTaskNull() {
        Todo todo = new Todo();
        todo.addTask(new Task());

        assertThrows(
                IllegalArgumentException.class,
                () -> todo.getTask(null),
                "Searching Id should not be null!"
        );
    }

    @Test
    void testGetTaskWithWrongId() {
        Todo todo = new Todo();
        todo.addTask(new Task());
        UUID uuid = UUID.randomUUID();

        assertThrows(
                NoSuchElementException.class,
                () -> todo.getTask(uuid),
                "No task should be found with a random UUID"
        );
    }

    @Test
    void testUpdateTask() {
        Todo todo = new Todo();
        Task task = new Task();
        todo.addTask(task);

        UUID id = task.getId();

        Task toUpdate = todo.getTask(id);

        toUpdate.setTitle("My Awesome New Title");

        todo.updateTask(toUpdate);

        assertEquals(
                todo.getTask(id),
                toUpdate,
                "Task should be updated correctly!"
        );
    }

    @Test
    void testUpdateTaskNull() {
        Todo todo = new Todo();
        todo.addTask(new Task());

        assertThrows(
                IllegalArgumentException.class,
                () -> todo.updateTask(null),
                "UpdatedTask should not be null"
        );
    }

    @Test
    void testUpdateValuePresent() {
        Todo todo = new Todo();
        Task task = new Task();
        todo.addTask(task);

        todo.updateTask(task);

        assertEquals(todo.getTask(task.getId()), task, "Task should be the same!");
    }

    @Test
    void testDeleteTask() {
        Todo todo = new Todo();
        Task task = new Task();
        todo.addTask(task);

        UUID taskId = task.getId();

        todo.deleteTask(taskId);

        assertThrows(NoSuchElementException.class, () -> todo.getTask(taskId));
    }

    @Test
    void testDeleteTaskNull() {
        Todo todo = new Todo();
        todo.addTask(new Task());

        assertThrows(IllegalArgumentException.class, () -> todo.deleteTask(null));
    }

    @Test
    void testDeleteTaskTwice() {
        Todo todo = new Todo();
        Task task = new Task();
        todo.addTask(task);

        UUID taskId = task.getId();

        todo.deleteTask(taskId);

        assertThrows(NoSuchElementException.class, () -> todo.deleteTask(taskId));
    }

    @Test
    void testModifyTaskOutsideTodoDoesNotAffectInternalState() {
        Todo todo = new Todo();
        Task originalTask = new Task();
        originalTask.setTitle("Original Title");
        originalTask.setDescription("Original Description");
        todo.addTask(originalTask);

        Task retrievedTask = todo.getTask(originalTask.getId());

        retrievedTask.setTitle("Modified Title");
        retrievedTask.setDescription("Modified Description");

        Task internalTask = todo.getTask(originalTask.getId());
        assertEquals(
                "Original Title",
                internalTask.getTitle(),
                "Title should remain unchanged in Todo"
        );
        assertEquals(
                "Original Description",
                internalTask.getDescription(),
                "Description should remain unchanged in Todo"
        );
    }
}
