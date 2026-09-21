package battle;

import data.PokemonDatabase;
import model.Pokemon;

public class BattleTurnTest {

    public static void main(String[] args) {

        Pokemon gengar = PokemonDatabase.getPokemon("Gengar");
        Pokemon charizard = PokemonDatabase.getPokemon("Charizard");

        System.out.println("Before turn:");
        System.out.println("Gengar HP: " + gengar.getCurrentHP());
        System.out.println("Charizard HP: " + charizard.getCurrentHP());

        System.out.println();
        System.out.println("=== Battle Turn ===");

        BattleTurn.executeTurn(gengar, charizard);

        System.out.println();
        System.out.println("After turn:");
        System.out.println("Gengar HP: " + gengar.getCurrentHP());
        System.out.println("Charizard HP: " + charizard.getCurrentHP());
    }
}
