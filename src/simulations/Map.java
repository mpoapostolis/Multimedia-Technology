package simulations;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

public class Map {

    /**
     *
     * @param simulation
     * @param myList
     *
     */

    public void draw(GridPane simulation, List<String> myList) {
        simulation.getChildren().clear();
        String color = "#FFFFFF";
        int listSize = myList.size();
        int altitude = 0;
        double Width = 16;
        double Height = 16;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 60; j++) {
                if (listSize > 0) {
                    altitude = Integer.parseInt(myList.get((i * 30) + j));
                    color = getColor(altitude);
                }
                StackPane sp = new StackPane();
                sp.setPrefSize(Width, Height);
                sp.setStyle(String.format("-fx-background-color: %s", color));
                Label l = new Label(Integer.toString(altitude));
                sp.getChildren().addAll(l);
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
            return "#0000FF";
        } else if (altitude < 200) return "#3CB371";
        else if (altitude < 400) {
            return "#2E8B57";
        } else if (altitude < 700) {
            return "#228B22";
        } else if (altitude < 1500) {
            return "#DEB887";
        } else if (altitude < 3500) {
            return "#CD853F";
        } else {
            return "#915014";
        }
    }

}
