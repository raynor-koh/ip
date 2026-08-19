/**
 * Command that marks a task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws BobException {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        ui.showMarked(task);
    }
}
