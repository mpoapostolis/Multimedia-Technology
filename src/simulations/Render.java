package simulations;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class Render {


    /**
     * @param simulation
     * @param airports
     */


    public void drawAirplanes(GridPane simulation, List<Flight> flights) {
        for (Flight flight : flights) {
            Airplane plane = flight.getAirplane();
            simulation.add(plane.getImgView(), plane.getX(), plane.getY());
        }
    }


    public void drawAirports(GridPane simulation, List<Airport> airports) {
        for (Airport airport : airports) {
            simulation.add(airport.getImgView(), airport.getX(), airport.getY());
        }
    }

    /**
     * @param worldMap
     * @param simulation
     */

    public void drawMap(GridPane simulation, List<String> worldMap) {
        String color;
        int listSize = worldMap.size();
        int colorNumber;
        double Width = 16;
        double Height = 16;

        for (int i = 0; i < listSize; i++) {
            String[] lineArr = worldMap.get(i).split(",");
            for (int j = 0; j < 60; j++) {
                colorNumber = Integer.parseInt(lineArr[j]);
                color = getColor(colorNumber);
                Pane pane = new Pane();
                pane.setMinSize(Width, Height);
                pane.setStyle(String.format("-fx-background-color: %s", color));
                simulation.add(pane, j, i);
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
