package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import kanto.exceptions.AllBadges;
import kanto.json.Read;
import kanto.json.Write;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;

import java.io.IOException;

//Represent a class containing methods, fields and scenes associated with ExistingTrControllerFXML
public class ExistingTrController {

    @FXML
    private Button details;
    @FXML
    private Button exit;
    @FXML
    private Button back;
    @FXML
    private Button catchNew;
    @FXML
    private Button release;
    @FXML
    private Button badge;
    @FXML
    private Button pokemon;
    @FXML
    private TextField prompt;

    Pokedex pokedex;
    Read read;
    Write write;
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";

    public ExistingTrController() throws IOException, ParseException {
        pokedex = new Pokedex();
        read = new Read();
        write = new Write();
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
        String file = "TrOptions.fxml";
        BackButton backButton = new BackButton();
        backButton.backPress(file,event);
    }


    // EFFECTS: button press views the scene with the trainer details
    // Read the class/function for function documentation
    public void viewDetails(MouseEvent event) throws IOException {
        String file = "TrainerDetails.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: button press views the scene with all the pokemon registered with the trainer
    public void viewPokemon(MouseEvent event) throws IOException {
        String file = "ViewPokemon.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: button press views the scene to catch a new pokemon and register it
    public void newPokemon(MouseEvent event) throws IOException {
        String file = "CatchPokemon.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: button press views the scene that lets the trainer release one of their pokemon
    public void releasePokemon(MouseEvent event) throws IOException {
        String file = "ReleasePokemon.fxml";
        NewWindowButton newWindowButton = new NewWindowButton();
        newWindowButton.newWindowButtonPress(file,event);
    }

    // EFFECTS: button press views the scene that lets the trainer add a badge
    public void addBadge(MouseEvent event) throws IOException, ParseException {
        Trainer trainer = read.readTrainer(jsonFileTrainer);
        boolean a = false;
        try {
            a = trainer.addBadge();
        } catch (AllBadges allBadges) {
            allBadges.printStackTrace();
        }
        if (a) {
            prompt.setText("Badge added Successfully!");

            write.writeJson(jsonFileTrainer, trainer);
        } else {
            prompt.setText("You have collected 8 badges!");
        }
    }
}
