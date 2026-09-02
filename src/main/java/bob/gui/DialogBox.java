package bob.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Locale;

import bob.ResponseType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Displays one chatbot message together with the speaker's avatar.
 */
public class DialogBox extends HBox {
    private static final String DIALOG_BOX_FXML = "/view/DialogBox.fxml";

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource(DIALOG_BOX_FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load the dialog box layout.", exception);
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Creates a dialog displayed on the user's side.
     *
     * @param text message to display.
     * @param image user avatar.
     * @return user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Creates a dialog displayed on Bob's side.
     *
     * @param text message to display.
     * @param image Bob's avatar.
     * @param responseType semantic type used to style the response.
     * @return Bob dialog box.
     */
    public static DialogBox getBobDialog(String text, Image image, ResponseType responseType) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        dialogBox.applyResponseStyle(responseType);
        return dialogBox;
    }

    private void applyResponseStyle(ResponseType responseType) {
        String styleClass = "response-" + responseType.name().toLowerCase(Locale.ROOT);
        dialog.getStyleClass().add(styleClass);
    }

    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        Collections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }
}
