package johndoe.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import johndoe.app.JohnDoe;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private JohnDoe johnDoe;


    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the {@code JohnDoe} object.
     * Also loads existing tasks from file path stored in {@code JohnDoe}.
     */
    public void setJohnDoe(JohnDoe j) {
        johnDoe = j;
        String output = johnDoe.loadTasks();
        dialogContainer.getChildren().addAll(
                DialogBox.getJohnDoeBox(output)
        );
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String output = johnDoe.processUserInput(input);
        if (output.equals("bye")) {
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserBox(input),
                DialogBox.getJohnDoeBox(output)
        );
        userInput.clear();
    }
}
