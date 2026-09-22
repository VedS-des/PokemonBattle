package battle;

import data.PokemonDatabase;
import model.Move;
import model.Pokemon;

public class RestTest {

    public static void main(String[] args) {

        Pokemon snorlax = PokemonDatabase.getPokemon("Snorlax");

        snorlax.setCurrentHP(100);

        Move rest = snorlax.getMove(3);

        System.out.println("Before Rest:");
        System.out.println("HP: " + snorlax.getCurrentHP());
        System.out.println("Move: " + rest.getName());
        System.out.println("Heal Amount: " + rest.getHealAmount());

        MoveExecutor.executeMove(snorlax, snorlax, rest);

        System.out.println("After Rest:");
        System.out.println("HP: " + snorlax.getCurrentHP());
    }
}
