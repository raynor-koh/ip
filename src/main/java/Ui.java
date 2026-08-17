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

    public void showEcho(String message) {
        System.out.println(MESSAGE_PREFIX + message);
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
