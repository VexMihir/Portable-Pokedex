package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// Represents a class that contains methods called when the back button is pressed in any GUI panel
public class BackButton {

    //REQUIRES: File passed is an actual FXML file
    //MODIFIES: Changes the window/scene to the FXML file passed
    //EFFECTS: Changes the scene to show the FXML file
    public void backPress(String file, MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource(file));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }
}
