package presentation.titleScreen;

import interfaceI.DomainI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.Main;

import java.io.IOException;

public class roadController extends Main {

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

    @FXML
    public void goHomev2() throws IOException {
        changeRooms("home", "homeScreen.fxml");
    }
}
