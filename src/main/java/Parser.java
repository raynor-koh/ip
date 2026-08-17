/**
 * Converts raw user input into command objects.
 */
public class Parser {

    public Command parse(String input) {
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        return new EchoCommand(input);
    }
}
