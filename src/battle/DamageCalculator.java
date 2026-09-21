package battle;

import model.Move;
import model.Pokemon;

public class DamageCalculator {

    public static int calculateDamage(Pokemon attacker, Pokemon defender, Move move) {

        int attack = attacker.getAttack();
        int defense = defender.getDefense();
        int power = move.getPower();

        if (power <= 0) {
            return 0;
        }

        int damage = (attack * power) / defense;

        double effectiveness = getTypeEffectiveness(
                move.getType(),
                defender.getType1(),
                defender.getType2()
        );

        damage = (int) (damage * effectiveness);

        if (damage < 1 && effectiveness > 0) {
            damage = 1;
        }

        return damage;
    }

    private static double getTypeEffectiveness(
            String moveType,
            String defenderType1,
            String defenderType2) {

        double effectiveness = getSingleTypeEffectiveness(
                moveType,
                defenderType1
        );

        if (defenderType2 != null && !defenderType2.isEmpty()) {
            effectiveness *= getSingleTypeEffectiveness(
                    moveType,
                    defenderType2
            );
        }

        return effectiveness;
    }

    private static double getSingleTypeEffectiveness(
            String moveType,
            String defenderType) {

        // Normal
        if (moveType.equals("Normal")) {
            if (defenderType.equals("Ghost")) {
                return 0.0;
            }
            if (defenderType.equals("Rock") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
        }

        // Fire
        if (moveType.equals("Fire")) {
            if (defenderType.equals("Grass") ||
                defenderType.equals("Ice") ||
                defenderType.equals("Bug") ||
                defenderType.equals("Steel")) {
                return 2.0;
            }
            if (defenderType.equals("Fire") ||
                defenderType.equals("Water") ||
                defenderType.equals("Rock") ||
                defenderType.equals("Dragon")) {
                return 0.5;
            }
        }

        // Water
        if (moveType.equals("Water")) {
            if (defenderType.equals("Fire") ||
                defenderType.equals("Ground") ||
                defenderType.equals("Rock")) {
                return 2.0;
            }
            if (defenderType.equals("Water") ||
                defenderType.equals("Grass") ||
                defenderType.equals("Dragon")) {
                return 0.5;
            }
        }

        // Electric
        if (moveType.equals("Electric")) {
            if (defenderType.equals("Water") ||
                defenderType.equals("Flying")) {
                return 2.0;
            }
            if (defenderType.equals("Electric") ||
                defenderType.equals("Grass") ||
                defenderType.equals("Dragon")) {
                return 0.5;
            }
            if (defenderType.equals("Ground")) {
                return 0.0;
            }
        }

        // Grass
        if (moveType.equals("Grass")) {
            if (defenderType.equals("Water") ||
                defenderType.equals("Ground") ||
                defenderType.equals("Rock")) {
                return 2.0;
            }
            if (defenderType.equals("Fire") ||
                defenderType.equals("Grass") ||
                defenderType.equals("Poison") ||
                defenderType.equals("Flying") ||
                defenderType.equals("Bug") ||
                defenderType.equals("Dragon") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
        }

        // Ice
        if (moveType.equals("Ice")) {
            if (defenderType.equals("Grass") ||
                defenderType.equals("Ground") ||
                defenderType.equals("Flying") ||
                defenderType.equals("Dragon")) {
                return 2.0;
            }
            if (defenderType.equals("Fire") ||
                defenderType.equals("Water") ||
                defenderType.equals("Ice") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
        }

        // Fighting
        if (moveType.equals("Fighting")) {
            if (defenderType.equals("Normal") ||
                defenderType.equals("Ice") ||
                defenderType.equals("Rock") ||
                defenderType.equals("Dark") ||
                defenderType.equals("Steel")) {
                return 2.0;
            }
            if (defenderType.equals("Poison") ||
                defenderType.equals("Flying") ||
                defenderType.equals("Psychic") ||
                defenderType.equals("Bug") ||
                defenderType.equals("Fairy")) {
                return 0.5;
            }
            if (defenderType.equals("Ghost")) {
                return 0.0;
            }
        }

        // Poison
        if (moveType.equals("Poison")) {
            if (defenderType.equals("Grass") ||
                defenderType.equals("Fairy")) {
                return 2.0;
            }
            if (defenderType.equals("Poison") ||
                defenderType.equals("Ground") ||
                defenderType.equals("Rock") ||
                defenderType.equals("Ghost")) {
                return 0.5;
            }
            if (defenderType.equals("Steel")) {
                return 0.0;
            }
        }

        // Ground
        if (moveType.equals("Ground")) {
            if (defenderType.equals("Fire") ||
                defenderType.equals("Electric") ||
                defenderType.equals("Poison") ||
                defenderType.equals("Rock") ||
                defenderType.equals("Steel")) {
                return 2.0;
            }
            if (defenderType.equals("Grass") ||
                defenderType.equals("Bug")) {
                return 0.5;
            }
            if (defenderType.equals("Flying")) {
                return 0.0;
            }
        }

        // Flying
        if (moveType.equals("Flying")) {
            if (defenderType.equals("Grass") ||
                defenderType.equals("Fighting") ||
                defenderType.equals("Bug")) {
                return 2.0;
            }
            if (defenderType.equals("Electric") ||
                defenderType.equals("Rock") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
        }

        // Psychic
        if (moveType.equals("Psychic")) {
            if (defenderType.equals("Fighting") ||
                defenderType.equals("Poison")) {
                return 2.0;
            }
            if (defenderType.equals("Psychic") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
            if (defenderType.equals("Dark")) {
                return 0.0;
            }
        }

        // Bug
        if (moveType.equals("Bug")) {
            if (defenderType.equals("Grass") ||
                defenderType.equals("Psychic") ||
                defenderType.equals("Dark")) {
                return 2.0;
            }
            if (defenderType.equals("Fire") ||
                defenderType.equals("Fighting") ||
                defenderType.equals("Poison") ||
                defenderType.equals("Flying") ||
                defenderType.equals("Ghost") ||
                defenderType.equals("Steel") ||
                defenderType.equals("Fairy")) {
                return 0.5;
            }
        }

        // Rock
        if (moveType.equals("Rock")) {
            if (defenderType.equals("Fire") ||
                defenderType.equals("Ice") ||
                defenderType.equals("Flying") ||
                defenderType.equals("Bug")) {
                return 2.0;
            }
            if (defenderType.equals("Fighting") ||
                defenderType.equals("Ground") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
        }

        // Ghost
        if (moveType.equals("Ghost")) {
            if (defenderType.equals("Psychic") ||
                defenderType.equals("Ghost")) {
                return 2.0;
            }
            if (defenderType.equals("Dark")) {
                return 0.5;
            }
            if (defenderType.equals("Normal")) {
                return 0.0;
            }
        }

        // Dragon
        if (moveType.equals("Dragon")) {
            if (defenderType.equals("Dragon")) {
                return 2.0;
            }
            if (defenderType.equals("Steel")) {
                return 0.5;
            }
            if (defenderType.equals("Fairy")) {
                return 0.0;
            }
        }

        // Dark
        if (moveType.equals("Dark")) {
            if (defenderType.equals("Psychic") ||
                defenderType.equals("Ghost")) {
                return 2.0;
            }
            if (defenderType.equals("Fighting") ||
                defenderType.equals("Dark") ||
                defenderType.equals("Fairy")) {
                return 0.5;
            }
        }

        // Steel
        if (moveType.equals("Steel")) {
            if (defenderType.equals("Ice") ||
                defenderType.equals("Rock") ||
                defenderType.equals("Fairy")) {
                return 2.0;
            }
            if (defenderType.equals("Fire") ||
                defenderType.equals("Water") ||
                defenderType.equals("Electric") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
        }

        // Fairy
        if (moveType.equals("Fairy")) {
            if (defenderType.equals("Fighting") ||
                defenderType.equals("Dragon") ||
                defenderType.equals("Dark")) {
                return 2.0;
            }
            if (defenderType.equals("Fire") ||
                defenderType.equals("Poison") ||
                defenderType.equals("Steel")) {
                return 0.5;
            }
        }

        return 1.0;
    }
}