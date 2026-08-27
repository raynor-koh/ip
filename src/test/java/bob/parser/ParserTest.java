package bob.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bob.command.AddCommand;
import bob.command.ByeCommand;
import bob.command.DeleteCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;
import bob.exception.BobException;

/** Tests conversion of user input into executable commands. */
class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void parse_commandsWithoutArguments_returnsMatchingCommand() {
        assertAll(
                () -> assertInstanceOf(ByeCommand.class, parser.parse("bye")),
                () -> assertInstanceOf(ListCommand.class, parser.parse("list")));
    }

    @Test
    void parse_taskMutationCommandsWithPositiveIndex_returnsMatchingCommand() {
        assertAll(
                () -> assertInstanceOf(MarkCommand.class, parser.parse("mark 1")),
                () -> assertInstanceOf(UnmarkCommand.class, parser.parse("unmark 2")),
                () -> assertInstanceOf(DeleteCommand.class, parser.parse("delete 3")));
    }

    @Test
    void parse_addCommandsWithValidDetails_returnsAddCommand() {
        assertAll(
                () -> assertInstanceOf(AddCommand.class, parser.parse("todo read a book")),
                () -> assertInstanceOf(AddCommand.class,
                        parser.parse("deadline submit report /by 2/12/2019 1800")),
                () -> assertInstanceOf(AddCommand.class,
                        parser.parse("event project meeting /from 2/12/2019 1800 /to 2/12/2019 1900")));
    }

    @Test
    void parse_mixedCaseAndSurroundingWhitespace_returnsMatchingCommand() throws BobException {
        assertInstanceOf(AddCommand.class, parser.parse("  ToDo read a book  "));
    }

    @Test
    void parse_nullOrBlankInput_bobExceptionThrown() {
        assertAll(
                () -> assertThrows(BobException.class, () -> parser.parse(null)),
                () -> assertThrows(BobException.class, () -> parser.parse("")),
                () -> assertThrows(BobException.class, () -> parser.parse("   ")));
    }

    @Test
    void parse_unknownCommand_bobExceptionThrown() {
        assertThrows(BobException.class, () -> parser.parse("find book"));
    }

    @Test
    void parse_commandThatForbidsArgumentsWithArgument_bobExceptionThrown() {
        assertAll(
                () -> assertThrows(BobException.class, () -> parser.parse("bye now")),
                () -> assertThrows(BobException.class, () -> parser.parse("list all")));
    }

    @Test
    void parse_taskMutationCommandWithInvalidIndex_bobExceptionThrown() {
        assertAll(
                () -> assertThrows(BobException.class, () -> parser.parse("mark")),
                () -> assertThrows(BobException.class, () -> parser.parse("unmark zero")),
                () -> assertThrows(BobException.class, () -> parser.parse("delete 0")),
                () -> assertThrows(BobException.class, () -> parser.parse("delete -1")));
    }

    @Test
    void parse_todoWithInvalidDescription_bobExceptionThrown() {
        assertAll(
                () -> assertThrows(BobException.class, () -> parser.parse("todo")),
                () -> assertThrows(BobException.class, () -> parser.parse("todo buy milk | bread")));
    }

    @Test
    void parse_deadlineWithMissingOrInvalidDetails_exceptionThrown() {
        assertAll(
                () -> assertThrows(BobException.class, () -> parser.parse("deadline submit report")),
                () -> assertThrows(BobException.class, () -> parser.parse("deadline /by 2/12/2019")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> parser.parse("deadline submit report /by invalid date")),
                () -> assertThrows(BobException.class,
                        () -> parser.parse("deadline submit | report /by 2/12/2019")));
    }

    @Test
    void parse_eventWithMissingOrInvalidDetails_exceptionThrown() {
        assertAll(
                () -> assertThrows(BobException.class, () -> parser.parse("event project meeting")),
                () -> assertThrows(BobException.class,
                        () -> parser.parse("event /from 2/12/2019 1800 /to 2/12/2019 1900")),
                () -> assertThrows(BobException.class,
                        () -> parser.parse("event project meeting /from 2/12/2019 1800")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> parser.parse("event project meeting /from invalid /to 2/12/2019 1900")),
                () -> assertThrows(BobException.class,
                        () -> parser.parse(
                                "event project | meeting /from 2/12/2019 1800 /to 2/12/2019 1900")));
    }
}
