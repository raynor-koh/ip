/**
 * Converts raw user input into command objects.
 */
public class Parser {

    public Command parse(String input) {
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }

        String[] words = input.split(" ", 2);
        if (words[0].equals("mark")) {
            int taskNumber = Integer.parseInt(words[1].trim());
            return new MarkCommand(taskNumber);
        }
        return new AddCommand(input);
    }
}
