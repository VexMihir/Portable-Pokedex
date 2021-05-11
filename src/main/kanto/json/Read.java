package kanto.json;


import kanto.exceptions.AllBadges;
import kanto.model.Pokemon;
import kanto.model.Trainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Represents a JSON file reader that reads through a file and returns key value pairs
public class Read extends Write {
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";

    //REQUIRES: An input of the trainer's file
    //EFFECTS: Reads the json file for the trainer's details and returns it as a Trainer object
    public Trainer readTrainer(String file) throws IOException, ParseException {
        Trainer trainer = new Trainer();
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader(file);
        Object obj = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;

        trainer.setTrainerID(Integer.parseInt(String.valueOf(jsonObject.get("ID"))));

        trainer.setTrainerLastName((String) jsonObject.get("LName"));

        trainer.setTotalBadge(Integer.parseInt(String.valueOf(jsonObject.get("Badge"))));

        //Do nothing


        trainer.setTotalPokemon(Integer.parseInt(String.valueOf(jsonObject.get("TotalPokemon"))));

        trainer.setTrainerFirstName((String) jsonObject.get("FName"));

        trainer.setNew(false);

        ArrayList<Integer> ids = new ArrayList<>();

        JSONArray jsonArray = (JSONArray) jsonObject.get("Pokemon");
        //Iterating the contents of the array
        for (Object o : jsonArray) {
            String values = String.valueOf(o);
            int pokeID = Integer.parseInt(values);
            ids.add(pokeID);

            trainer.setPokemon(ids);
        }
        return trainer;

    }

    //REQUIRES: An input of a Pokemon's ID between 1 and 151
    //EFFECTS: Searches the JSON file for the pokemon with the same ID and returns a Pokemon object
    public Pokemon searchPokemon(String file, int id) {

        Pokemon poke2 = new Pokemon();
        //Iterate over employee array
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try {
            //Read JSON file
            FileReader reader = new FileReader(file);

            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray pokemonList = (JSONArray) jsonObject.get("Pokemon");


            for (Object i : pokemonList) {
                poke2 = parsePokemonObject(id, (JSONObject) i);
                if (poke2.getPokeID() == id) {
                    break;
                } else {
                    poke2 = new Pokemon();
                }

            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return poke2;


    }

    //ALL BELOW ARE HELPER
    //EFFECTS: Compares the pokemon's ID held in the JSONObject with the given ID.
    //         If found then, sets the values to a new Pokemon object
    public Pokemon parsePokemonObject(int id, JSONObject pokemon) {
        Pokemon poke = new Pokemon();
        if (id == Integer.parseInt(getID(pokemon))) {
            poke.setPokeID((Integer.parseInt(getNum(pokemon))));
            poke.setPokemonName(getName(pokemon));
            poke.setPokemonType(getType(pokemon));
            poke.setHeight(getHeight(pokemon));
            poke.setWeight(getWeight(pokemon));
        }

        return poke;

    }


    //HELPER
    //EFFECTS: Returns the pokemon's name held in the JSONObject
    public String getName(JSONObject obj) {

        return (String) obj.get("name");
    }

    //HELPER
    //EFFECTS: Returns the pokemon's num held in the JSONObject
    public String getNum(JSONObject obj) {

        return (String) obj.get("num");
    }

    //HELPER
    //EFFECTS: Returns the pokemon's height held in the JSONObject
    public String getHeight(JSONObject obj) {

        return (String) obj.get("height");
    }

    //HELPER
    //EFFECTS: Returns the pokemon's weight held in the JSONObject
    public String getWeight(JSONObject obj) {

        return (String) obj.get("weight");
    }

    //HELPER
    //EFFECTS: Returns the pokemon's types held in the JSONObject
    public ArrayList<String> getType(JSONObject obj) {
        JSONArray types = (JSONArray) obj.get("type");
        ArrayList<String> type = new ArrayList<>();
        for (Object o : types) {
            //System.out.println(types.get(i));
            type.add((String) o);
        }
        return type;
    }

    //HELPER
    //EFFECTS: Returns the pokemon's ID held in the JSONObject
    public String getID(JSONObject obj) {

        return String.valueOf(obj.get("id"));
    }

}
