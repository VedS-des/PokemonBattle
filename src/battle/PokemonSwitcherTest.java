package battle;

import data.TrainerDatabase;
import model.Pokemon;
import model.Trainer;

public class PokemonSwitcherTest {

    public static void main(String[] args) {

        Trainer enemy = TrainerDatabase.getRed();

        Pokemon first = enemy.getPokemon(0);

        System.out.println("First Pokemon: " + first.getName());

        first.setCurrentHP(0);

        Pokemon next = PokemonSwitcher.getNextPokemon(enemy);

        System.out.println("Next Pokemon: " + next.getName());
    }
}