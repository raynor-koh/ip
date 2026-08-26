package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.ToDo;
import bob.ui.Ui;

/** Tests adding a task through an {@link AddCommand}. */
class AddCommandTest {
    @TempDir
    private Path tempDirectory;

    @Test
    void execute_validTask_addsAndSavesTask() throws BobException, IOException {
        Task task = new ToDo("read book");
        TaskList taskList = new TaskList();
        Storage storage = new Storage(tempDirectory.resolve("tasks.txt").toString());

        new AddCommand(task).execute(taskList, new Ui(), storage);

        assertEquals(1, taskList.getTaskCount());
        assertSame(task, taskList.get(0));
        assertEquals("read book", storage.load().get(0).getDescription());
    }
}
