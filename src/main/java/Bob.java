public class Bob {
    public static void main(String[] args) {
        String chatBotName = "Bob";
        String divider = "____________________________________________________________";
        System.out.println(divider);

        // @formatter:off
        String banner = " ____        _     \n"
                + "| __ )  ___ | |__\n"
                + "|  _ \\ / _ \\| '_ \\\n"
                + "| |_) | (_) | |_) |\n"
                + "|____/ \\___/|_.__/";
        // @formatter:on
        System.out.println(banner);

        String helloMessage = "Hello! I'm " + chatBotName + ".\n" + "What can I do for you?";
        System.out.println(helloMessage);

        System.out.println(divider);

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);

        System.out.println(divider);
    }
}
