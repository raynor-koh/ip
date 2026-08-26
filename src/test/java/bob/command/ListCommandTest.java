package bob.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.task.ToDo;
import bob.ui.Ui;

/** Tests task-list display behavior of {@link ListCommand}. */
class ListCommandTest {

    @Test
    void execute_tasksPresent_displaysNumberedTasks() {
        PrintStream originalOutput = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            TaskList taskList = new TaskList(List.of(new ToDo("first task"), new ToDo("second task")));

            new ListCommand().execute(taskList, new Ui(), new Storage());

            String displayedText = output.toString(StandardCharsets.UTF_8);
            assertTrue(displayedText.contains("1.[T][ ] first task"));
            assertTrue(displayedText.contains("2.[T][ ] second task"));
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    void isExit_returnsFalse() {
        assertFalse(new ListCommand().isExit());
    }
}
