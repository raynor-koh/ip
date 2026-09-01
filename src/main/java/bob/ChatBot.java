package bob;

import java.io.IOException;

import bob.command.Command;
import bob.exception.BobException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

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
     *
     * @throws BobException if saved tasks cannot be loaded.
     */
    public ChatBot() throws BobException {
        this("data/bob.txt");
    }

    /**
     * Creates a chatbot that persists tasks at the supplied path.
     *
     * @param filePath path of the file used to load and save tasks.
     * @throws BobException if saved tasks cannot be loaded.
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
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = parser.parse(fullCommand);
                String response = command.execute(tasks, storage);
                ui.showResponse(response);

                if (command.isExit()) {
                    break;
                }
            } catch (BobException | IOException exception) {
                ui.showError(exception.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }
}
