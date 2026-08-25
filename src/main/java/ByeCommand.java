public class ByeCommand extends Command {
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showGoodbye();
        return false;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
