package battle;

import data.PokemonDatabase;
import model.Move;
import model.Pokemon;

public class DamageCalculatorTest {

    public static void main(String[] args) {

        Pokemon attacker = PokemonDatabase.getPokemon("Charizard");
        Pokemon defender = PokemonDatabase.getPokemon("Gardevoir");

        Move move = attacker.getMove(0);

        int damage = DamageCalculator.calculateDamage(
                attacker,
                defender,
                move
        );

        System.out.println("Attacker: " + attacker.getName());
        System.out.println("Defender: " + defender.getName());
        System.out.println("Move: " + move.getName());
        System.out.println("Damage: " + damage);
    }
}