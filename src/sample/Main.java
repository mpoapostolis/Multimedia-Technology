package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    public double WIDTH = primaryScreenBounds.getWidth() / 1.2;
    public double HEIGHT = primaryScreenBounds.getHeight() / 1.2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Simulator");
        primaryStage.setScene(new Scene(root,WIDTH, HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
