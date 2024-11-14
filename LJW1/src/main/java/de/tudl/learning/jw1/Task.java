package de.tudl.learning.jw1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a Task with properties like title, description, and due date.
 *
 * @author Florian Liehr
 * @version 1.0
 * @since 1.0
 */
public class Task {

    /**
     * A unique identifier for the task.
     */
    private UUID id;

    /**
     * The title of the task.
     */
    private String title;

    /**
     * A detailed description of the task.
     */
    private String description;

    /**
     * The date and time by which the task should be completed.
     */
    private LocalDateTime dueDate;

    /**
     * Creates a new Task with the provided properties. If the id is null, a new UUID will be generated.
     *
     * @param id The unique identifier for the task (can be null).
     * @param title The title of the task. (must not be null or empty)
     * @param description The description of the task. (must not be null or empty)
     * @param dueDate The date and time by which the task should be completed. (must be a valid date in the future)
     * @throws IllegalArgumentException if title, description, or dueDate is invalid.
     */
    public Task(
            UUID id,
            String title,
            String description,
            LocalDateTime dueDate
    ) {
        if (id == null) this.id = UUID.randomUUID();

        if (title == null || title.isEmpty()) throw new IllegalArgumentException(
                "Title cannot be null or empty!"
        );

        if (
                description == null || description.isEmpty()
        ) throw new IllegalArgumentException(
                "Description cannot be null or empty!"
        );

        if (
                dueDate == null || dueDate.toLocalDate().isBefore(LocalDate.now())
        ) throw new IllegalArgumentException(
                "Due date must be a valid date in the future!"
        );

        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Creates a new Task with a default title, description, and due date set to the current date and time.
     * The id will be randomly generated.
     */
    public Task() {
        this.id = UUID.randomUUID();
        this.title = "New Task";
        this.description = "New Task Description";
        this.dueDate = LocalDateTime.now();
    }

    /**
     * Creates a copy of the provided Task object.
     *
     * @param other The Task object to be copied.
     */
    public Task(Task other) {
        this.id = other.getId();
        this.title = other.getTitle();
        this.description = other.getDescription();
        this.dueDate = other.getDueDate();
    }

    /**
     *  Returns the unique identifier of the task.
     *
     *  @return The UUID of the task.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the task. If the provided id is null or the same as the current id, no change is made.
     *
     * @param id The new UUID for the task.
     */
    public void setId(UUID id) {
        if (id == null || this.id.equals(id)) return;

        this.id = id;
    }

    /**
     * Returns the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task. If the provided title is null or the same as the current title, no change is made.
     * The title is stripped of whitespaces in front or at the end of the string.
     *
     * @param title The new title of the task.
     * @throws IllegalArgumentException if title is empty.
     */
    public void setTitle(String title) {
        if (title == null || this.title.equals(title)) return;

        if (title.isEmpty()) throw new IllegalArgumentException(
                "Title cannot be empty!"
        );

        this.title = title.strip();
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task. If the provided description is null or the same as the current description, no change is made.
     * The description is stripped of whitespaces in front or at the end of the string.
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        if (description == null || this.description.equals(description)) return;

        if (description.isEmpty()) throw new IllegalArgumentException(
                "Description cannot be empty!"
        );

        this.description = description.strip();
    }

    /**
     * Returns the date and time by which the task should be completed.
     *
     * @return The due date of the task as a LocalDateTime object.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Sets the date and time by which the task should be completed.
     *
     * @param dueDate The new due date for the task. (must be a valid date in the future)
     * @throws IllegalArgumentException if the provided due date is null or in the past.
     */
    public void setDueDate(LocalDateTime dueDate) {
        if (dueDate == null || this.dueDate.equals(dueDate)) return;

        if (
                dueDate.toLocalDate().isBefore(LocalDate.now())
        ) throw new IllegalArgumentException("Due date cannot be in the past!");

        this.dueDate = dueDate;
    }

    /**
     * Indicates whether another object is equal to this Task.
     *
     * @param o The object to compare with this Task.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return (
                Objects.equals(id, task.id) &&
                        Objects.equals(title, task.title) &&
                        Objects.equals(description, task.description) &&
                        Objects.equals(dueDate, task.dueDate)
        );
    }

    /**
     * Returns a hash code value for this Task.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate);
    }
}
