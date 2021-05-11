package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


//Represent a class containing methods, fields and scenes associated with TrOptionsFXML
public class TrOptionsController {

    @FXML
    private Button existingTr;
    @FXML
    private Button newTr;
    @FXML
    private Button back;
    @FXML
    private Button exit;

    // EFFECTS: button press views the scene with the options for an existing trainer
    // Read the class/function for function documentation
    public void existingTr(MouseEvent event) throws IOException {
        String file = "ExistingTr.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: button press views the scene with the options for a new trainer
    // Read the class/function for function documentation
    public void newTrainer(MouseEvent event) throws IOException {
        String file = "NewTrainer.fxml";
        BackButton backButton = new BackButton();
        backButton.backPress(file,event);
    }

    // EFFECTS: displays the exit screen
    // Read the class/function for function documentation
    public void exit(MouseEvent event) throws IOException, InterruptedException {

        ExitButton exitButton = new ExitButton();
        exitButton.exitPress(event);

    }

    // EFFECTS: displays the previous screen
    // Read the class/function for function documentation
    public void goBack(MouseEvent event) throws IOException {
        String file = "ExistingTr.fxml";
        BackButton backButton = new BackButton();
        backButton.backPress(file,event);
    }
}
