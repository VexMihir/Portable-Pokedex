
package ui;

import kanto.exceptions.AllBadges;
import kanto.exceptions.NotCaughtException;
import kanto.model.Pokemon;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//NOT PART OF THE APPLICATION ANYMORE
public class Pokedex extends Trainer {


    boolean keepGoing = true;
    Trainer trainer = new Trainer();
    Trainer trainer1 = new Trainer();
    Trainer exTrainer = readTrainer(jsonFileTrainer);
    boolean flag = false;
    boolean counterA = false;
    boolean counterB = false;
    String firstName;
    int badges;
    private static String jsonFilePokemon = "data/pokemon.json";
    private static String jsonFileTrainer = "data/trainer.json";
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object

    // Pokedex Application
    public Pokedex() throws IOException, ParseException {


    }

    // EFFECTS: Runs the Pokedex Application
    public void runPokedex() throws IOException, ParseException {

        while (keepGoing) {
            displayMenu();

        }

        System.out.println("\nShutting Down ....");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }


        System.out.println("\nGoodbye!");

    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() throws IOException, ParseException {

        System.out.println("\n");
        System.out.println("Welcome!");
        System.out.println("Menu:");
        System.out.println("1. Trainer Details");
        System.out.println("2. Search Pokemon");
        System.out.println("3. Random Pokemon!");
        System.out.println("4. Exit");
        System.out.println("Please enter your option");
        int option;

        option = myObj.nextInt();
        if (option == 4) {
            keepGoing = false;
        } else {
            processOption(option);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user option for the main menu
    public void processOption(int option) throws IOException, ParseException {


        switch (option) {
            case 1: {
                trainerOptions();
                break;
            }
            case 2: {
                int id;
                System.out.println("Please enter the Pokemon ID");
                id = myObj.nextInt();
                Pokemon p = searchPokemon(jsonFilePokemon, id);
                printPokemonDetails(p);
                break;

            }
            case 3: {
                randomPokemon();
                break;
            }
            default:
                System.out.println("Please enter a valid option");
                break;
        }
    }

    //REQUIRES: Trainer already registered in the system
    //EFFECTS: Welcomes back the trainer
    public void existingTrainer() throws IOException, ParseException {
        Trainer exTrainer = readTrainer(jsonFileTrainer);
        firstName = exTrainer.getTrainerFirstName();
        System.out.println();
        System.out.println("Welcome back! " + firstName);
        existingTrainerOptions();

    }

    // REQUIRES: Trainer isn't registered in the system
    // MODIFIES: this
    // EFFECTS: Registers the new trainer and stores the details in a JSON file
    public void newTrainer() throws IOException, ParseException {

        System.out.println("\nPlease choose a 3 digit trainer ID ");
        int id = myObj.nextInt();

        trainer1.setTrainerID(id);


        System.out.println("\nPlease enter your first name ");
        String firstName = myObj.next();
        trainer1.setTrainerFirstName(firstName);


        System.out.println("\nPlease enter your last name");
        String lastName = myObj.next();
        trainer1.setTrainerLastName(lastName);

        trainerRegisterBadges();

        PokemonManager pokemonManager = new PokemonManager();
        pokemonManager.catchPokemon(trainer1);

        trainer1.setNew(false);
        writeJson(jsonFileTrainer, trainer1);


    }


    // EFFECTS: Confirms if the new trainer wants to overwrite the details of the previous one
    public void checkDelete() throws IOException, ParseException {
        Trainer exTrainer = readTrainer(jsonFileTrainer);
        String ch;
        if (!exTrainer.isNew()) {
            System.out.println("\nAre you sure? You will delete the data of the previous trainer. (Y/N)");
            ch = myObj.next();

            if (ch.equalsIgnoreCase("Y")) {
                newTrainer();
            } else {
                trainerOptions();
            }
        } else {
            newTrainer();
        }
    }


    //HELPER
    //MODIFIES: this
    //REQUIRES: Trainer isn't registered in the system
    //EFFECTS: Register the new trainer's badges
    public void trainerRegisterBadges() {
        while (!counterB) {

            System.out.println("\nPlease enter the number of badges collected (0-8) ");
            badges = myObj.nextInt();
            if ((badges < 9) && (badges >= 0)) {

                trainer1.setTotalBadge(badges);
                counterB = true;
            } else {
                System.out.println("There can be only 8 badges");
            }
        }
    }

    //REQUIRES: Trainer already registered in the system
    //EFFECTS: Displays the sub-menu for a registered trainer
    public void existingTrainerOptions() throws IOException, ParseException {
        boolean flag = false;
        while (!flag) {


            System.out.println("\n");
            System.out.println("Menu:");
            System.out.println("1. View Trainer Details");
            System.out.println("2. View Pokemon");
            System.out.println("3. Catch Pokemon");
            System.out.println("4. Add Badge");
            System.out.println("5. Release Pokemon");
            System.out.println("6. Main Menu");
            System.out.println("\n Please enter your choice \n");
            int option;

            option = myObj.nextInt();
            if (option == 6) {
                flag = true;
            } else {

                command(option);
            }
        }
        displayMenu();
    }

    // REQUIRES: Trainer already registered in the system
    // MODIFIES: this
    // EFFECTS: processes user option for the sub-menu (trainer menu)
    public void command(int option) throws IOException, ParseException {
        Trainer exTrainer = readTrainer(jsonFileTrainer);
        if (option == 1) {
            exTrainer.printDetails();
        } else if (option == 2) {
            try {
                exTrainer.printPokemon();
            } catch (NotCaughtException e) {
                System.out.println("You haven't caught any pokemon");
            }
        } else if (option == 3) {
            addPokemon();
        } else if (option == 4) {
            addBadges(exTrainer);
        } else if (option == 5) {
            release(exTrainer);
        } else if (option == 6) {
            flag = true;
        } else {
            System.out.println("Please enter a valid option");
        }

    }

    //Helper
    public void addBadges(Trainer exTrainer) throws IOException {
        boolean b = false;
        try {
            b = exTrainer.addBadge();
        } catch (AllBadges e) {
            System.out.println("You haven't caught this pokemon");
        }
        if (b) {
            writeJson(jsonFileTrainer, exTrainer);
        }
    }

    //Helper
    public void release(Trainer exTrainer) throws IOException {
        boolean a = false;
        PokemonManager pokemonManager = new PokemonManager();
        a = pokemonManager.release(exTrainer);
        if (a) {
            writeJson(jsonFilePokemon, exTrainer);
        }
    }

    // REQUIRES: Trainer registered in the system
    // MODIFIES: this
    // EFFECTS: Searches for the pokemon, adds it to the trainer object and writes it to the json file
    public void addPokemon() throws IOException, ParseException {
        Trainer exTrainer = readTrainer(jsonFileTrainer);
        PokemonManager pokemonManager = new PokemonManager();
        boolean a = pokemonManager.catchPokemon(exTrainer);
        if (a) {
            writeJson(jsonFilePokemon, exTrainer);
        }
    }

    //MODIFIES: this
    //EFFECTS: Displays the initial (new/existing) trainer menu
    public void trainerOptions() throws IOException, ParseException {

        System.out.println("\n1. Existing Trainer \n2. New Trainer");
        System.out.println("Please enter your option");
        int choice = myObj.nextInt();
        Trainer trainer = readTrainer(jsonFileTrainer);
        switch (choice) {
            case 1: {
                if (trainer.isNew()) {
                    checkDelete();
                } else {
                    existingTrainer();
                }
                break;
            }
            case 2: {
                checkDelete();
                break;
            }
            default: {
                System.out.println("Please enter a valid option");
                displayMenu();
                break;
            }
        }
    }

    //EFFECTS: Chooses a random pokemon from the database and displays it.
    public void randomPokemon() {
        Random rand = new Random();
        int randnum = rand.nextInt(151);
        randnum++;
        Pokemon p = searchPokemon(jsonFilePokemon, randnum);
        printPokemonDetails(p);
    }

    //EFFECTS: Displays the details of a pokemon given to it
    public void printPokemonDetails(Pokemon p) {
        System.out.println("Name: " + p.getPokemonName());
        System.out.println("Number: " + p.getPokeID());
        System.out.println("Weight: " + p.getWeight());
        System.out.println("Height: " + p.getHeight());
        System.out.println("Type: " + p.getPokemonType());
    }
}



