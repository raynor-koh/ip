package bob.command;

import bob.ResponseType;
import bob.storage.Storage;
import bob.task.TaskList;

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
     * @param tasks current task list.
     * @param storage task storage, which is unchanged.
     * @return farewell response.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.BYE;
    }

    /**
     * Indicates that this command ends the chatbot session.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
