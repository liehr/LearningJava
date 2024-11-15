package de.tudl.learning.jw1;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    private static final Todo todoList = new Todo();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int userChoice = 0;

        while (userChoice != 6) {
            userChoice = menu();

            switch (userChoice) {
                case 1:
                    listAllTasks();
                    break;
                case 2:
                    showSingleTask();
                    break;
                case 3:
                    addTask();
                    break;
                case 4:
                    editTask();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    logger.info("Exiting the program.");
                    break;
                default:
                    logger.warning("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static int menu() {
        System.out.println("Choose from these choices:");
        System.out.println("-------------------------");
        System.out.println("1 - List all tasks");
        System.out.println("2 - Show a task");
        System.out.println("3 - Add a task");
        System.out.println("4 - Edit a task");
        System.out.println("5 - Delete a Task");
        System.out.println("6 - Quit");

        int selection = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please enter a number: ");
            if (input.hasNextInt()) {
                selection = input.nextInt();
                input.nextLine();
                validInput = true;
            } else {
                logger.warning("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }

        return selection;
    }

    private static void listAllTasks() {
        todoList.getTasks().forEach(Main::showTask);
    }

    private static void showTask(Task task) {
        System.out.println("----------------------------");
        System.out.printf("ID: %s%n", task.getId());
        System.out.printf("Title: %s%n", task.getTitle());
        System.out.printf("Description: %s%n", task.getDescription());
        System.out.printf("Due Date: %s%n", task.getDueDate());
        System.out.println("----------------------------");
    }

    private static void showSingleTask() {
        todoList.getTasks().forEach(e -> logger.log(Level.INFO, "Task ID: {0}", e.getId()));

        System.out.println("Enter the ID of the task you want to view:");
        String id = input.nextLine();
        Task searchedTask;

        try {
            searchedTask = todoList.getTask(UUID.fromString(id));
            showTask(searchedTask);
        } catch (Exception e) {
            logger.log(Level.WARNING, "No task found using ID {0}", id);
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = input.nextLine();

        System.out.print("Enter task description: ");
        String description = input.nextLine();

        System.out.print("Enter due date (format: YYYY-MM-DDTHH:MM): ");
        String dateTimeInput = input.nextLine();
        LocalDateTime dueDate;

        try {
            dueDate = LocalDateTime.parse(dateTimeInput);
            Task newTask = new Task(UUID.randomUUID(), title, description, dueDate);
            todoList.addTask(newTask);
            logger.info("Task added successfully.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Invalid date format or task data. Please try again.");
        }
    }

    private static void editTask() {
        todoList.getTasks().forEach(e -> logger.log(Level.INFO, "Task ID: {0}", e.getId()));

        System.out.print("Enter task id:");
        String id = input.nextLine();

        Task searchTask = null;

        try
        {
            searchTask = todoList.getTask(UUID.fromString(id));
            showTask(searchTask);
        }
        catch (Exception e)
        {
            logger.log(Level.WARNING, "No task found using ID {0}", id);
        }

        if (searchTask == null) return;

        System.out.printf("Enter new title (Old: %s) :%n", searchTask.getTitle());
        String newTitle = input.nextLine();

        System.out.printf("Enter a new description (Old: %s) :%n", searchTask.getDescription());
        String newDescription = input.nextLine();

        System.out.printf("Enter new due date (Old: %s) :%n", searchTask.getDueDate());
        String newDueDate = input.nextLine();

        try {
            if (!newTitle.isEmpty())
                searchTask.setTitle(newTitle);

            if (!newDescription.isEmpty())
                searchTask.setDescription(newDescription);

            if (!newDueDate.isEmpty())
                searchTask.setDueDate(LocalDateTime.parse(newDueDate));

            todoList.updateTask(searchTask);
            logger.log(Level.INFO, "Task {0} updated successfully!", searchTask.getTitle());
        }
        catch (Exception e)
        {
            logger.log(Level.WARNING, "Cannot update Task with Id {0}", searchTask.getId());
        }
    }

    private static void deleteTask() {
        todoList.getTasks().forEach(e -> logger.log(Level.INFO, "Task ID: {0}", e.getId()));

        System.out.print("Enter task id:");
        String id = input.nextLine();

        Task searchTask = null;

        try
        {
            searchTask = todoList.getTask(UUID.fromString(id));
            showTask(searchTask);
        }
        catch (Exception e)
        {
            logger.log(Level.WARNING, "No task found using ID {0}", id);
        }

        if (searchTask == null) return;

        System.out.printf("Are you shure you want to delete this task? (Y, N) %n");
        String choice = input.nextLine();

        if (!choice.toLowerCase().contentEquals("y"))
            return;

        try
        {
            todoList.deleteTask(searchTask.getId());
            logger.info("Task deleted successfully!");
        }
        catch (Exception e)
        {
            logger.warning("Cannot delete task!");
        }
    }
}