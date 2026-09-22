package battle;

import java.util.Random;

import model.Move;
import model.Pokemon;

public class MoveExecutor {

    private static final Random random = new Random();

    public static boolean executeMove(Pokemon attacker, Pokemon defender, Move move) {

        System.out.println(attacker.getName() + " used " + move.getName() + "!");

        int roll = random.nextInt(100) + 1;

        if (roll > move.getAccuracy()) {
            System.out.println("The move missed!");
            return false;
        }

        if (move.isHealingMove()) {
            int oldHP = attacker.getCurrentHP();

            attacker.heal(move.getHealAmount());

            int healed = attacker.getCurrentHP() - oldHP;

            System.out.println(attacker.getName() + " recovered " + healed + " HP.");

            return true;
        }

        int damage = DamageCalculator.calculateDamage(
                attacker,
                defender,
                move
        );

        defender.takeDamage(damage);

        System.out.println(
                defender.getName() + " took " + damage + " damage."
        );

        if (defender.isFainted()) {
            System.out.println(defender.getName() + " fainted!");
        }

        return true;
    }
}
