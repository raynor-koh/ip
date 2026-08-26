package bob.parser;

import java.util.Locale;

import bob.command.AddCommand;
import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.CommandType;
import bob.command.DeleteCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.TaskDateTime;
import bob.task.ToDo;

/**
 * Converts raw user input into command objects.
 */
public class Parser {

    public Command parse(String input) throws BobException {
        if (input == null || input.trim().isEmpty()) {
            throw new BobException("Please enter a command, such as 'todo read chapter 1'.");
        }

        String[] words = input.trim().split("\\s+", 2);
        CommandType command = CommandType.fromKeyword(words[0].toLowerCase(Locale.ROOT));
        String argument = words.length == 2 ? words[1].trim() : "";

        if (command == null) {
            throw new BobException("I don't recognise that command. Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', 'delete', or 'bye'.");
        }

        switch (command) {
        case BYE:
            requireNoArgument(argument, command);
            return new ByeCommand();
        case LIST:
            requireNoArgument(argument, command);
            return new ListCommand();
        case MARK:
            return new MarkCommand(parseTaskNumber(argument, command));
        case UNMARK:
            return new UnmarkCommand(parseTaskNumber(argument, command));
        case DELETE:
            return new DeleteCommand(parseTaskNumber(argument, command));
        case TODO:
            return new AddCommand(new ToDo(requireText(argument,
                                            "A todo needs a description. Try: todo read chapter 1")));
        case DEADLINE:
            return parseDeadline(argument);
        case EVENT:
            return parseEvent(argument);
        }

        throw new BobException("I don't recognise that command. Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', 'delete', or 'bye'.");
    }

    private Command parseDeadline(String argument) throws BobException {
        String[] parts = argument.split("\\s+/by\\s+", 2);
        if (parts.length < 2) {
            throw new BobException("A deadline needs a due date. Try: deadline submit report /by Friday");
        }
        String description = requireText(parts[0].trim(), "A deadline needs a description before '/by'.");
        TaskDateTime dueDate = DateTimeParser.parseUserInput(parts[1].trim());
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
        TaskDateTime from = DateTimeParser.parseUserInput(toParts[0].trim());
        TaskDateTime to = DateTimeParser.parseUserInput(toParts[1].trim());

        return new AddCommand(new Event(description, from, to));
    }

    private int parseTaskNumber(String argument, CommandType command) throws BobException {
        if (argument.isEmpty()) {
            throw new BobException("'" + command.getKeyword() + "' needs a task number. Try: " + command.getKeyword()
                                            + " 1");
        }
        try {
            int taskNumber = Integer.parseInt(argument);
            if (taskNumber <= 0) {
                throw new BobException("Task numbers start at 1. Use 'list' to see the available tasks.");
            }
            return taskNumber;
        } catch (NumberFormatException exception) {
            throw new BobException("'" + command.getKeyword() + "' needs a whole-number task index, such as 1.");
        }
    }

    private String requireText(String text, String message) throws BobException {
        if (text.isEmpty()) {
            throw new BobException(message);
        }
        if (text.contains(Storage.FIELD_SEPARATOR)) {
            throw new BobException("The character '|' is not allowed in task details.");
        }
        return text;
    }

    private void requireNoArgument(String argument, CommandType command) throws BobException {
        if (!argument.isEmpty()) {
            throw new BobException("'" + command.getKeyword() + "' does not take extra arguments.");
        }
    }
}
