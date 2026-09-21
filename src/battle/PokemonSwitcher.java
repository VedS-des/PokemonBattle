package battle;

import model.Pokemon;
import model.Trainer;

public class PokemonSwitcher {

    public static Pokemon getNextPokemon(Trainer trainer) {

        if (trainer == null || trainer.isDefeated()) {
            return null;
        }

        return trainer.getActivePokemon();
    }
}