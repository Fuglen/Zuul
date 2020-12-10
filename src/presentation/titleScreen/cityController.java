package presentation.titleScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class cityController {
    @FXML
    public void goRoad(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("road.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }
    @FXML
    public void goWork(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("work.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }
    @FXML
    public void goMcdonalds(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("mcdonalds.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }
    @FXML
    public void goPark(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("park.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }
}
