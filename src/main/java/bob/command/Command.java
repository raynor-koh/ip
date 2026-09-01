package bob.command;

import java.io.IOException;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;

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
     * Applies this command and returns the resulting response.
     *
     * @param tasks task list on which the command operates.
     * @param storage storage used to persist task changes.
     * @return response to display to the user.
     * @throws BobException if the command cannot be applied.
     * @throws IOException if updated tasks cannot be saved.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws BobException, IOException;

    /**
     * Returns whether this command should end the chatbot session.
     *
     * @return true if the chatbot should exit.
     */
    public boolean isExit() {
        return false;
    }
}
