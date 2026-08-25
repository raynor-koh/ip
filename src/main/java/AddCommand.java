public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) throws BobException {
        tasks.add(task);
        ui.showAdded(task, tasks.getTaskCount());
        return true;
    }
}
