/**
 * Converts raw user input into command objects.
 */
public class Parser {

    public Command parse(String input) throws BobException {
        if (input == null || input.trim().isEmpty()) {
            throw new BobException("Please enter a command, such as 'todo read chapter 1'.");
        }

        String[] words = input.trim().split("\\s+", 2);
        String command = words[0].toLowerCase();
        String argument = words.length == 2 ? words[1].trim() : "";

        switch (command) {
        case "bye":
            requireNoArgument(argument, "bye");
            return new ByeCommand();
        case "list":
            requireNoArgument(argument, "list");
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskNumber(argument, "mark"));
        case "unmark":
            return new UnmarkCommand(parseTaskNumber(argument, "unmark"));
        case "todo":
            return new AddCommand(new ToDo(requireText(argument,
                                            "A todo needs a description. Try: todo read chapter 1")));
        case "deadline":
            return parseDeadline(argument);
        case "event":
            return parseEvent(argument);
        default:
            throw new BobException("I don't recognise that command. Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', or 'bye'.");
        }
    }

    private Command parseDeadline(String argument) throws BobException {
        String[] parts = argument.split("\\s+/by\\s+", 2);
        if (parts.length < 2) {
            throw new BobException("A deadline needs a due date. Try: deadline submit report /by Friday");
        }
        String description = requireText(parts[0].trim(), "A deadline needs a description before '/by'.");
        String dueDate = requireText(parts[1].trim(), "A deadline needs a due date after '/by'.");
        return new AddCommand(new Deadline(description, dueDate));
    }

    private Command parseEvent(String argument) throws BobException {
        String[] fromParts = argument.split("\\s+/from\\s+", 2);
        if (fromParts.length < 2) {
            throw new BobException("An event needs a time range. Try: event meeting /from 2pm /to 3pm");
        }
        String description = requireText(fromParts[0].trim(), "An event needs a description before '/from'.");

        String[] toParts = fromParts[1].split("\\s+/to\\s+", 2);
        if (toParts.length < 2) {
            throw new BobException("An event needs an end time after '/to'. Try: event meeting /from 2pm /to 3pm");
        }
        String from = requireText(toParts[0].trim(), "An event needs a start time after '/from'.");
        String to = requireText(toParts[1].trim(), "An event needs an end time after '/to'.");
        return new AddCommand(new Event(description, from, to));
    }

    private int parseTaskNumber(String argument, String command) throws BobException {
        if (argument.isEmpty()) {
            throw new BobException("'" + command + "' needs a task number. Try: " + command + " 1");
        }
        try {
            int taskNumber = Integer.parseInt(argument);
            if (taskNumber <= 0) {
                throw new BobException("Task numbers start at 1. Use 'list' to see the available tasks.");
            }
            return taskNumber;
        } catch (NumberFormatException exception) {
            throw new BobException("'" + command + "' needs a whole-number task index, such as 1.");
        }
    }

    private String requireText(String text, String message) throws BobException {
        if (text.isEmpty()) {
            throw new BobException(message);
        }
        return text;
    }

    private void requireNoArgument(String argument, String command) throws BobException {
        if (!argument.isEmpty()) {
            throw new BobException("'" + command + "' does not take extra arguments.");
        }
    }
}
