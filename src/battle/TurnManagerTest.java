package battle;

import data.PokemonDatabase;
import model.Pokemon;

public class TurnManagerTest {

    public static void main(String[] args) {

        Pokemon gengar = PokemonDatabase.getPokemon("Gengar");
        Pokemon charizard = PokemonDatabase.getPokemon("Charizard");

        Pokemon first = TurnManager.getFirstAttacker(gengar, charizard);
        Pokemon second = TurnManager.getSecondAttacker(gengar, charizard);

        System.out.println("Gengar Speed: " + gengar.getSpeed());
        System.out.println("Charizard Speed: " + charizard.getSpeed());

        System.out.println("First attacker: " + first.getName());
        System.out.println("Second attacker: " + second.getName());
    }
}
