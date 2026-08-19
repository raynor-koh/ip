public class Ui {
    private static final String CHATBOT_NAME = "Bob";
    private static final String LINE_PREFIX = "    ";
    private static final String MESSAGE_PREFIX = "     ";
    private static final String DIVIDER = LINE_PREFIX + "____________________________________________________________";

    public void showWelcome() {
        showDivider();
        showBanner();
        System.out.println(MESSAGE_PREFIX + "Hello! I'm " + CHATBOT_NAME + ".");
        System.out.println(MESSAGE_PREFIX + "What can I do for you?");
        showDivider();
    }

    public void showGoodbye() {
        System.out.println(MESSAGE_PREFIX + "Bye. Hope to see you again soon!");
    }

    public void showAdded(Task task, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Got it. I've added this task:");
        System.out.println(MESSAGE_PREFIX + "added: " + task);
        System.out.println(MESSAGE_PREFIX + "Now you have " + taskCount + " tasks in the list.");
    }

    public void showTasks(Task[] tasks, int taskCount) {
        System.out.println(MESSAGE_PREFIX + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks[i];
            System.out.println(MESSAGE_PREFIX + (i + 1) + "." + task);
        }
    }

    public void showMarked(Task task) {
        System.out.println(MESSAGE_PREFIX + "Nice! I've marked this task as done:");
        System.out.println(MESSAGE_PREFIX + "  " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println(MESSAGE_PREFIX + "OK, I've marked this task as not done yet:");
        System.out.println(MESSAGE_PREFIX + "  " + task);
    }

    /** Displays a recoverable input error. */
    public void showError(String message) {
        System.out.println(MESSAGE_PREFIX + "I couldn't process that: " + message);
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

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
