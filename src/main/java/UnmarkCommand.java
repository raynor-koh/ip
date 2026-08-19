/**
 * Command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws BobException {
        Task task = taskList.get(taskNumber - 1);
        task.markAsNotDone();
        ui.showUnmarked(task);
    }
}
