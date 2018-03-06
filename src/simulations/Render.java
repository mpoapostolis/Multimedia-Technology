package simulations;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class Render {


    /**
     * @param simulation
     * @param airports
     */

    public void drawAirports(GridPane simulation, List<String> airports) {
        for (int i = 0; i < airports.size(); i++) {
            String imagePath = "resources/airport.png";
            String[] row = airports.get(i).split(",");
            int x = Integer.parseInt(row[1])-1;
            int y = Integer.parseInt(row[2])-1;
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.minHeight(16);
            imageView.minWidth(16);
            simulation.add(imageView, x, y);
        }
    }

    /**
     * @param worldMap
     * @param simulation
     */

    public void drawMap(GridPane simulation, List<String> worldMap) {
        String color;
        int listSize = worldMap.size();
        int altitude;
        double Width = 16;
        double Height = 16;
        Pane p = new Pane();

        for (int i = 0; i < listSize; i++) {
            String[] lineArr = worldMap.get(i).split(",");
            for (int j = 0; j < 60; j++) {
                altitude = Integer.parseInt(lineArr[j]);
                color = getColor(altitude);
                Pane sp = new Pane();
                sp.setMinSize(Width, Height);
                sp.setStyle(String.format("-fx-background-color: %s", color));
                sp.getChildren().addAll(p);
                simulation.add(sp, j, i);
            }
        }
    }


    /**
     * @param altitude
     * @return HexColor
     */
    private String getColor(int altitude) {
        if (altitude == 0) {
            return "rgb(0,0,255)";
        } else if (altitude < 200) return "rgb(60,179,113)";
        else if (altitude < 400) {
            return "rgb(46,139,87)";
        } else if (altitude < 700) {
            return "rgb(34,139,34)";
        } else if (altitude < 1500) {
            return "rgb(222,184,135)";
        } else if (altitude < 3500) {
            return "rgb(205,133,63)";
        } else {
            return "rgb(145, 80, 20)";
        }
    }

}
