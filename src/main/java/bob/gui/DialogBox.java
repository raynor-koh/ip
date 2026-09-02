package bob.gui;

import java.io.IOException;
import java.util.Collections;

import bob.ResponseType;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

/**
 * Displays one chatbot message together with the speaker's avatar.
 */
public class DialogBox extends HBox {
    private static final String DIALOG_BOX_FXML = "/view/DialogBox.fxml";
    private static final double AVATAR_SIZE = 48.0;
    private static final Duration ENTRANCE_DURATION = Duration.millis(180);

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
        AvatarView.configure(displayPicture, image, AVATAR_SIZE);
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

    /**
     * Creates the temporary dialog displayed while Bob prepares a response.
     *
     * @param text initial typing-indicator text.
     * @param image Bob's avatar.
     * @return Bob typing dialog box.
     */
    public static DialogBox getTypingDialog(String text, Image image) {
        DialogBox dialogBox = getBobDialog(text, image, ResponseType.INFO);
        dialogBox.dialog.getStyleClass().add("typing-label");
        return dialogBox;
    }

    void setDialogText(String text) {
        dialog.setText(text);
    }

    void playEntranceAnimation() {
        double startingOffset = getAlignment() == Pos.TOP_LEFT ? -12.0 : 12.0;
        setOpacity(0.0);
        setTranslateX(startingOffset);

        FadeTransition fade = new FadeTransition(ENTRANCE_DURATION, this);
        fade.setToValue(1.0);
        TranslateTransition slide = new TranslateTransition(ENTRANCE_DURATION, this);
        slide.setToX(0.0);

        new ParallelTransition(fade, slide).play();
    }

    private void applyResponseStyle(ResponseType responseType) {
        dialog.getStyleClass().add(responseType.getStyleClass());
    }

    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        Collections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }
}
