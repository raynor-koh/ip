import java.io.IOException;

/**
 * Command that removes a task from the list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobException, IOException {
        Task deletedTask = taskList.remove(taskNumber - 1);
        ui.showDeleted(deletedTask, taskList.getTaskCount());
        storage.save(taskList.getTasks());
    }
}
