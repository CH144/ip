package johndoe.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import johndoe.gui.MainWindow;

/**
 * GUI for the {@code JohnDoe} application.
 */
public class Main extends Application {
    private final JohnDoe johnDoe = new JohnDoe("./data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setJohnDoe(johnDoe);
            stage.setTitle("JohnDoe");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
