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
     * Executes this command against the supplied task list.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobException, IOException;

    /**
     * Returns whether this command ends the chatbot session.
     */
    public boolean isExit() {
        return false;
    }
}
