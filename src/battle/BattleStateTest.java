package battle;

import data.TrainerDatabase;
import model.Trainer;

public class BattleStateTest {

    public static void main(String[] args) {

        Trainer player = TrainerDatabase.createPlayerTrainerByNames(
                "Ankit",
                "Gengar",
                "Charizard",
                "Lucario"
        );

        Trainer enemy = TrainerDatabase.getRed();

        BattleState state = new BattleState(player, enemy);

        System.out.println("Player: " + state.getPlayer().getName());
        System.out.println("Enemy: " + state.getEnemy().getName());

        System.out.println("Player active Pokemon: "
                + state.getPlayerPokemon().getName());

        System.out.println("Enemy active Pokemon: "
                + state.getEnemyPokemon().getName());
    }
}
