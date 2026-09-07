package bob.command;

import java.io.IOException;
import java.util.List;

import bob.ResponseType;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Task;
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
     * Returns the semantic type of the response produced by this command.
     *
     * @return response type for presentation purposes.
     */
    public abstract ResponseType getResponseType();

    /**
     * Returns whether this command should end the chatbot session.
     *
     * @return true if the chatbot should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Formats tasks as a numbered response under the supplied heading.
     *
     * @param heading response heading.
     * @param tasks tasks to include in the response.
     * @return formatted response containing the numbered tasks.
     */
    protected String formatTasks(String heading, List<Task> tasks) {
        StringBuilder response = new StringBuilder(heading);

        for (int i = 0; i < tasks.size(); i++) {
            response.append('\n')
                    .append(i + 1)
                    .append('.')
                    .append(tasks.get(i));
        }

        return response.toString();
    }
}
