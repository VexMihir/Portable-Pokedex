package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import kanto.json.Read;
import kanto.json.Write;
import kanto.model.Pokemon;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;

import java.io.IOException;

//Represent a class containing methods, fields and scenes associated with CatchPokemonFXML
public class CatchPokemonController {
    Pokedex pokedex;
    Read read;
    Write write;
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";


    @FXML
    private Button catchButton;

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

    public CatchPokemonController() throws IOException, ParseException {
        pokedex = new Pokedex();
        read = new Read();
        write = new Write();
    }

    // MODIFIES: jsonFileTrainer
    // EFFECTS: button press catches a new pokemon and adds it to the database
    public void catchNew(MouseEvent event) throws IOException, ParseException {
        String id = searchID.getText();
        int pokeID1 = Integer.parseInt(id);
        Pokemon p = read.searchPokemon(jsonFilePokemon, pokeID1);
        name.setText("Name: " + p.getPokemonName());
        height.setText("Height: " + p.getHeight());
        weight.setText("Weight: " + p.getWeight());
        types.setText("Types: " + p.getPokemonType());

        Trainer trainer = read.readTrainer(jsonFileTrainer);
        if (trainer.catchPokemons(pokeID1)) {
            prompt.setText(p.getPokemonName() + " was successfully added to your collection.");
            write.writeJson(jsonFileTrainer,trainer);
        } else {
            prompt.setText("Invalid Pokemon ID");
        }


    }


    // EFFECTS: displays the previous screen
    // Read the class for function documentation
    public void goBack(MouseEvent event) throws IOException {

        String file = "ExistingTr.fxml";
        BackButton backButton = new BackButton();
        backButton.backPress(file,event);
    }

    // EFFECTS: displays the exit screen
    // Read the class for function documentation
    public void exit(MouseEvent event) throws IOException, InterruptedException {

        ExitButton exitButton = new ExitButton();
        exitButton.exitPress(event);
    }
}

