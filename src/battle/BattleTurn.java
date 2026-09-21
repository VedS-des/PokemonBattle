package battle;

import ai.AIPlayer;
import model.Move;
import model.Pokemon;

public class BattleTurn {

    public static void executeTurn(Pokemon pokemon1, Pokemon pokemon2) {

        Pokemon firstAttacker =
                TurnManager.getFirstAttacker(pokemon1, pokemon2);

        Pokemon secondAttacker =
                TurnManager.getSecondAttacker(pokemon1, pokemon2);

        Move firstMove = AIPlayer.chooseMove(firstAttacker);
        MoveExecutor.executeMove(
                firstAttacker,
                secondAttacker,
                firstMove
        );

        if (secondAttacker.isFainted()) {
            return;
        }

        Move secondMove = AIPlayer.chooseMove(secondAttacker);
        MoveExecutor.executeMove(
                secondAttacker,
                firstAttacker,
                secondMove
        );
    }

    public static void executeTurn(
            Pokemon playerPokemon,
            Pokemon enemyPokemon,
            Move playerMove) {

        Pokemon firstAttacker =
                TurnManager.getFirstAttacker(
                        playerPokemon,
                        enemyPokemon
                );

        Pokemon secondAttacker =
                TurnManager.getSecondAttacker(
                        playerPokemon,
                        enemyPokemon
                );

        Move enemyMove = AIPlayer.chooseMove(enemyPokemon);

        if (firstAttacker == playerPokemon) {

            MoveExecutor.executeMove(
                    playerPokemon,
                    enemyPokemon,
                    playerMove
            );

            if (enemyPokemon.isFainted()) {
                return;
            }

            MoveExecutor.executeMove(
                    enemyPokemon,
                    playerPokemon,
                    enemyMove
            );

        } else {

            MoveExecutor.executeMove(
                    enemyPokemon,
                    playerPokemon,
                    enemyMove
            );

            if (playerPokemon.isFainted()) {
                return;
            }

            MoveExecutor.executeMove(
                    playerPokemon,
                    enemyPokemon,
                    playerMove
            );
        }
    }
}
