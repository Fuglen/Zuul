package presentation.titleScreen;

import domain.Domain;
import interfaceI.DomainI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class titleScreenController {
    DomainI domainI = new Domain();

    @FXML
    private void EndGame () {
        Platform.exit();
    }

    @FXML
    public void startGame(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("gameStart.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }

    @FXML
    private void addItem() {
        domainI.addItem("Plastic");
    }
    @FXML
    private void printInventory() {
        domainI.printInventory();
    }

}
