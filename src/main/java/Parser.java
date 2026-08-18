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
        if (words[0].equals("unmark")) {
            int taskNumber = Integer.parseInt(words[1].trim());
            return new UnmarkCommand(taskNumber);
        }
        if (words[0].equals("todo")) {
            return new AddCommand(new ToDo(words[1]));
        }
        if (words[0].equals("deadline")) {
            String[] parts = words[1].split(" /by ", 2);

            String description = parts[0];
            String by = parts[1];

            return new AddCommand(new Deadline(description, by));
        }
        if (words[0].equals("event")) {
            String[] fromParts = words[1].split(" /from ", 2);
            String description = fromParts[0];

            String[] toParts = fromParts[1].split(" /to ", 2);
            String from = toParts[0];
            String to = toParts[1];

            return new AddCommand(new Event(description, from, to));
        }
        // Fallback Case: Will implement replacement later
        return new AddCommand(new ToDo(input));
    }
}
