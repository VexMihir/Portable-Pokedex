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


//Represent a class containing methods, fields and scenes associated with WelcomeScreenFXML
public class WelcomeScreenController {

    @FXML private Button srchPokemon;
    @FXML private Button rndPokemon;
    @FXML private Button trainer;
    @FXML private Button exit;
    @FXML private AnchorPane bg;

    // EFFECTS: button press views the scene to search for pokemon
    // Read the class/function for function documentation
    public void searchPokemon(MouseEvent event) throws IOException {
        String file = "SearchPokemon.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: button press views the scene that lets the user choose between a new or existing trainer
    // Read the class/function for function documentation
    public void trainerOptions(MouseEvent event) throws IOException {
        String file = "TrOptions.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: button press views the scene that shows a random pokemon's details
    // Read the class/function for function documentation
    public void random(MouseEvent event) throws IOException {
        String file = "RandomPokemon.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: displays the exit screen
    // Read the class/function for function documentation
    public void exit(MouseEvent event) throws IOException, InterruptedException {

        ExitButton exitButton = new ExitButton();
        exitButton.exitPress(event);

    }

}
