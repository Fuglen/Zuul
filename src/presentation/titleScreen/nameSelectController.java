package presentation.titleScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.Main;

import java.io.IOException;

public class nameSelectController extends Main {
    @FXML
    private Label label, nameLabel;
    @FXML
    private TextField textField;
    @FXML
    public void textFieldOutput(KeyEvent event) throws InterruptedException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            if (textField.getText().isEmpty()) {
                label.setText("Please enter a name");
            } else {
                label.setText("Hello " + textField.getText() + "\nWelcome to our game!");
                nameLabel.setText(null);
                stackPane.setVisible(true);
            }
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
    public void begin(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
        start.setResizable(true);
    }
}
