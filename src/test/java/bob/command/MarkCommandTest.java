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

/** Tests marking a task as done through a {@link MarkCommand}. */
class MarkCommandTest {
    @TempDir
    private Path tempDirectory;

    @Test
    void execute_validOneBasedTaskNumber_marksAndSavesTask() throws BobException, IOException {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));
        Storage storage = new Storage(tempDirectory.resolve("tasks.txt").toString());

        new MarkCommand(1).execute(taskList, new Ui(), storage);

        assertEquals(TaskStatus.DONE, taskList.get(0).getStatus());
        assertEquals(TaskStatus.DONE, storage.load().get(0).getStatus());
    }

    @Test
    void execute_nonexistentTaskNumber_bobExceptionThrown() {
        TaskList taskList = new TaskList(List.of(new ToDo("read book")));
        Storage storage = new Storage(tempDirectory.resolve("tasks.txt").toString());

        assertThrows(BobException.class, () -> new MarkCommand(2).execute(taskList, new Ui(), storage));
    }
}
