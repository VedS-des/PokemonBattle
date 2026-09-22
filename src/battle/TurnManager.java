package battle;

import model.Pokemon;

public class TurnManager {

    public static Pokemon getFirstAttacker(Pokemon pokemon1, Pokemon pokemon2) {

        if (pokemon1.getSpeed() >= pokemon2.getSpeed()) {
            return pokemon1;
        }

        return pokemon2;
    }

    public static Pokemon getSecondAttacker(Pokemon pokemon1, Pokemon pokemon2) {

        if (pokemon1.getSpeed() >= pokemon2.getSpeed()) {
            return pokemon2;
        }

        return pokemon1;
    }
}
