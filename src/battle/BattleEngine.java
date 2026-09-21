package battle;

import model.Move;
import model.Pokemon;
import model.Trainer;

public class BattleEngine {

    private final BattleState state;

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

        BattleTurn.executeTurn(
                state.getPlayerPokemon(),
                state.getEnemyPokemon(),
                playerMove
        );

        updateActivePokemon();
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
}
