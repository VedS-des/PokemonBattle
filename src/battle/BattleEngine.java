package battle;

import model.Move;
import model.Pokemon;
import model.Trainer;

public class BattleEngine {

    private final BattleState state;
    private Move lastEnemyMove;
    private Move preparedEnemyMove;
    private boolean playerAttacksFirst;

    public BattleEngine(Trainer player, Trainer enemy) {
        this.state = new BattleState(player, enemy);
    }

    public BattleState getState() {
        return state;
    }

    public void executeTurn(Move playerMove) {

        if (state.getPlayer().isDefeated()
                || state.getEnemy().isDefeated()) {
            return;
        }

        if (playerMove == null) {
            return;
        }

        prepareTurn(playerMove);
    }

    private void updateActivePokemon() {

        if (state.getPlayerPokemon().isFainted()
                && !state.getPlayer().isDefeated()) {

            Pokemon nextPlayer = PokemonSwitcher.getNextPokemon(
                    state.getPlayer()
            );

            state.setPlayerPokemon(nextPlayer);
        }

        if (state.getEnemyPokemon().isFainted()
                && !state.getEnemy().isDefeated()) {

            Pokemon nextEnemy = PokemonSwitcher.getNextPokemon(
                    state.getEnemy()
            );

            state.setEnemyPokemon(nextEnemy);
        }
    }

    public boolean isBattleOver() {
        return state.getPlayer().isDefeated()
                || state.getEnemy().isDefeated();
    }
    public Move getLastEnemyMove() {
        return lastEnemyMove;
    }
    public void executeEnemyMove(Move enemyMove) {

        if (enemyMove == null) {
            return;
        }

        MoveExecutor.executeMove(
                state.getEnemyPokemon(),
                state.getPlayerPokemon(),
                enemyMove
        );

        updateActivePokemon();
    }
    public void prepareTurn(Move playerMove) {

        Pokemon playerPokemon = state.getPlayerPokemon();
        Pokemon enemyPokemon = state.getEnemyPokemon();

        playerAttacksFirst =
                TurnManager.getFirstAttacker(
                        playerPokemon,
                        enemyPokemon
                ) == playerPokemon;

        preparedEnemyMove =
                BattleTurn.chooseEnemyMove(enemyPokemon);
    }

    public boolean doesPlayerAttackFirst() {
        return playerAttacksFirst;
    }

    public Move getPreparedEnemyMove() {
        return preparedEnemyMove;
    }

    public void executeSingleMove(
            Pokemon attacker,
            Pokemon defender,
            Move move) {

        if (attacker == null
                || defender == null
                || move == null) {
            return;
        }

        MoveExecutor.executeMove(
                attacker,
                defender,
                move
        );

        updateActivePokemon();
    }
}
