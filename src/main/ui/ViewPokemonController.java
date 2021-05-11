package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import kanto.json.Read;
import kanto.json.Write;
import kanto.model.Pokemon;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


//Represent a class containing methods, fields and scenes associated with ViewPokemonFXML
public class ViewPokemonController implements Initializable {

    Pokedex pokedex;
    Read read;
    Write write;
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private ListView pokemons;


    public ViewPokemonController() throws IOException, ParseException {
        pokedex = new Pokedex();
        read = new Read();
        write = new Write();
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

    // EFFECTS: function reads and displays the trainer's pokemon from the file
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Trainer trainer;
        ArrayList<String> names = new ArrayList<>();
        try {
            trainer = read.readTrainer(jsonFileTrainer);
            for (Pokemon i : trainer.pokemons) {
                pokemons.getItems().add(i.getPokemonName());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }
}
