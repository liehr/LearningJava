package de.tudl.learning.jw1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * This class represents a To-do list containing a collection of Task objects.
 *
 * @author Florian Liehr
 * @version 1.0
 * @since 1.0
 */
public class Todo {

    /**
     * A list of Task objects representing the tasks in the To-do list.
     */
    private ArrayList<Task> tasks;

    /**
     * Creates a new To-do object with the provided list of tasks.
     *
     * @param tasks The list of tasks for the To-do list. (must not be null)
     * @throws IllegalArgumentException if the provided list of tasks is null.
     */
    public Todo(List<Task> tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("Task should not be null!");
        }
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Creates a new empty To-do object.
     */
    public Todo() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new Task object to the To-do list.
     *
     * @param task The Task object to be added. (must not be null)
     * @throws IllegalArgumentException if the provided task is null or if a task with the same id already exists in the list.
     */
    public void addTask(Task task) {
        if (task == null) throw new IllegalArgumentException(
                "Task should not be null!"
        );

        if (tasks.stream().anyMatch(e -> e.getId().equals(task.getId()))) return;

        tasks.add(new Task(task));
    }

    /**
     * Returns a defensive copy of the list of tasks in the To-do list.
     *
     * @return A new List object containing copies of the tasks in the To-do list.
     */
    public List<Task> getTasks() {
        List<Task> tasksCopy = new ArrayList<>();

        for (Task task : tasks) {
            tasksCopy.add(new Task(task));
        }

        return tasksCopy;
    }

    /**
     * Sets the list of tasks in the To-do list.
     *
     * @param tasks The new list of tasks for the To-do list. (must not be null and must not be empty)
     * @throws IllegalArgumentException if the provided list is null or empty, or if the current list is already equal to the provided list.
     */
    public void setTasks(List<Task> tasks) {
        if (tasks == null || (this.tasks.equals(tasks) && !tasks.isEmpty())) {
            return;
        }

        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("Tasks should not be empty!");
        }

        this.tasks = new ArrayList<>();
        for (Task task : tasks) {
            this.tasks.add(new Task(task));
        }
    }

    /**
     * Retrieves a specific Task object from the To-do list based on its unique identifier.
     *
     * @param id The UUID of the task to be retrieved. (must not be null)
     * @return A Task object matching the provided id, or throws an exception if no task is found.
     * @throws IllegalArgumentException if the provided id is null.
     * @throws NoSuchElementException if no task is found with the provided id.
     */
    public Task getTask(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id should not be null!");
        }

        return tasks
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(Task::new)
                .orElseThrow(() ->
                        new NoSuchElementException("No task found with ID:" + id)
                );
    }

    /**
     * Updates an existing Task object in the To-do list with the provided information.
     *
     * @param updatedTask The Task object containing the updated information. (must not be null)
     * @throws IllegalArgumentException if the provided updatedTask is null or if no task with the same id exists in the list.
     */
    public void updateTask(Task updatedTask) {
        if (updatedTask == null) {
            throw new IllegalArgumentException("Updated Task should not be null!");
        }

        Task task = getTask(updatedTask.getId());

        if (task.equals(updatedTask)) {
            return;
        }

        Task updatedTaskCopy = new Task(updatedTask);
        int indexOfTask = tasks.indexOf(task);

        tasks.set(indexOfTask, updatedTaskCopy);
    }

    /**
     * Removes a task from the To-do list based on its unique identifier.
     *
     * @param taskId The UUID of the task to be removed.
     * @throws IllegalArgumentException if the provided `taskId` is null.
     * @throws NoSuchElementException if no task with the given `taskId` is found.
     */
    public void deleteTask(UUID taskId) {
        if (taskId == null) throw new IllegalArgumentException(
                "TaskId should not be null!"
        );

        Task task = getTask(taskId);

        tasks.remove(task);
    }
}
