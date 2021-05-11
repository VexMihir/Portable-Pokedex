package json;

import kanto.exceptions.AllBadges;
import kanto.json.Read;
import kanto.model.Pokemon;
import kanto.model.Trainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestRead {
    private static String jsonFilePokemon = "data/pokemon.json";
    Read read = new Read();

    //private static String emptyFilePokemon = "data/emptyFilePokemon.json";
    private static String emptyFileTrainer = "data/emptyFileTrainer.json";
    private static String testFile = "data/testFile.json";

    JSONParser jsonParser = new JSONParser();
    FileReader reader = new FileReader(jsonFilePokemon);
    Object obj = jsonParser.parse(reader);

    JSONObject jsonObject = (JSONObject) obj;
    Trainer t = new Trainer();

    JSONArray pokemonList = (JSONArray) jsonObject.get("Pokemon");
    int i = 1;
    Object p = pokemonList.get(i);
    Pokemon charmander;
    ArrayList<String> typeFire;

    public TestRead() throws IOException, ParseException {
    }

    @BeforeEach
    public void runBefore() throws IOException, ParseException {
        typeFire = new ArrayList<>();
        typeFire.add("Fire");
        charmander = new Pokemon();


        charmander.setPokeID(4);
        charmander.setPokemonName("Charmander");
        charmander.setPokemonType(typeFire);

        t.setTrainerID(742);
        t.setTrainerFirstName("Mihir");
        t.setTrainerLastName("H");
        t.setTotalBadge(5);
        t.setTotalPokemon(8);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(65);
        t.setPokemon(ids);


    }

    @Test
    public void testReadTrainer() throws IOException, ParseException {

        try {
            read.readTrainer("bfkbw");
        } catch (IOException | ParseException e) {
//Do nothing
        }

        assertEquals(read.readTrainer(testFile).getTrainerID(), 134);
        assertEquals(read.readTrainer(testFile).getTrainerFirstName(), "Jake");
        assertEquals(read.readTrainer(testFile).getTrainerLastName(), "Peralta");
    }

    @Test
    public void testSearchPokemon() {

        read.searchPokemon("bfkbw", 5);
        assertEquals(read.searchPokemon(jsonFilePokemon, 4).getPokemonName(), "Charmander");
        assertEquals(read.searchPokemon(jsonFilePokemon, 240).getPokeID(), 0);
        assertEquals(read.searchPokemon(jsonFilePokemon, 240).getPokeID(), 0);


    }

    @Test
    void testParsePokemonObject() {
        assertEquals(read.parsePokemonObject(4, (JSONObject) p).getPokeID(), 0);
        assertEquals(read.parsePokemonObject(300, (JSONObject) p).getPokeID(), 0);
    }

    @Test
    void testGetID() {
        assertEquals(read.getID((JSONObject) p), "2");
    }

    @Test
    void testGetWeight() {
        assertEquals(read.getWeight((JSONObject) p), "13.0 kg");
    }

    @Test
    void testGetHeight() {
        assertEquals(read.getHeight((JSONObject) p), "0.99 m");
    }

    @Test
    void testGetNum() {
        assertEquals(read.getNum((JSONObject) p), "002");
    }

    @Test
    void testGetName() {
        assertEquals(read.getName((JSONObject) p), "Ivysaur");
    }

    @Test
    void testGetType() {
        ArrayList<String> type = new ArrayList<>();
        type.add("Grass");
        type.add("Poison");
        assertEquals(read.getType((JSONObject) p), type);
    }
}
