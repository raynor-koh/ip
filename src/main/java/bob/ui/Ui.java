package bob.ui;

import java.util.List;
import java.util.Scanner;

import bob.task.Task;

/**
 * Handles console input and presents chatbot messages to the user.
 */
public class Ui {
    private static final String CHATBOT_NAME = "Bob";
    private static final String LINE_PREFIX = "    ";
    private static final String MESSAGE_PREFIX = "     ";
    private static final String DIVIDER = LINE_PREFIX + "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Creates a user interface that reads commands from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns whether another command is available from the user.
     *
     * @return true if another line can be read
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return the next input line
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the input stream owned by this user interface.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays the chatbot banner and greeting.
     */
    public void showWelcome() {
        showLine();
        showBanner();
        System.out.println(MESSAGE_PREFIX + "Hello! I'm " + CHATBOT_NAME + ".");
        System.out.println(MESSAGE_PREFIX + "What can I do for you?");
        showLine();
    }

    /**
     * Displays the farewell message.
     */
    public void showGoodbye() {
        System.out.println(MESSAGE_PREFIX + "Bye. Hope to see you again soon!");
    }

    /**
     * Confirms that a task was added and displays the new task count.
     *
     * @param task task that was added
     * @param taskCount number of tasks after the addition
     */
    public void showAdded(Task task, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Got it. I've added this task:");
        System.out.println(MESSAGE_PREFIX + "added: " + task);
        System.out.println(MESSAGE_PREFIX + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays all tasks in their numbered list order.
     *
     * @param tasks tasks to display
     * @param taskCount number of tasks to display
     */
    public void showTasks(List<Task> tasks, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks.get(i);
            System.out.println(MESSAGE_PREFIX + (i + 1) + "." + task);
        }
    }

    /**
     * Confirms that a task was deleted and displays the remaining task count.
     *
     * @param task task that was deleted
     * @param taskCount number of tasks after the deletion
     */
    public void showDeleted(Task task, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Noted. I've removed this task:");
        System.out.println(MESSAGE_PREFIX + task);
        String taskLabel = taskCount == 1 ? "task" : "tasks";
        System.out.println(MESSAGE_PREFIX + "Now you have " + taskCount + " " + taskLabel + " in the list.");
    }

    /**
     * Confirms that a task was marked as done.
     *
     * @param task task whose status changed
     */
    public void showMarked(Task task) {
        System.out.println(MESSAGE_PREFIX + "Nice! I've marked this task as done:");
        System.out.println(MESSAGE_PREFIX + "  " + task);
    }

    /**
     * Confirms that a task was marked as not done.
     *
     * @param task task whose status changed
     */
    public void showUnmarked(Task task) {
        System.out.println(MESSAGE_PREFIX + "OK, I've marked this task as not done yet:");
        System.out.println(MESSAGE_PREFIX + "  " + task);
    }

    /**
     * Displays a recoverable input error.
     *
     * @param message explanation of the error
     */
    public void showError(String message) {
        System.out.println(MESSAGE_PREFIX + "I couldn't process that: " + message);
    }

    /**
     * Displays a divider between chatbot messages.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays the chatbot name as ASCII art.
     */
    private void showBanner() {
        // @formatter:off
        String banner = MESSAGE_PREFIX + " ____        _     \n"
                + MESSAGE_PREFIX + "| __ )  ___ | |__\n"
                + MESSAGE_PREFIX + "|  _ \\ / _ \\| '_ \\\n"
                + MESSAGE_PREFIX + "| |_) | (_) | |_) |\n"
                + MESSAGE_PREFIX + "|____/ \\___/|_.__/";
        // @formatter:on

        System.out.println(banner);
    }
}
