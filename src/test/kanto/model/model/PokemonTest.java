package kanto.model.model;

import kanto.model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {

    private Pokemon charmander;
    private Pokemon bulbasaur;
    private Pokemon squirtle;
    private ArrayList<String> typeFire;
    private ArrayList<String> typeWater;
    private ArrayList<String> typeGrass;

    @BeforeEach
    void runBefore() {
        charmander = new Pokemon();
        bulbasaur = new Pokemon();
        squirtle = new Pokemon();
        typeFire = new ArrayList<>();
        typeGrass = new ArrayList<>();
        typeWater = new ArrayList<>();

        typeFire.add("Fire");
        typeWater.add("Water");
        typeGrass.add("Grass");
        typeGrass.add("Poison");

        charmander.setPokeID(4);
        charmander.setPokemonName("Charmander");
        charmander.setPokemonType(typeFire);
        charmander.setHeight("1.4 m");
        charmander.setWeight("14.0 kg");

        squirtle.setPokeID(7);
        squirtle.setPokemonName("Squirtle");
        squirtle.setPokemonType(typeWater);
        squirtle.setHeight("0.8 m");
        squirtle.setWeight("22.0 kg");

    }

    @Test
    public void testSetPokemonName() {
        bulbasaur.setPokemonName("LOL");
        assertEquals(bulbasaur.getPokemonName(), "LOL");
        bulbasaur.setPokemonName("Bulbasaur");
        assertEquals(bulbasaur.getPokemonName(), "Bulbasaur");
    }

    @Test
    public void testSetPokeID() {
        bulbasaur.setPokeID(16);
        assertEquals(bulbasaur.getPokeID(), 16);
        bulbasaur.setPokeID(1);
        assertEquals(bulbasaur.getPokeID(), 1);
    }

    @Test
    public void testSetPokemonType() {
        bulbasaur.setPokemonType(typeWater);
        assertEquals(bulbasaur.getPokemonType(), typeWater);
        bulbasaur.setPokemonType(typeGrass);
        assertEquals(bulbasaur.getPokemonType(), typeGrass);
    }

    @Test
    public void testGetPokemonName() {
        assertEquals(charmander.getPokemonName(), "Charmander");
        assertEquals(squirtle.getPokemonName(), "Squirtle");
    }

    @Test
    public void testGetPokeID() {
        assertEquals(charmander.getPokeID(), 4);
        assertEquals(squirtle.getPokeID(), 7);
    }

    @Test
    public void testGetWeight() {
        assertEquals(charmander.getWeight(), "14.0 kg");
        assertEquals(squirtle.getWeight(), "22.0 kg");
    }

    @Test
    public void testGetHeight() {
        assertEquals(charmander.getHeight(), "1.4 m");
        assertEquals(squirtle.getHeight(), "0.8 m");
    }

    @Test
    public void testSetHeight() {
        charmander.setHeight("1.3 m");
        assertEquals(charmander.getHeight(), "1.3 m");

    }

    @Test
    public void testSetWeight() {
        charmander.setWeight("19.0 kg");
        assertEquals(charmander.getWeight(), "19.0 kg");
    }

    @Test
    public void testGetPokemonType() {
        assertEquals(charmander.getPokemonType(), typeFire);
        assertEquals(squirtle.getPokemonType(), typeWater);
    }
}
