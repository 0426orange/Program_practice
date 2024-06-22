import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task implements Serializable {
    private String description;
    private boolean isCompleted;
    private int priority;

    public Task(String description, int priority) {
        this.description = description;
        this.isCompleted = false;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + description + " (Priority: " + priority + ")";
    }
}

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description, int priority) {
        tasks.add(new Task(description, priority));
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void markTaskCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void saveTasksToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasksFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to Task Manager!");
        taskManager.loadTasksFromFile("tasks.ser");

        do {
            System.out.println("Enter command (add, remove, view, complete, save, quit):");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.println("Enter task description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter task priority (1-5):");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    taskManager.addTask(description, priority);
                    break;
                case "remove":
                    System.out.println("Enter task index to remove:");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    taskManager.removeTask(index - 1);
                    break;
                case "view":
                    taskManager.viewTasks();
                    break;
                case "complete":
                    System.out.println("Enter task index to mark as completed:");
                    int completeIndex = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    taskManager.markTaskCompleted(completeIndex - 1);
                    break;
                case "save":
                    taskManager.saveTasksToFile("tasks.ser");
                    break;
                case "quit":
                    System.out.println("Exiting the program.");
                    taskManager.saveTasksToFile("tasks.ser");
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        } while (!command.equals("quit"));

        scanner.close();
    }
}
