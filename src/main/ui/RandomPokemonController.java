package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import kanto.json.Read;
import kanto.model.Pokemon;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;

//Represent a class containing methods, fields and scenes associated with RandomPokemonController
public class RandomPokemonController {

    Pokedex pokedex;
    Read read;
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";


    @FXML
    private Button randomButton;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField types;

    public RandomPokemonController() throws IOException, ParseException {
        pokedex = new Pokedex();
        read = new Read();
    }

    // EFFECTS: button press views the details of a random pokemon
    public void random(MouseEvent event) {
        Random rand = new Random();
        int randnum = rand.nextInt(152);

        Pokemon p = read.searchPokemon(jsonFilePokemon, randnum + 1);
        name.setText("Name: " + p.getPokemonName());
        id.setText("ID: " + p.getPokeID());
        height.setText("Height: " + p.getHeight());
        weight.setText("Weight: " + p.getWeight());
        types.setText("Types: " + p.getPokemonType());


    }

    // EFFECTS: displays the previous screen
    // Read the class/function for function documentation
    public void goBack(MouseEvent event) throws IOException {

        String file = "WelcomeScreen.fxml";
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
