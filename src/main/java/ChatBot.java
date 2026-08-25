import java.io.IOException;

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
            this.tasks = new TaskList(storage.load());
        } catch (IOException exception) {
            throw new BobException("I could not load your saved tasks");
        }
    }

    /**
     * Runs the chatbot until the user enters an exit command.
     */
    public void run() {
        ui.showWelcome();

        while (ui.hasNextLine()) {
            ui.showDivider();
            try {
                String input = ui.readLine();
                Command command = parser.parse(input);
                command.execute(tasks, ui, storage);

                if (command.isExit()) {
                    break;
                }
            } catch (BobException | IOException exception) {
                ui.showError(exception.getMessage());
            }
            ui.showDivider();
        }
        ui.close();
    }
}
