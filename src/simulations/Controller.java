package simulations;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Controller {
    @FXML
    GridPane simulation;

    @FXML
    VBox logs;

    @FXML
    Label timeLabel;

    @FXML
    MenuItem loadairport;

    @FXML
    MenuItem loadflight;


    private Render render = new Render();
    private Logs logger = new Logs();
    private List<String> worldMap = new ArrayList<>(1800);
    private List<String> airports = new ArrayList<>();
    private List<String> flights = new ArrayList<>();
    private List<Point> airportsPoints = new ArrayList<>();

    @FXML
    public void draw() {
    }

    @FXML
    public void loadMaps() throws IOException {
        worldMap = loadFile();
        if(worldMap.size()>0) loadairport.setDisable(false);
        else return;

        render.drawMap(simulation, worldMap);
    }


    @FXML
    public void loadAirports() throws IOException {
        airports = loadFile();
        if(airports.size()>0) loadflight.setDisable(false);
        else return;
        render.drawAirports(simulation, airports);
    }


    @FXML
    public void loadFlights() throws IOException {
        flights = loadFile();
        draw();

    }


    /**
     * @return
     * @throws IOException
     */

    @FXML
    public List<String> loadFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        List<String> tmp = new ArrayList<>();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(null);
        String name = file.getName();

        if (file != null) {
            String fullPath = file.getCanonicalPath();
            Path path = Paths.get(fullPath);
            Stream<String> lines = Files.lines(path);
            lines.forEach(tmp::add);
            logger.addLog(logs, "Load: " + name + " successfully");
        }
        return tmp;
    }

}
