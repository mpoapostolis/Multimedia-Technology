package sample;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class Controller {
    @FXML
    GridPane simulation;

    @FXML
    public void initialize() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        double Width = primaryScreenBounds.getWidth() * 0.8 / 60;
        double Height = primaryScreenBounds.getHeight() / 30;
        for (int i=0; i<30; i++){
            for (int j =0; j<60; j++ ) {
                StackPane sp = new StackPane();
                sp.setPrefSize(Width, Height);
                Label l = new Label(Integer.toString(i));
                Rectangle border = new Rectangle(Width, Height);
                border.setFill(null);
                sp.getChildren().addAll(border,l);
                simulation.add(sp,j,i);
            }
        }
    }
}
