import java.util.Scanner;

/**
 * Coordinates the chatbot's input-processing loop.
 */
public class ChatBot {
    private final Ui ui;
    private final Parser parser;

    /**
     * Creates a chatbot with its user interface and input parser.
     */
    public ChatBot() {
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Runs the chatbot until the user enters an exit command.
     */
    public void run() {
        ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Command command = parser.parse(input);

            ui.showDivider();
            command.execute(ui);
            ui.showDivider();

            if (command.isExit()) {
                break;
            }
        }
        scanner.close();
    }
}
