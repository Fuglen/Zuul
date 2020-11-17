package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import worldofzuul.Game;

public class MainController {
    @FXML
    private void StartGame () {
        Game game = new Game();
        game.play();
    }
    @FXML
    private void EndGame () {
        Platform.exit();
    }
}