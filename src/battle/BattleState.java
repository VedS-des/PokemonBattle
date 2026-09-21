package battle;

import model.Pokemon;
import model.Trainer;

public class BattleState {

    private final Trainer player;
    private final Trainer enemy;

    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;

    public BattleState(Trainer player, Trainer enemy) {
        this.player = player;
        this.enemy = enemy;

        this.playerPokemon = player.getActivePokemon();
        this.enemyPokemon = enemy.getActivePokemon();
    }

    public Trainer getPlayer() {
        return player;
    }

    public Trainer getEnemy() {
        return enemy;
    }

    public Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public Pokemon getEnemyPokemon() {
        return enemyPokemon;
    }

    public void setPlayerPokemon(Pokemon pokemon) {
        this.playerPokemon = pokemon;
    }

    public void setEnemyPokemon(Pokemon pokemon) {
        this.enemyPokemon = pokemon;
    }
}
