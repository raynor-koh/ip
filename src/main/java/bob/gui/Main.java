package bob.gui;

import java.io.IOException;

import bob.ChatBot;
import bob.exception.BobException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Configures and displays the JavaFX interface for Bob.
 */
public class Main extends Application {
    private static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";

    @Override
    public void start(Stage stage) throws IOException, BobException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML));
        Parent root = fxmlLoader.load();

        MainWindow controller = fxmlLoader.getController();
        controller.setChatBot(new ChatBot());

        stage.setTitle("Bob");
        stage.setScene(new Scene(root));
        stage.setMinWidth(417);
        stage.setMinHeight(300);
        stage.show();
    }
}
