package kanto.model;

import java.util.ArrayList;

//Represents a Pokemon with its key attributes (Name, Number, Type, Height, Weight)
public class Pokemon {
    protected String pokemonName; // Pokemon's name
    protected int pokeID; // Pokemon's number/ID
    protected ArrayList<String> pokemonType; // Arraylist of the pokemon's types
    protected String weight; //Pokemon's weight
    protected String height; //Pokemon's height

    //EFFECTS: makes a new pokemon with all its key attributes initialised.
    public Pokemon() {
        pokemonName = "";
        pokeID = 0;
        pokemonType = new ArrayList<>();
        height = "";
        weight = "";
    }

    //MODIFIES: this
    //EFFECTS: Sets the Pokemon's name to the given name
    public void setPokemonName(String name) {
        this.pokemonName = name;
    }

    //MODIFIES: this
    //EFFECTS: Sets the Pokemon's ID to the given ID
    public void setPokeID(int id) {
        this.pokeID = id;
    }

    //MODIFIES: this
    //EFFECTS: Sets the Pokemon's types to the given types
    public void setPokemonType(ArrayList<String> types) {
        this.pokemonType = types;
    }


    //EFFECTS: Returns the pokemon's weight
    public String getWeight() {
        return weight;
    }

    //EFFECTS: Returns the pokemon's height
    public String getHeight() {
        return height;
    }

    //EFFECTS: Returns the pokemon's name
    public String getPokemonName() {

        return pokemonName;
    }

    //EFFECTS: Returns the pokemon's ID
    public int getPokeID() {

        return pokeID;
    }

    //MODIFIES: this
    //EFFECTS: Sets the Pokemon's height to the given height
    public void setHeight(String height) {
        this.height = height;
    }

    //MODIFIES: this
    //EFFECTS: Sets the Pokemon's weight to the given weight
    public void setWeight(String weight) {
        this.weight = weight;
    }

    //EFFECTS: Returns the pokemon's types
    public ArrayList<String> getPokemonType() {
        return pokemonType;
    }

}