package kanto.model;

import kanto.exceptions.AllBadges;
import kanto.exceptions.NotCaughtException;
import kanto.json.Read;

import java.util.ArrayList;
import java.util.Scanner;

// Represents a trainer, with his/her name, ID, number of badges obtained, and pokemons they've collected.
public class Trainer extends Read {
    Scanner myObj = new Scanner(System.in);
    protected String trainerFirstName;  // Represents the trainer's first name
    protected String trainerLastName;  // Represents the trainer's last name
    protected int trainerID;  // Represents the trainer's ID
    protected int totalPokemon;  // Represents the total pokemon collected by the trainer
    protected int totalBadge;  // Represents the total badges collected by the trainer
    boolean flag = false;
    public ArrayList<Pokemon> pokemons;  // Represents the various pokemon collected by the trainer
    protected boolean isNew; // Tells if the trainer is already registered or a new one


    private static String jsonFilePokemon = "data/pokemon.json";

    //REQUIRES: A new trainer (not registered)
    //EFFECTS: makes a new trainer with all its key attributes initialised and isNew set to true.
    public Trainer() {
        super();
        trainerFirstName = "";
        trainerLastName = "";
        trainerID = 0;
        isNew = true;
        totalBadge = 0;
        totalPokemon = 0;
        pokemons = new ArrayList<>();

    }


    //MODIFIES: this
    //EFFECTS: Sets the Trainer's first name to the given name
    public void setTrainerFirstName(String name) {
        this.trainerFirstName = name;
    }

    //MODIFIES: this
    //EFFECTS: Sets the Trainer's last name to the given name
    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    //MODIFIES: this
    //EFFECTS: Sets the Trainer's ID to the given ID
    public void setTrainerID(int id) {
        this.trainerID = id;
    }

    //REQUIRES: given number of badges (parameter) less than 8
    //MODIFIES: this
    //EFFECTS: Sets the trainer's badges to the given badges.
    //         If the given badges is more than eight or the trainer already has 8 badges,
    //         then no effects
    public void setTotalBadge(int badges) {
        if (badges < 8) {
            this.totalBadge = badges;
        } else if (badges == 8) {
            //System.out.println("Congrats! You've collected all the badges. Time to challenge the ELite Four");
            this.totalBadge = badges;
        } else {
            System.out.println("There can be only 8 badges");

        }


    }

    //MODIFIES: this
    //EFFECTS: Sets totalPokemon to the given parameter;
    public void setPokemon(ArrayList<Integer> poke) {
        ArrayList<Pokemon> pokes = new ArrayList<>();

        for (int i : poke) {
            pokes.add(searchPokemon(jsonFilePokemon, i));
        }
        this.pokemons = pokes;
        this.totalPokemon = pokes.size();
    }


    //MODIFIES: this
    //EFFECTS: Sets totalPokemon to the given parameter;
    public void setTotalPokemon(int num) {
        this.totalPokemon = num;
    }

    //EFFECTS: Returns true if the trainer is unregistered, else false
    public boolean isNew() {
        return isNew;
    }

    //MODIFIES: this
    //EFFECTS: Sets isNew to false once the trainer is registered
    public void setNew(boolean anew) {
        isNew = anew;
    }

    // REQUIRES: An input of an integer between 1 and 151
    //MODIFIES: this
    //EFFECTS: Adds the pokemon with the given ID to the list of pokemon collected by the trainer
    public boolean catchPokemons(int id) {
        flag = false;

        Pokemon poke = new Pokemon();
        poke = searchPokemon(jsonFilePokemon, id);
        if (poke.getPokeID() == id) {
            this.pokemons.add(poke);
            flag = true;
            this.addTotalPokemon();
        }
        return flag;
    }

    //REQUIRES: Number of badges collected by the trainer to be less than 8
    //MODIFIES: this
    //EFFECTS: Increases the number of badges collected by the trainer by 1
    public boolean addBadge() throws AllBadges {
        boolean a = true;
        if (this.getTotalBadge() == 8) {
            a = false;
            throw new AllBadges();
        }
        if (this.getTotalBadge() > 0) {
            int badges = this.getTotalBadge();
            ++badges;
            this.setTotalBadge(badges);
        }
        return a;
    }

    // REQUIRES: An input of an integer between 1 and 151 and,
    // the trainer already has the pokemon with the same ID
    //MODIFIES: this
    //EFFECTS: Removes the pokemon with the given ID to the list of pokemon collected by the trainer
    public boolean releasePokemon(int num) throws NotCaughtException {

        String name;
        boolean a;
        ArrayList<Integer> temp = new ArrayList<>();

        for (Pokemon i : this.pokemons) {

            temp.add(i.getPokeID());
        }

        if (temp.contains(num)) {
            int index = temp.indexOf(num);
            Pokemon pokemon = this.pokemons.get(index);
            name = pokemon.getPokemonName();
            this.pokemons.remove(index);


            System.out.println("\n" + name + " was released into the wild! Bye " + name + "\n");
            a = true;
            removeTotalPokemon();
        } else {
            //System.out.println("Sorry you haven't caught that Pokemon yet");
            a = false;
            throw new NotCaughtException();
        }
        return a;
    }

    //MODIFIES: this
    //EFFECTS: Decreases the number of pokemon by 1
    public void removeTotalPokemon() {
        totalPokemon--;
    }

    //MODIFIES: this
    //EFFECTS: Increases the number of pokemon by 1
    public void addTotalPokemon() {
        totalPokemon++;
    }

    //EFFECTS: Returns the trainer's total badges
    public int getTotalBadge() {
        return totalBadge;
    }

    //EFFECTS: Returns the trainer's first name
    public String getTrainerFirstName() {
        return trainerFirstName;
    }

    //EFFECTS: Returns the trainer's last name
    public String getTrainerLastName() {
        return trainerLastName;
    }

    //EFFECTS: Returns the trainer's ID
    public int getTrainerID() {
        return trainerID;
    }

    //EFFECTS: Returns the trainer's total pokemon
    public int getTotalPokemon() {
        return totalPokemon;
    }

    //EFFECTS: Displays the trainer's details
    public boolean printDetails() {
        String firstName;
        String lastName;
        long id;
        int badges;
        int total;

        firstName = this.getTrainerFirstName();
        lastName = this.getTrainerLastName();
        id = this.getTrainerID();
        badges = this.getTotalBadge();
        total = this.getTotalPokemon();


        System.out.println("Name:" + firstName + " " + lastName);
        System.out.println("ID:" + id);
        System.out.println("Badges:" + badges);
        System.out.println("Total Pokemon:" + total);

        return true;
    }

    //EFFECTS: Returns the pokemon's name collected by the trainer
    public boolean printPokemon() throws NotCaughtException {

        if (this.pokemons.size() == 0) {
            throw new NotCaughtException();
        } else {
            for (Pokemon i : this.pokemons) {
                String name = i.getPokemonName();
                System.out.println(name);
            }
        }
        return true;
    }
}
