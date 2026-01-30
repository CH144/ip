package johndoe.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Encapsulates the user input and {@code JohnDoe} output to be displayed.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    private DialogBox(String text) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }

    /**
     * Returns a new {@code DialogBox}.
     * Aligned right for the user.
     */
    public static DialogBox getUserBox(String s) {
        DialogBox d = new DialogBox(s);
        d.setAlignment(Pos.TOP_RIGHT);
        return d;
    }

    /**
     * Returns a new {@code DialogBox}.
     * Aligned left for {@code JohnDoe}.
     */
    public static DialogBox getJohnDoeBox(String s) {
        DialogBox d = new DialogBox(s);
        d.setAlignment(Pos.TOP_LEFT);
        return d;
    }
}
