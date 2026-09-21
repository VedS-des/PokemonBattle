package battle;

import ai.AIPlayer;
import model.Move;
import model.Pokemon;

public class BattleTurn {

    public static void executeTurn(Pokemon pokemon1, Pokemon pokemon2) {

        Pokemon firstAttacker = TurnManager.getFirstAttacker(pokemon1, pokemon2);
        Pokemon secondAttacker = TurnManager.getSecondAttacker(pokemon1, pokemon2);

        Move firstMove = AIPlayer.chooseMove(firstAttacker);
        MoveExecutor.executeMove(firstAttacker, secondAttacker, firstMove);

        if (secondAttacker.isFainted()) {
            return;
        }

        Move secondMove = AIPlayer.chooseMove(secondAttacker);
        MoveExecutor.executeMove(secondAttacker, firstAttacker, secondMove);
    }
}
