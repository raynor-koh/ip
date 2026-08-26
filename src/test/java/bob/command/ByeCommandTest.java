package bob.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/** Tests the exit behavior of {@link ByeCommand}. */
class ByeCommandTest {

    @Test
    void execute_displaysGoodbyeMessage() {
        PrintStream originalOutput = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            new ByeCommand().execute(new TaskList(), new Ui(), new Storage());

            assertTrue(output.toString(StandardCharsets.UTF_8).contains("Bye. Hope to see you again soon!"));
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    void isExit_returnsTrue() {
        assertTrue(new ByeCommand().isExit());
    }
}
