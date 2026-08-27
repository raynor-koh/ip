package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command that displays a farewell and ends the chatbot session.
 */
public class ByeCommand extends Command {
    /**
     * Creates a command that ends the chatbot session.
     */
    public ByeCommand() {
    }

    /**
     * Displays the farewell message.
     *
     * @param tasks current task list
     * @param ui user interface used to display the farewell
     * @param storage task storage, which is unchanged
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command ends the chatbot session.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
