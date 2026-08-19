import java.util.Scanner;

/**
 * Coordinates the chatbot's input-processing loop.
 */
public class ChatBot {
    private final Ui ui;
    private final Parser parser;
    private final TaskList tasks;

    /**
     * Creates a chatbot with its user interface and input parser.
     */
    public ChatBot() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList();
    }

    /**
     * Runs the chatbot until the user enters an exit command.
     */
    public void run() {
        ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            ui.showDivider();
            try {
                String input = scanner.nextLine();
                Command command = parser.parse(input);
                command.execute(ui, tasks);

                if (command.isExit()) {
                    break;
                }
            } catch (BobException exception) {
                ui.showError(exception.getMessage());
            }
            ui.showDivider();
        }
        scanner.close();
    }
}
