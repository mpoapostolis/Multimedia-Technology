package simulations;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private final double periodMillis = 200;
    @FXML
    GridPane simulation;
    @FXML
    VBox logs;
    @FXML
    Label timeLabel;
    @FXML
    Label totalAircraft;
    @FXML
    Label landings;
    @FXML
    Label collisions;

    @FXML
    MenuItem Start;
    @FXML
    MenuItem Stop;


    private Render render = new Render();
    private Logs logger = new Logs();
    private List<String> worldMap = new ArrayList<>(1800);
    private List<Airport> airports = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();
    private double time;
    private int totalAirplanes = 0;
    private int collides = 0;
    private int reached = 0;


    Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(periodMillis),
            ae -> draw()));

    @FXML
    public void draw() {
        time += periodMillis / 1000;
        double simMinutes = time / 5;
        flights.forEach(flight -> {
            simulation.getChildren().remove(flight.getAirplane().getImgView());
            if (simMinutes > flight.getTime())
                flight.getAirplane().move();

            // check Colides
            flights.stream().forEach(f -> {
                if (f.getAirplane() != flight.getAirplane()
                        && f.getAirplane().getX() == flight.getAirplane().getX()
                        && f.getAirplane().getY() == flight.getAirplane().getY()) {
                    flight.getAirplane().setCollide(true);
                    collides++;
                }
            });
        });

        flights.stream().forEach(f -> {
            if (f.getAirplane().isReachedDestination()) reached++;
        });

        flights = flights.stream()
                .filter(f ->
                        !(f.getAirplane().isReachedDestination() &&
                                !f.getAirplane().isCrashed() &&
                                !f.getAirplane().isCollide())).collect(Collectors.toList());

        render.drawAirplanes(simulation, flights);
        totalAirplanes = flights.size();
        if (totalAirplanes > 0) {
            timeLabel.setText(String.format("Simulated Time %.2f min", simMinutes));
        }
        totalAircraft.setText("Total Aircrafts: " + totalAirplanes);
        landings.setText("Landings: " + reached);
        collisions.setText("Collisions: " + collides);
    }

    @FXML
    public void load() {
        String mapId = simulations.Popup.display("test");
        String mapStr = "resources/world_" + mapId + ".txt";
        String airStr = "resources/airports_" + mapId + ".txt";
        String flStr = "resources/flights_" + mapId + ".txt";
        loadMaps(mapStr);
        loadAirports(airStr);
        loadFlights(flStr);
        Start.setDisable(false);
        Stop.setDisable(false);
    }

    @FXML
    public void loadMaps(String str) {
        worldMap = loadFile(str);
        render.drawMap(simulation, worldMap);
    }

    @FXML
    public void stop() {
        timeline.stop();
        flights.clear();
        timeLabel.setText("Simulated Time: ");
        totalAircraft.setText("Total Aircraft: ");
        landings.setText("Collisions: ");
        collisions.setText("Landings: ");
        load();
        logs.getChildren().clear();
    }

    @FXML
    public void start() throws InterruptedException {
        flights.forEach(System.out::print);
        flights = flights.stream().filter(f -> f.isValid(airports)).collect(Collectors.toList());
        logger.addLog(logs, "Start successfully");
        flights.forEach(System.out::print);
        time = 0;
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void exit() {
        Platform.exit();
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
