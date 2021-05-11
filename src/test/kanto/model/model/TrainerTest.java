package kanto.model.model;


import kanto.exceptions.AllBadges;
import kanto.exceptions.NotCaughtException;
import kanto.model.Pokemon;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {
    Trainer Ash;
    Trainer Brock;
    Trainer Misty;
    ArrayList<Pokemon> poke;
    Pokemon charmander = new Pokemon();
    Pokemon squirtle = new Pokemon();
    private ArrayList<String> typeFire;
    private ArrayList<String> typeWater;


    @BeforeEach
    void runBefore() throws IOException, ParseException {
        typeFire = new ArrayList<>();
        typeFire.add("Fire");
        charmander.setPokeID(4);
        charmander.setPokemonName("Charmander");
        charmander.setPokemonType(typeFire);

        typeWater = new ArrayList<>();
        typeWater.add("Water");
        squirtle.setPokeID(7);
        squirtle.setPokemonName("Squirtle");
        squirtle.setPokemonType(typeWater);

        Ash = new Trainer();
        Misty = new Trainer();

        Brock = new Trainer();
        Brock.setNew(false);

        Brock.setTotalBadge(8);
        Brock.setTrainerFirstName("Brock");
        Brock.setTrainerLastName("Brock");
        Brock.setTrainerID(4892);
        poke = new ArrayList<>();
        poke.add(charmander);
        Brock.pokemons = poke;

        Ash.catchPokemons(61);
        Ash.catchPokemons(70);
        Ash.catchPokemons(132);

        try {
            Ash.releasePokemon(70);
            Ash.releasePokemon(12);
        } catch (NotCaughtException e) {
            System.out.println("You haven't caught this pokemon yet.");
        }


    }

    @Test
    public void testSetTrainerFirstName() {
        Ash.setTrainerFirstName("Ash");
        assertEquals(Ash.getTrainerFirstName(), "Ash");

    }

    @Test
    public void testIsNew() {
        assertTrue(Ash.isNew());
    }

    @Test
    public void testSetNew() {
        Ash.setNew(false);
        assertFalse(Ash.isNew());
    }

    @Test
    public void testPrintPokemonIfPokemonIsCaught() {
        try {
            Brock.printPokemon();
            // Misty.printPokemon();
        } catch (NotCaughtException e) {
            fail();
        }

    }

    @Test
    public void testPrintPokemonIfPokemonIsNotCaught() {
        try {
            //Brock.printPokemon();
            Misty.printPokemon();
            fail();
        } catch (NotCaughtException e) {
            //Do nothing
            System.out.println("You haven't caught this pokemon yet.");
        }

    }


    @Test
    public void testPrintDetails() {
        assertTrue(Brock.printDetails());
    }

    @Test
    public void testSetTrainerLastName() {
        Ash.setTrainerLastName("Ketchum");
        assertEquals(Ash.getTrainerLastName(), "Ketchum");
    }

    @Test
    public void testSetTrainerID() {
        Ash.setTrainerID(56834);
        assertEquals(Ash.getTrainerID(), 56834);
    }

    @Test
    public void testSetTotalBadge() {
        Ash.setTotalBadge(10);
        assertEquals(Ash.getTotalBadge(), 0);

        Ash.setTotalBadge(4);
        assertEquals(Ash.getTotalBadge(), 4);
        Ash.setTotalBadge(8);
        assertEquals(Ash.getTotalBadge(), 8);
    }

    @Test
    public void testCatchPokemon() throws IOException, ParseException {
        Ash.catchPokemons(23);
        Ash.catchPokemons(400);
        assertTrue(Ash.catchPokemons(5));
        assertFalse(Ash.catchPokemons(300));
        assertTrue(Misty.catchPokemons(36));
    }


    @Test
    public void testReleasePokemonIfNotCaught() throws IOException, ParseException {
        try {
            assertFalse(Misty.releasePokemon(5));
            fail();
        } catch (NotCaughtException e) {
            //Do nothing
            System.out.println("You haven't caught this pokemon");
        }

        Misty.catchPokemons(5);
        Misty.catchPokemons(65);
        try {
            Misty.releasePokemon(5);
            //Do nothing
        } catch (NotCaughtException e) {
            fail();
            //Should not have happened.
        }


    }

    @Test
    public void testReleasePokemonIfCaught() throws IOException, ParseException {

        Ash.catchPokemons(46);
        Ash.catchPokemons(36);
        Ash.catchPokemons(321);
        Ash.catchPokemons(23);
        try {
            Ash.releasePokemon(46);
            //Do nothing
        } catch (NotCaughtException e) {
            //Should not have happened.
            fail();

        }
    }

    @Test
    public void testSetPokemon() throws IOException, ParseException {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(4);

        Misty.setPokemon(ids);
        assertEquals(Misty.getTotalPokemon(), 1);

        assertEquals(Misty.pokemons.get(0).getPokeID(), 4);

        ids.add(7);
        Misty.setPokemon(ids);
        assertEquals(Misty.getTotalPokemon(), 2);
        assertEquals(Misty.pokemons.get(0).getPokeID(), 4);
        assertEquals(Misty.pokemons.get(1).getPokeID(), 7);
    }

    @Test
    public void testSetTotalPokemon() {
        Misty.setTotalPokemon(4);
        assertEquals(Misty.getTotalPokemon(), 4);
        Misty.setTotalPokemon(9);
        assertEquals(Misty.getTotalPokemon(), 9);
    }

    @Test
    public void testAddBadgesIfAllNotCollected() {
        try {
            Misty.setTotalBadge(2);
            Misty.addBadge();
            //Do nothing
        } catch (AllBadges allBadges) {
            fail();
        }

    }

    @Test
    public void testAddBadgesIfAllCollected() {
        try {
            assertTrue(Misty.addBadge());
            assertTrue(Ash.addBadge());
            Ash.setTotalBadge(7);
            Ash.addBadge();
            Ash.addBadge();
            fail();
        } catch (AllBadges allBadges) {
            System.out.println("You have collected all the badges!");
            //Do nothing
        }

    }

    @Test
    public void testAddBadgesIfInitialWrong() {
        Ash.setTotalBadge(-2);
        try {
            Ash.addBadge();
        } catch (AllBadges allBadges) {
            allBadges.printStackTrace();
        }
        assertEquals(Ash.getTotalBadge(),-2);

    }

    @Test
    public void testAddTotalPokemon() {
        Misty.addTotalPokemon();
        assertEquals(Misty.getTotalPokemon(), 1);
        Misty.addTotalPokemon();
        Misty.addTotalPokemon();
        assertEquals(Misty.getTotalPokemon(), 3);
    }

    @Test
    public void testRemoveTotalPokemon() {
        Misty.addTotalPokemon();
        Misty.addTotalPokemon();
        Misty.addTotalPokemon();
        assertEquals(Misty.getTotalPokemon(), 3);
        Misty.removeTotalPokemon();
        assertEquals(Misty.getTotalPokemon(), 2);
        Misty.removeTotalPokemon();
        assertEquals(Misty.getTotalPokemon(), 1);
    }


}
