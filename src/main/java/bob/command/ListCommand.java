package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command that displays all stored tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a command that displays the task list.
     */
    public ListCommand() {
    }

    /**
     * Displays every task in the current task list.
     *
     * @param tasks task list to display.
     * @param ui user interface used to display the list.
     * @param storage task storage, which is unchanged.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTasks(), tasks.getTaskCount());
    }
}
