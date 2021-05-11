package json;

import kanto.exceptions.AllBadges;
import kanto.json.Read;
import kanto.json.Write;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWrite {
    Trainer t1 = new Trainer();
    ArrayList<Integer> ids = new ArrayList<>();
    //Pokemon charmander;
    public static final String testTrainerFile = "data/trainerTest.json";
   // public static final String emptyTrainerFile = "data/emptyFileTrainer.json";

    Write write = new Write();
    Read read = new Read();
    @BeforeEach
    public void runBefore() throws IOException, ParseException {

        t1.setTotalBadge(6);
        t1.setTrainerID(429);
        t1.setTrainerFirstName("Jake");
        t1.setTrainerLastName("Peralta");
        ids.add(4);
        t1.setPokemon(ids);
    }

    @Test
    public void testWriteJson() throws IOException, ParseException {
        assertTrue(write.writeJson(testTrainerFile, t1));
        write.writeJson(testTrainerFile,t1);

        assertEquals(read.readTrainer(testTrainerFile).getTrainerID(),429);
        assertEquals(read.readTrainer(testTrainerFile).getTrainerFirstName(),"Jake");
        assertEquals(read.readTrainer(testTrainerFile).getTrainerLastName(),"Peralta");
        assertEquals(read.readTrainer(testTrainerFile).getTotalPokemon(),1);
        assertEquals(read.readTrainer(testTrainerFile).pokemons.get(0).getPokemonName(),"Charmander");
    }
}
