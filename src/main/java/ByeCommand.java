public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
