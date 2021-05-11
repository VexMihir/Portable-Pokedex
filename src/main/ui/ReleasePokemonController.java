package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import kanto.exceptions.AllBadges;
import kanto.exceptions.NotCaughtException;
import kanto.json.Read;
import kanto.json.Write;
import kanto.model.Pokemon;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;

import java.io.IOException;

//Represent a class containing methods, fields and scenes associated with ReleasePokemonFXML
public class ReleasePokemonController {
    Pokedex pokedex;
    Read read;
    Write write;
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";


    @FXML
    private Button releaseButton;

    @FXML
    private TextField prompt;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private TextField searchID;
    @FXML
    private TextField name;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField types;

    public ReleasePokemonController() throws IOException, ParseException {
        pokedex = new Pokedex();
        read = new Read();
        write = new Write();
    }

    // REQUIRES: trainer already has caught the pokemon
    // MODIFIES: jsonFileTrainer
    // EFFECTS: button press release the pokemon with the id
    public void release(MouseEvent event) throws IOException, ParseException {
        String id = searchID.getText();
        int pokeID1 = Integer.parseInt(id);
        Pokemon p = read.searchPokemon(jsonFilePokemon, pokeID1);
        name.setText("Name: " + p.getPokemonName());
        height.setText("Height: " + p.getHeight());
        weight.setText("Weight: " + p.getWeight());
        types.setText("Types: " + p.getPokemonType());

        Trainer trainer = read.readTrainer(jsonFileTrainer);
        boolean a = false;
        try {
            a = trainer.releasePokemon(pokeID1);
        } catch (NotCaughtException e) {
            // Do nothing
        }
        if (a) {
            prompt.setText(p.getPokemonName() + " was released into the wild.");
            write.writeJson(jsonFileTrainer, trainer);
        } else {
            prompt.setText("You haven't caught this Pokemon yet.");
        }


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

