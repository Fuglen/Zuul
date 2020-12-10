package presentation.titleScreen;

<<<<<<< HEAD
import domain.Domain;
import interfaceI.DomainI;
=======
import domain.Command;
import domain.CommandWord;
import domain.Item;
>>>>>>> 59b13ea465bdb17651ad3d5753ee4feb57c25234
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Stage start = (Stage) ((Node) event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }

    @FXML
<<<<<<< HEAD
    private StackPane roadButton;
=======
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

>>>>>>> 59b13ea465bdb17651ad3d5753ee4feb57c25234
    @FXML
    public void hover() {
        if (roadButton.getOpacity() == 1) {
            roadButton.setOpacity(0.75);
        } else {
            roadButton.setOpacity(1);
        }
    }
<<<<<<< HEAD
=======

    public void collectShoes(){
        getGame().collectItem(new Command(CommandWord.COLLECT, "shoes"));
        shoes.setVisible(false);
        Item item = new Item("shoes");

    }

>>>>>>> 59b13ea465bdb17651ad3d5753ee4feb57c25234
    @FXML
    public void goRoad(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("road.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage) ((Node) event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
<<<<<<< HEAD
        domainI.load();
    }

    DomainI domainI = new Domain("dataFile.txt");

    private boolean sceneLoaded = false;
    @FXML
    private void loadSceneData() {
        if (!sceneLoaded) {
            domainI.load();
            sceneLoaded = true;
        }
    }
    @FXML
    private void addItem() {
        domainI.addItem("Plastic");
        domainI.store();
    }
    @FXML
    private void printInventory() {
        domainI.printInventory();
=======
    }

    @FXML
    public void goRoadv2() throws IOException {
        changeRooms("road", "road.fxml");
>>>>>>> 59b13ea465bdb17651ad3d5753ee4feb57c25234
    }
}