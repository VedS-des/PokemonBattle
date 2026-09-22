package battle;

import data.PokemonDatabase;
import model.Move;
import model.Pokemon;

public class MoveExecutorTest {

    public static void main(String[] args) {

        Pokemon attacker = PokemonDatabase.getPokemon("Charizard");
        Pokemon defender = PokemonDatabase.getPokemon("Gardevoir");

        Move move = attacker.getMove(0);

        System.out.println("Before attack:");
        System.out.println(defender.getName() + " HP: " + defender.getCurrentHP());

        MoveExecutor.executeMove(attacker, defender, move);

        System.out.println("After attack:");
        System.out.println(defender.getName() + " HP: " + defender.getCurrentHP());
    }
}
