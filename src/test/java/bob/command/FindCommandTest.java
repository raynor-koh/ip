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

/** Tests task-search behavior of {@link FindCommand}. */
class FindCommandTest {

    @Test
    void execute_matchingTasksPresent_displaysOnlyMatchingTasks() {
        PrintStream originalOutput = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            TaskList taskList = new TaskList(List.of(
                    new ToDo("read book"), new ToDo("write report"), new ToDo("return BOOK")));

            new FindCommand("book").execute(taskList, new Ui(), new Storage());

            String displayedText = output.toString(StandardCharsets.UTF_8);
            assertTrue(displayedText.contains("Here are the matching tasks in your list:"));
            assertTrue(displayedText.contains("1.[T][ ] read book"));
            assertTrue(displayedText.contains("2.[T][ ] return BOOK"));
            assertFalse(displayedText.contains("write report"));
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    void isExit_returnsFalse() {
        assertFalse(new FindCommand("book").isExit());
    }
}
