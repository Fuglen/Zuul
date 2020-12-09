package presentation.titleScreen;

import domain.Domain;
import interfaceI.DomainI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class roadController {
    DomainI domainI = new Domain("dataFile.txt");

    private boolean sceneLoaded = false;
 /*   public boolean getSceneLoaded() {
        return sceneLoaded;
    }
    public void setSceneLoaded(boolean sceneLoaded) {
        this.sceneLoaded = sceneLoaded;
    }*/
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
    }

    @FXML
    public void goForest(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("forest.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }

    @FXML
    public void goCity(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("city.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }

    @FXML
    public void goBeach(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("beach.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }

    @FXML
    public void goRecyclingHome(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("recyclingHome.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }

    @FXML
    public void goHome(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }
}
