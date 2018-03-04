package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Controller {

    private List<String> myList = new ArrayList<>();

    @FXML
    GridPane simulation;

    private String getColor(int x){
        if (x == 0){
            return "#0000FF";
        } else if (x < 200){
            return "#3CB371";
        } else if (x < 400){
            return "#2E8B57";
        } else if (x < 700){
            return "#228B22";
        }  else if (x < 1500){
            return "#DEB887";
        }  else if (x < 3500){
            return "#CD853F";
        }  else {
            return "#915014";
        }
    }

    @FXML
    public void draw() {
        simulation.getChildren().clear();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double Width = (primaryScreenBounds.getWidth()* 0.8) / 1.2  / 60;
        double Height = primaryScreenBounds.getHeight() / 1.2/ 30;
        String color = "#FFFFFF";
        int altitude = 0;
        for (int i=0; i<30; i++){
            for (int j =0; j<60; j++ ) {
                if(myList.size() > 0){
                    altitude = Integer.parseInt(myList.get((i*30)+j));
                    color = getColor(altitude);
                    System.out.println(color);
                }
                StackPane sp = new StackPane();
                sp.setPrefSize(Width, Height);
                sp.setStyle(String.format("-fx-background-color: %s", color));
                Label l = new Label(Integer.toString(altitude));
                sp.getChildren().addAll(l);
                simulation.add(sp,j,i);
            }
        }
    }

    @FXML
    public void initialize() {
        draw();
    }

        @FXML
    public void test (ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
          String fullPath = file.getCanonicalPath();
          Path path = Paths.get(fullPath);
          Stream <String> lines = Files.lines(path);
          lines.forEach(line -> Arrays.asList(line.split(",")).forEach(myList::add));
          draw();
        }
    }

}
