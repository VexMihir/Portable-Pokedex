package ui;

import kanto.exceptions.NotCaughtException;
import kanto.model.Trainer;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class PokemonManager {
    Scanner myObj = new Scanner(System.in);

    public boolean catchPokemon(Trainer trainer) throws IOException, ParseException {

        System.out.println("Enter the pokemon ID you want to add (between 1 and 151)");
        int id = myObj.nextInt();
        boolean a = trainer.catchPokemons(id);
        return a;
    }

    public boolean release(Trainer trainer) {
        System.out.println("Enter the pokemon ID you want to release");
        int id = myObj.nextInt();
        boolean a = false;
        try {
            a = trainer.releasePokemon(id);
        } catch (NotCaughtException e) {
            System.out.println("You haven't caught this pokemon");
        }

        return a;
    }
}
