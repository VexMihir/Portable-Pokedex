package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

// Represents a class that contains methods called when the exit button is pressed in any GUI panel
public class ExitButton {


    //MODIFIES: Changes the window/scene to the LoadingOut FXML file
    //EFFECTS: Changes the scene to show LoadingOut screen (exit screen)
    public  void exitPress(MouseEvent event) throws IOException, InterruptedException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoadingOut.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.initStyle(StageStyle.UNDECORATED);
        window.show();

        Thread.sleep(500);
    }
}
