package simulations;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    @FXML
    GridPane simulation;

    @FXML
    VBox logs;

    @FXML
    Label timeLabel;

    @FXML
    MenuItem loadAirport;

    @FXML
    MenuItem loadFlight;


    private Render render = new Render();
    private Logs logger = new Logs();
    private List<String> worldMap = new ArrayList<>(1800);
    private List<Airport> airports = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();
    private double time;
    private final int periodMillis = 200;

    @FXML
    public void draw() {
        time += periodMillis/1000;
        double simMinutes = time/5;
        flights.forEach( flight -> {
            simulation.getChildren().remove(flight.getAirplane().getImgView());
            if (simMinutes > flight.getTime())
                flight.getAirplane().move();
        });
        flights = flights.stream().filter( f -> !(f.getAirplane().isReachedDestination())).collect(Collectors.toList());
        render.drawAirplanes(simulation, flights);
    }

    @FXML
    public void load() {
        String mapStr = "resources/world_default.txt";
        String airStr = "resources/airports_default.txt";
        String flStr = "resources/flights_default.txt";
        loadMaps(mapStr);
        loadAirports(airStr);
        loadFlights(flStr);
    }

    @FXML
    public void loadMaps(String str){
        worldMap = loadFile(str);
        render.drawMap(simulation, worldMap);
    }

    @FXML
    public void start() throws InterruptedException {
        flights.forEach(System.out::print);
        flights = flights.stream().filter(f -> f.isValid(airports)).collect(Collectors.toList());
        logger.addLog(logs, "Start successfully");
        flights.forEach(System.out::print);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(periodMillis),
                ae -> draw()));
        timeline.setCycleCount(Animation.INDEFINITE);
        time=0;
        timeline.play();
    }

    @FXML
    public void loadAirports(String str) {
        airports.clear();
        List<String> airportsInput = loadFile(str);
        for (int i = 0; i < airportsInput.size(); i++) {
            String[] row = airportsInput.get(i).split(",");
            Airport airport = new Airport(row);
            airports.add(airport);
        }


        render.drawAirports(simulation, airports);
    }


    @FXML
    public void loadFlights(String str) {
        flights.clear();
        List<String> flightsInput = loadFile(str);

        for (int i = 0; i < flightsInput.size(); i++) {
            String[] row = flightsInput.get(i).split(",");
            Flight flight = new Flight(row);
            flights.add(flight);
        }
    }


    /**
     * @return
     * @throws IOException
     */

    @FXML
    public List<String> loadFile(String str) {
        try {
            Path path = Paths.get(Controller.class.getClassLoader().getResource(str).toURI());
            Stream<String> lines = Files.lines(path);
            logger.addLog(logs, "Load: " + str + " successfully");
            return lines.collect(Collectors.toList());
        } catch (Exception e) {
            logger.addLog(logs, "Failed to load file: " + str);
            return Collections.emptyList();
        }
    }

}
