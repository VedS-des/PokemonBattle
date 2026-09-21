package ai;

import data.PokemonDatabase;
import model.Move;
import model.Pokemon;

public class AIPlayerTest {

    public static void main(String[] args) {

        Pokemon gengar = PokemonDatabase.getPokemon("Gengar");

        Move selectedMove = AIPlayer.chooseMove(gengar);

        System.out.println("Pokemon: " + gengar.getName());
        System.out.println("AI selected move: " + selectedMove.getName());
        System.out.println("Move type: " + selectedMove.getType());
        System.out.println("Move power: " + selectedMove.getPower());
    }
}
