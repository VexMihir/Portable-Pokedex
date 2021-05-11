package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

//Represent a class containing methods, fields and scenes associated with LoadingOutFXML
public class LoadingOutController {

    @FXML
    private Button cross;

    // EFFECTS: exits the program
    public void closeProgram(javafx.scene.input.MouseEvent event) {
        System.exit(0);
    }
}
