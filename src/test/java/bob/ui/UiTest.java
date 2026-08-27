package bob.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.task.Task;
import bob.task.ToDo;

/** Tests console input and output behavior of the user interface. */
class UiTest {
    private InputStream originalInput;
    private PrintStream originalOutput;
    private ByteArrayOutputStream output;
    private Ui ui;

    @BeforeEach
    void setUpStreams() {
        originalInput = System.in;
        originalOutput = System.out;
        System.setIn(new ByteArrayInputStream(
                "first command\nsecond command\n".getBytes(StandardCharsets.UTF_8)));
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        ui = new Ui();
    }

    @AfterEach
    void restoreStreams() {
        ui.close();
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    void hasNextLineAndReadCommand_multipleLines_readsCommandsInOrder() {
        assertTrue(ui.hasNextLine());
        assertEquals("first command", ui.readCommand());
        assertTrue(ui.hasNextLine());
        assertEquals("second command", ui.readCommand());
        assertFalse(ui.hasNextLine());
    }

    @Test
    void showWelcomeAndGoodbye_displaysBannerAndMessages() {
        ui.showWelcome();
        ui.showGoodbye();

        String displayedText = output.toString(StandardCharsets.UTF_8);
        assertTrue(displayedText.contains("Hello! I'm Bob."));
        assertTrue(displayedText.contains("What can I do for you?"));
        assertTrue(displayedText.contains("____        _"));
        assertTrue(displayedText.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    void showTaskMessages_displaysTaskDetailsAndCounts() {
        Task firstTask = new ToDo("first task");
        Task secondTask = new ToDo("second task");
        secondTask.markAsDone();

        ui.showAdded(firstTask, 2);
        ui.showTasks(List.of(firstTask, secondTask), 2);
        ui.showMatchingTasks(List.of(secondTask));
        ui.showDeleted(firstTask, 1);
        ui.showDeleted(secondTask, 0);
        ui.showMarked(secondTask);
        ui.showUnmarked(firstTask);

        String displayedText = output.toString(StandardCharsets.UTF_8);
        assertTrue(displayedText.contains("added: [T][ ] first task"));
        assertTrue(displayedText.contains("Now you have 2 tasks in the list."));
        assertTrue(displayedText.contains("1.[T][ ] first task"));
        assertTrue(displayedText.contains("2.[T][X] second task"));
        assertTrue(displayedText.contains("Here are the matching tasks in your list:"));
        assertTrue(displayedText.contains("Now you have 1 task in the list."));
        assertTrue(displayedText.contains("Now you have 0 tasks in the list."));
        assertTrue(displayedText.contains("marked this task as done"));
        assertTrue(displayedText.contains("marked this task as not done yet"));
    }

    @Test
    void showErrorAndLine_displaysErrorAndDivider() {
        ui.showError("invalid command");
        ui.showLine();

        String displayedText = output.toString(StandardCharsets.UTF_8);
        assertTrue(displayedText.contains("I couldn't process that: invalid command"));
        assertTrue(displayedText.contains("____________________________________________________________"));
    }
}
