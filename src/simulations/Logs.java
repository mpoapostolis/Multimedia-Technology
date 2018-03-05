package simulations;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Logs {

    /**
     * @param vbox
     * @param text
     */

    public void addLog(VBox vbox, String text) {
        Text t = new Text(text);
        t.setWrappingWidth(175);
        vbox.getChildren().add(t);
    }
}
