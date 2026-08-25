/**
 * Command that removes a task from the list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) throws BobException {
        Task deletedTask = taskList.remove(taskNumber - 1);
        ui.showDeleted(deletedTask, taskList.getTaskCount());
        return true;
    }
}
