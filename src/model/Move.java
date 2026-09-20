package model;

/**
 * Represents a Pokémon move with its name, type, power, accuracy,
 * and optional healing effect (e.g., Rest).
 */
public class Move {
    private final String name;
    private final String type;
    private final int power;
    private final int accuracy;
    private final int healAmount; // For moves like Rest (restores 30 HP)

    /**
     * Standard constructor for damaging / non-healing moves.
     *
     * @param name     Move name
     * @param type     Move elemental type (e.g., "Fire", "Water")
     * @param power    Base power of the move
     * @param accuracy Accuracy percentage (e.g., 100 for 100%)
     */
    public Move(String name, String type, int power, int accuracy) {
        this(name, type, power, accuracy, 0);
    }

    /**
     * Constructor for moves with healing effects (such as Rest).
     *
     * @param name       Move name
     * @param type       Move elemental type
     * @param power      Base power of the move
     * @param accuracy   Accuracy percentage
     * @param healAmount Amount of HP restored by this move (e.g., 30 for Rest)
     */
    public Move(String name, String type, int power, int accuracy, int healAmount) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.healAmount = healAmount;
    }

    /**
     * Copy constructor.
     *
     * @param other Move to copy
     */
    public Move(Move other) {
        this(other.name, other.type, other.power, other.accuracy, other.healAmount);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public boolean isHealingMove() {
        return healAmount > 0;
    }

    @Override
    public String toString() {
        if (isHealingMove()) {
            return name + " (" + type + " | Heal: " + healAmount + " HP | Acc: " + accuracy + "%)";
        }
        return name + " (" + type + " | Power: " + power + " | Acc: " + accuracy + "%)";
    }
}
