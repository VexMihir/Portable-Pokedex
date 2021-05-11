package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import kanto.json.Read;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;

import java.io.IOException;

//Represent a class containing methods, fields and scenes associated with TrainerDetailsFXML
public class TrainerDetailsController {

    Pokedex pokedex;
    Read read;
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";


    @FXML
    private Button loadButton;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private TextField trainerID;
    @FXML
    private TextField name;
    @FXML
    private TextField badges;
    @FXML
    private TextField pokemons;


    public TrainerDetailsController() throws IOException, ParseException {
        pokedex = new Pokedex();
        read = new Read();
    }

    // REQUIRES: jsonFileTrainer
    // EFFECTS: button press reads and displays the trainer's details from the file
    public void load(MouseEvent event) throws IOException, ParseException {
        Trainer exTrainer = read.readTrainer(jsonFileTrainer);
        name.setText("Name: " + exTrainer.getTrainerFirstName() + " " + exTrainer.getTrainerLastName());
        trainerID.setText("ID: " + exTrainer.getTrainerID());
        badges.setText("Badges: " + exTrainer.getTotalBadge());
        pokemons.setText("Pokemon: " + exTrainer.getTotalPokemon());


    }

    // EFFECTS: displays the previous screen
    // Read the class/function for function documentation
    public void goBack(MouseEvent event) throws IOException {
        String file = "ExistingTr.fxml";
        BackButton backButton = new BackButton();
        backButton.backPress(file,event);
    }

    // EFFECTS: displays the exit screen
    // Read the class/function for function documentation
    public void exit(MouseEvent event) throws IOException, InterruptedException {

        ExitButton exitButton = new ExitButton();
        exitButton.exitPress(event);

    }
}
