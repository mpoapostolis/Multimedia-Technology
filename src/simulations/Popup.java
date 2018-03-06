package simulations;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
    static String textId;

    public static String display(String title) {
        Stage window = new Stage();
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinWidth(100);

        HBox layout = new HBox(10);
        TextField textField = new TextField();
        Button button = new Button("Click to get text");

        layout.getChildren().addAll(textField, button);
        layout.setAlignment(Pos.CENTER);
        Stage finalWindow = window;

        button.setOnAction( e -> {
            textId = textField.getText();
            finalWindow.close();
        });

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return  textId;
    }

}
