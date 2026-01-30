package johndoe.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;

    private DialogBox(String s) {
        text = new Label(s);
        text.setWrapText(true);
        this.getChildren().addAll(text);
    }

    public static DialogBox getUserBox(String s) {
        DialogBox d = new DialogBox(s);
        d.setAlignment(Pos.TOP_RIGHT);
        return d;
    }

    public static DialogBox getJohnDoeBox(String s) {
        DialogBox d = new DialogBox(s);
        d.setAlignment(Pos.TOP_LEFT);
        return d;
    }
}
