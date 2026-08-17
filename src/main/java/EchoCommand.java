public class EchoCommand extends Command {
    private final String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Ui ui) {
        ui.showEcho(message);
    }
}
