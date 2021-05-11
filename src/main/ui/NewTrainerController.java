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

//Represent a class containing methods, fields and scenes associated with NewTrainerFXML
public class NewTrainerController {
    Pokedex pokedex;
    Read read;
    Write write;
    private static String jsonFileTrainer = "data/trainer.json";


    @FXML
    private Button registerButton;

    @FXML
    private Button back;

    @FXML
    private Button exit;

    @FXML
    private TextField id;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;
    @FXML
    private TextField badges;
    @FXML
    private TextField firstPokemon;

    @FXML
    private TextField prompt;

    public NewTrainerController() throws IOException, ParseException {
        pokedex = new Pokedex();
        read = new Read();
        write = new Write();
    }

    // MODIFIES: jsonFileTrainer
    // EFFECTS: button press registers the trainer to the database
    public void register(MouseEvent event) throws IOException, ParseException {

        Trainer trainer = new Trainer();

        String firstNameText = firstName.getText();
        trainer.setTrainerFirstName(firstNameText);
        String lastNameText = lastName.getText();
        trainer.setTrainerLastName(lastNameText);
        String trainerID = id.getText();
        trainer.setTrainerID(Integer.parseInt(trainerID));
        int totalBadge = Integer.parseInt(badges.getText());
        if ((totalBadge < 9) && (totalBadge >= 0)) {
            trainer.setTotalBadge(totalBadge);
        } else {
            trainer.setTotalBadge(8);
        }
        String pokeID = firstPokemon.getText();

        trainer.catchPokemons(Integer.parseInt(pokeID));

        write.writeJson(jsonFileTrainer, trainer);

        prompt.setText("Trainer successfully registered and added to the system");


    }

    // EFFECTS: displays the previous screen
    // Read the class/function for function documentation
    public void goBack(MouseEvent event) throws IOException {
        String file = "WelcomeScreen.fxml";
        BackButton backButton = new BackButton();
        backButton.backPress(file, event);
    }

    // EFFECTS: displays the exit screen
    // Read the class/function for function documentation
    public void exit(MouseEvent event) throws IOException, InterruptedException {

        ExitButton exitButton = new ExitButton();
        exitButton.exitPress(event);
    }
}
