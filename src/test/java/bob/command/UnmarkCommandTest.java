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
import bob.task.TaskStatus;
import bob.task.ToDo;
import bob.ui.Ui;

/** Tests marking a task as not done through an {@link UnmarkCommand}. */
class UnmarkCommandTest {
    @TempDir
    private Path tempDirectory;

    @Test
    void execute_validOneBasedTaskNumber_unmarksAndSavesTask() throws BobException, IOException {
        ToDo task = new ToDo("read book");
        task.markAsDone();
        TaskList taskList = new TaskList(List.of(task));
        Storage storage = new Storage(tempDirectory.resolve("tasks.txt").toString());

        new UnmarkCommand(1).execute(taskList, new Ui(), storage);

        assertEquals(TaskStatus.NOT_DONE, taskList.get(0).getStatus());
        assertEquals(TaskStatus.NOT_DONE, storage.load().get(0).getStatus());
    }

    @Test
    void execute_nonexistentTaskNumber_bobExceptionThrown() {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));
        Storage storage = new Storage(tempDirectory.resolve("tasks.txt").toString());

        assertThrows(BobException.class, () -> new UnmarkCommand(2).execute(taskList, new Ui(), storage));
    }
}
