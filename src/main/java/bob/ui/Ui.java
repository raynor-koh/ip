package bob.ui;

import java.util.List;
import java.util.Scanner;

import bob.task.Task;

/**
 * Reads commands from the console and displays Bob's responses.
 */
public class Ui {
    private static final String CHATBOT_NAME = "Bob";
    private static final String LINE_PREFIX = "    ";
    private static final String MESSAGE_PREFIX = "     ";
    private static final String DIVIDER = LINE_PREFIX + "____________________________________________________________";
    private final Scanner scanner;

    /** Creates a user interface that reads commands from standard input. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Returns whether another command is available from the user. */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /** Reads the next command entered by the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Closes the input stream owned by this user interface. */
    public void close() {
        scanner.close();
    }

    /** Displays Bob's welcome banner and greeting. */
    public void showWelcome() {
        showLine();
        showBanner();
        System.out.println(MESSAGE_PREFIX + "Hello! I'm " + CHATBOT_NAME + ".");
        System.out.println(MESSAGE_PREFIX + "What can I do for you?");
        showLine();
    }

    /** Displays Bob's farewell message. */
    public void showGoodbye() {
        System.out.println(MESSAGE_PREFIX + "Bye. Hope to see you again soon!");
    }

    /** Displays the task that was added and the updated task count. */
    public void showAdded(Task task, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Got it. I've added this task:");
        System.out.println(MESSAGE_PREFIX + "added: " + task);
        System.out.println(MESSAGE_PREFIX + "Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays every task and the supplied task count. */
    public void showTasks(List<Task> tasks, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks.get(i);
            System.out.println(MESSAGE_PREFIX + (i + 1) + "." + task);
        }
    }

    /** Displays the task that was deleted and the updated task count. */
    public void showDeleted(Task task, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Noted. I've removed this task:");
        System.out.println(MESSAGE_PREFIX + task);
        String taskLabel = taskCount == 1 ? "task" : "tasks";
        System.out.println(MESSAGE_PREFIX + "Now you have " + taskCount + " " + taskLabel + " in the list.");
    }

    /** Displays the task that was marked as done. */
    public void showMarked(Task task) {
        System.out.println(MESSAGE_PREFIX + "Nice! I've marked this task as done:");
        System.out.println(MESSAGE_PREFIX + "  " + task);
    }

    /** Displays the task that was marked as not done. */
    public void showUnmarked(Task task) {
        System.out.println(MESSAGE_PREFIX + "OK, I've marked this task as not done yet:");
        System.out.println(MESSAGE_PREFIX + "  " + task);
    }

    /** Displays a recoverable input error. */
    public void showError(String message) {
        System.out.println(MESSAGE_PREFIX + "I couldn't process that: " + message);
    }

    /** Displays a horizontal divider. */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    private void showBanner() {
        String banner = MESSAGE_PREFIX + " ____        _     \n" + MESSAGE_PREFIX + "| __ )  ___ | |__\n"
                                        + MESSAGE_PREFIX + "|  _ \\ / _ \\| '_ \\\n" + MESSAGE_PREFIX
                                        + "| |_) | (_) | |_) |\n" + MESSAGE_PREFIX + "|____/ \\___/|_.__/";

        System.out.println(banner);
    }
}
