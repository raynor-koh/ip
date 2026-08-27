package bob.command;

import java.io.IOException;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command that removes a task from the list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a command for the specified one-based task number.
     *
     * @param taskNumber one-based number of the task to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Removes the selected task and persists the updated list.
     *
     * @param taskList task list to update.
     * @param ui user interface used to display confirmation.
     * @param storage storage used to persist the updated list.
     * @throws BobException if the task number does not identify a task.
     * @throws IOException if the updated task list cannot be saved.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobException, IOException {
        Task deletedTask = taskList.remove(taskNumber - 1);
        ui.showDeleted(deletedTask, taskList.getTaskCount());
        storage.save(taskList.getTasks());
    }
}
