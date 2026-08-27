package bob.command;

import java.io.IOException;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Represents an executable user command.
 */
public abstract class Command {

    /**
     * Creates a command.
     */
    protected Command() {
    }

    /**
     * Applies this command to the task list and displays its result.
     *
     * @param tasks task list on which the command operates
     * @param ui user interface used to display feedback
     * @param storage storage used to persist task changes
     * @throws BobException if the command cannot be applied to the task list
     * @throws IOException if updated tasks cannot be saved
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobException, IOException;

    /**
     * Returns whether this command should end the chatbot session.
     *
     * @return true if the chatbot should exit
     */
    public boolean isExit() {
        return false;
    }
}
