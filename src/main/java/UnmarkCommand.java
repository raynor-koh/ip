import java.io.IOException;

/**
 * Command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobException, IOException {
        Task task = taskList.get(taskNumber - 1);
        task.markAsNotDone();
        ui.showUnmarked(task);
        storage.save(taskList.getTasks());
    }
}
