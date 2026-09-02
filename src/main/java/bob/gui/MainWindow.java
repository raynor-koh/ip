package bob.gui;

import java.io.InputStream;
import java.util.Objects;

import bob.ChatBot;
import bob.ChatResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controls the main chatbot window.
 */
public class MainWindow {
    private static final String USER_IMAGE_PATH = "/images/huahua.png";
    private static final String BOB_IMAGE_PATH = "/images/yuai.png";

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private final Image userImage = loadImage(USER_IMAGE_PATH);
    private final Image bobImage = loadImage(BOB_IMAGE_PATH);

    private ChatBot chatBot;

    @FXML
    private void initialize() {
        dialogContainer.heightProperty().addListener((observable, oldHeight, newHeight) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Supplies the chatbot used to process user input.
     *
     * @param chatBot chatbot backing this window.
     */
    public void setChatBot(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (input.isEmpty()) {
            return;
        }

        ChatResponse response = chatBot.processCommand(input);

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                                        DialogBox.getBobDialog(
                                                response.text(), bobImage, response.responseType()));

        userInput.clear();
        userInput.requestFocus();

        if (response.isExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }

    private static Image loadImage(String path) {
        InputStream imageStream = Objects.requireNonNull(MainWindow.class.getResourceAsStream(path),
                                        "Missing image resource: " + path);
        return new Image(imageStream);
    }
}
