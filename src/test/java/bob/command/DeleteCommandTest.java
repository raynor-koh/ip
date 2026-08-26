package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.task.ToDo;
import bob.ui.Ui;

/** Tests removing a task through a {@link DeleteCommand}. */
class DeleteCommandTest {
    @TempDir
    private Path tempDirectory;

    @Test
    void execute_validOneBasedTaskNumber_removesAndSavesTask() throws BobException, IOException {
        TaskList taskList = new TaskList(List.of(new ToDo("first task"), new ToDo("second task")));
        Storage storage = new Storage(tempDirectory.resolve("tasks.txt").toString());

        new DeleteCommand(1).execute(taskList, new Ui(), storage);

        assertEquals(1, taskList.getTaskCount());
        assertEquals("second task", taskList.get(0).getDescription());
        assertEquals("second task", storage.load().get(0).getDescription());
    }

    @Test
    void execute_nonexistentTaskNumber_bobExceptionThrown() {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));
        Storage storage = new Storage(tempDirectory.resolve("tasks.txt").toString());

        assertThrows(BobException.class, () -> new DeleteCommand(2).execute(taskList, new Ui(), storage));
    }
}
