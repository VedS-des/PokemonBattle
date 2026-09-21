package ai;

import model.Move;
import model.Pokemon;

import java.util.Random;

public class AIPlayer {

    private static final Random random = new Random();

    public static Move chooseMove(Pokemon pokemon) {

        if (pokemon == null || pokemon.getMoves() == null || pokemon.getMoves().length == 0) {
            return null;
        }

        int index = random.nextInt(pokemon.getMoves().length);
        return pokemon.getMoves()[index];
    }
}
