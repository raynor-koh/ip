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
    private static final double MIN_WINDOW_WIDTH = 440.0;
    private static final double MIN_WINDOW_HEIGHT = 420.0;

    @Override
    public void start(Stage stage) throws IOException, BobException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML));
        Parent root = fxmlLoader.load();

        MainWindow controller = fxmlLoader.getController();
        controller.setChatBot(new ChatBot("data/bob.txt"));

        stage.setTitle("Bob - Task Assistant");
        stage.setScene(new Scene(root));
        stage.setMinWidth(MIN_WINDOW_WIDTH);
        stage.setMinHeight(MIN_WINDOW_HEIGHT);
        stage.show();
    }
}
