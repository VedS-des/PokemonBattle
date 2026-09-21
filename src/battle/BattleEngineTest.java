package battle;

import data.TrainerDatabase;
import model.Pokemon;
import model.Trainer;

public class BattleEngineTest {

    public static void main(String[] args) {

        Trainer player = TrainerDatabase.createPlayerTrainerByNames(
                "Ankit",
                "Gengar",
                "Charizard",
                "Lucario"
        );

        Trainer enemy = TrainerDatabase.getRed();

        BattleEngine engine = new BattleEngine(player, enemy);

        System.out.println("Player: " + player.getName());
        System.out.println("Enemy: " + enemy.getName());

        System.out.println("Player active Pokemon: "
                + engine.getState().getPlayerPokemon().getName());

        System.out.println("Enemy active Pokemon: "
                + engine.getState().getEnemyPokemon().getName());

        System.out.println();

        for (int turn = 1; turn <= 20 && !engine.isBattleOver(); turn++) {

            Pokemon playerBefore = engine.getState().getPlayerPokemon();
            Pokemon enemyBefore = engine.getState().getEnemyPokemon();

            System.out.println("----- Turn " + turn + " -----");

            engine.executeTurn();

            Pokemon playerAfter = engine.getState().getPlayerPokemon();
            Pokemon enemyAfter = engine.getState().getEnemyPokemon();

            System.out.println("Player team remaining: "
                    + player.getRemainingCount());

            System.out.println("Player team:");

            for (int i = 0; i < 3; i++) {
                Pokemon p = player.getPokemon(i);

                System.out.println(
                        "  " + p.getName()
                        + " HP: "
                        + p.getCurrentHP()
                        + "/"
                        + p.getMaxHP()
                        + " Fainted: "
                        + p.isFainted()
                );
            }

            if (playerAfter == null) {
                System.out.println("ERROR: Player active Pokemon is null.");
                break;
            }

            if (enemyAfter == null) {
                System.out.println("ERROR: Enemy active Pokemon is null.");
                break;
            }

            if (playerBefore != playerAfter) {
                System.out.println("Player switched to: "
                        + playerAfter.getName());
            }

            if (enemyBefore != enemyAfter) {
                System.out.println("Enemy switched to: "
                        + enemyAfter.getName());
            }

            System.out.println("Player active: "
                    + playerAfter.getName()
                    + " HP: "
                    + playerAfter.getCurrentHP());

            System.out.println("Enemy active: "
                    + enemyAfter.getName()
                    + " HP: "
                    + enemyAfter.getCurrentHP());

            System.out.println();
        }

        System.out.println("Battle over: " + engine.isBattleOver());
    }
}
