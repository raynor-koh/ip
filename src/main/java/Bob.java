import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String chatBotName = "Bob";
        String linePrefix = "    ";
        String messagePrefix = "     ";
        String divider = linePrefix + "____________________________________________________________";
        System.out.println(divider);

        // @formatter:off
        String banner = messagePrefix + " ____        _     \n"
                + messagePrefix + "| __ )  ___ | |__\n"
                + messagePrefix + "|  _ \\ / _ \\| '_ \\\n"
                + messagePrefix + "| |_) | (_) | |_) |\n"
                + messagePrefix + "|____/ \\___/|_.__/";
        // @formatter:on
        System.out.println(banner);

        String helloMessage = messagePrefix + "Hello! I'm " + chatBotName + ".\n" + messagePrefix
                                        + "What can I do for you?";
        System.out.println(helloMessage);

        System.out.println(divider);

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println(divider);
            if (command.equals("bye")) {
                System.out.println(messagePrefix + goodbyeMessage);
                System.out.println(divider);
                break;
            }
            System.out.println(messagePrefix + command);
            System.out.println(divider);
        }
        scanner.close();
    }
}
