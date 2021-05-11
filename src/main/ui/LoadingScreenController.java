package ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//Represent a class containing methods, fields and scenes associated with LoadingScreenFXML
public class LoadingScreenController {

    @FXML
    private AnchorPane pane;


    @FXML
    private Button jumpIN;

    // EFFECTS: button press views the main menu scene
    // Read the class/function for function documentation
    public void jumpin(MouseEvent event) throws IOException {
        String file = "WelcomeScreen.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);

    }

}