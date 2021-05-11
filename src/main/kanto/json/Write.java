package kanto.json;

import kanto.model.Trainer;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Represents a JSON file writer that writes key value pairs to a JSON file.
public class Write {

    private String jsonFileTrainers = "data/trainer.json";

    //REQUIRES: A file name to write and store the data in and
    //a Trainer object with the trainer's details that need to be stored
    //MODIFIES: the json file containing the trainer's details
    //EFFECTS: Overwrite the file with the data stored in the object
    public boolean writeJson(String fileName, Trainer trainer) throws IOException {
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("ID", trainer.getTrainerID());
        jsonObject.put("FName", trainer.getTrainerFirstName());
        jsonObject.put("LName", trainer.getTrainerLastName());
        jsonObject.put("Badge", trainer.getTotalBadge());
        ArrayList<Integer> pokemons = new ArrayList<>();

        for (int i = 0; i < trainer.pokemons.size(); i++) {
            pokemons.add(trainer.pokemons.get(i).getPokeID());
        }

        jsonObject.put("Pokemon", pokemons);
        jsonObject.put("TotalPokemon", pokemons.size());


        FileWriter file = new FileWriter(fileName);
        file.write(jsonObject.toString());
        file.close();

        System.out.println("Trainer added successfully to the database");

        return true;
    }


}


