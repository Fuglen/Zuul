package sample;

import interfaceI.DomainI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.effect.ColorAdjust;
import worldofzuul.Domain;

import java.io.IOException;

public class MainController {
    DomainI domainI = new Domain();
    @FXML
    private void EndGame () {
        Platform.exit();
    }
    @FXML
    private void goMain() throws IOException {
        Stage homeStage = FXMLLoader.load(getClass().getResource("home.fxml"));
        homeStage.show();
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