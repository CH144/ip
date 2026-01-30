package johndoe.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import johndoe.gui.DialogBox;

public class Main extends Application {
    private static final String FILE_PATH = "./data/tasks.txt";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private JohnDoe johnDoe;

    @Override
    public void start(Stage stage) {
        johnDoe = new JohnDoe(FILE_PATH);

        stage.setTitle("JohnDoe");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(600.0);

        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        loadTasks();

        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(600, 770);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(dialogContainer);
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        userInput = new TextField();
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        userInput.setPrefWidth(525.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        sendButton = new Button("Send");
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        sendButton.setPrefWidth(55.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(600.0, 800.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void loadTasks() {
        String output = johnDoe.loadTasks();
        if (!output.equals("")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getJohnDoeBox(output)
            );
        }
    }

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
