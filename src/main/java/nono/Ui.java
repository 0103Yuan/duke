package nono;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm NoNo");
        System.out.println("What can I do for you?\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Current task list:\nTask No. Status Type Description");
        if (tasks.isEmpty()) {
            System.out.println("No task added yet\n");
        } else {
            for (int j = 0; j < tasks.size(); j++) {
                try {
                    System.out.println("     " + (j + 1) + ". " + tasks.getTask(j));
                } catch (UserInputException e) {
                    // This should never happen since we're iterating within bounds
                    System.out.println("     " + (j + 1) + ". [Error: Invalid task]");
                }
            }
            System.out.println();
        }
    }

    public void showMarkResult(Task task, boolean isMark) {
        String action = isMark ? "done" : "not done yet";
        System.out.println("Nice! The task is marked as " + action + ":");
        System.out.println(task + "\n");
    }

    public void showDeleteResult(Task task, int remainingTasks) {
        System.out.println("OK! The task is deleted:");
        System.out.println(task);
        System.out.println("Now have " + remainingTasks + " task left\n");
    }

    public void showAddResult(Task task) {
        System.out.println("New task added: " + task + "\n");
    }
}