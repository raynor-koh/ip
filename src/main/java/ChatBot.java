import java.io.IOException;
import java.util.Scanner;

/**
 * Coordinates the chatbot's input-processing loop.
 */
public class ChatBot {
    private final Ui ui;
    private final Parser parser;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Creates a chatbot with its user interface and input parser.
     */
    public ChatBot() throws BobException {
        this("data/bob.txt");
    }

    /**
     * Creates a chatbot that persists tasks at the supplied path.
     *
     * @param filePath path of the file used to load and save tasks
     */
    public ChatBot(String filePath) throws BobException {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);

        // Load once when the chatbot starts
        try {
            this.tasks = storage.load();
        } catch (IOException exception) {
            throw new BobException("I could not load your saved tasks");
        }
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
                boolean changed = command.execute(ui, tasks);

                if (changed) {
                    storage.save(tasks);
                }

                if (command.isExit()) {
                    break;
                }
            } catch (BobException | IOException exception) {
                ui.showError(exception.getMessage());
            }
            ui.showDivider();
        }
        scanner.close();
    }
}
