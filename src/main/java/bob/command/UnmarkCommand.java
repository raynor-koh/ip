package bob.command;

import java.io.IOException;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a command for the specified one-based task number.
     *
     * @param taskNumber one-based number of the task to unmark.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the selected task as not done and persists the updated list.
     *
     * @param taskList task list to update.
     * @param ui user interface used to display confirmation.
     * @param storage storage used to persist the updated list.
     * @throws BobException if the task number does not identify a task.
     * @throws IOException if the updated task list cannot be saved.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobException, IOException {
        Task task = taskList.get(taskNumber - 1);
        task.markAsNotDone();
        ui.showUnmarked(task);
        storage.save(taskList.getTasks());
    }
}
