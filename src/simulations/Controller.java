package simulations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

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
    @FXML
    GridPane simulation;

    @FXML
    VBox logs;

    @FXML
    Label timeLabel;


    private Map map = new Map();
    private Logs logger = new Logs();
    private List<String> myList = new ArrayList<>();

    @FXML
    public void draw() {
        map.draw(simulation, myList);
    }

    /**
     *
     * @param event
     * @throws IOException
     *
     */

    @FXML
    public void chooseFile(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();

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
            lines.forEach(line -> Arrays.asList(line.split(",")).forEach(myList::add));
            draw();

            logger.addLog(logs, "Load: " + name  + " successfully");

        }
    }

}
