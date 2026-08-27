package bob.command;

import java.util.List;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command that displays tasks with descriptions containing a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a command that searches task descriptions for the supplied keyword.
     *
     * @param keyword text to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.find(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
