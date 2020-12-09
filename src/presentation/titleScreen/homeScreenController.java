package presentation.titleScreen;

import domain.Domain;
import interfaceI.DomainI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.Main;

import java.io.IOException;

public class homeScreenController extends Main {
    @FXML
    public void goBack(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("titleScreen.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }
    @FXML
    private ProgressBar progressBar;
    @FXML
    private CheckBox checkBox;
    @FXML
    public void toggleProgress() {
        if (checkBox.isSelected()) {
            progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        } else {
            progressBar.setProgress(0);
        }
    }
    @FXML
    private StackPane stackPane;
    @FXML
    public void hover() {
        if (stackPane.getOpacity() == 1) {
            stackPane.setOpacity(0.75);
        } else {
            stackPane.setOpacity(1);
        }
    }

    @FXML
    public void goRoad(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("road.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();;
        domainI.load();
    }


    DomainI domainI = new Domain("dataFile.txt");
    @FXML
    private void addItem() {
        domainI.addItem("Plastic");
    }

}