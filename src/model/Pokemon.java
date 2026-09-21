package model;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Pokémon with its stats, typing, 4 fixed moves,
 * current/max HP, and sprite reference for the frontend.
 */
public class Pokemon {
    private final String name;
    private final String type1;
    private final String type2; // null if single type
    private final int maxHP;
    private int currentHP;
    private final int attack;
    private final int defense;
    private final int speed;
    private final Move[] moves; // Exactly 4 fixed moves
    private String spriteReference;

    /**
     * Dual-type constructor with sprite reference.
     */
    public Pokemon(String name, String type1, String type2, int maxHP, int attack, int defense, int speed, Move[] moves, String spriteReference) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.spriteReference = spriteReference;

        this.moves = new Move[4];
        if (moves != null) {
            int count = Math.min(moves.length, 4);
            for (int i = 0; i < count; i++) {
                this.moves[i] = moves[i] != null ? new Move(moves[i]) : null;
            }
        }
    }

    /**
     * Dual-type constructor with default sprite reference.
     */
    public Pokemon(String name, String type1, String type2, int maxHP, int attack, int defense, int speed, Move[] moves) {
        this(name, type1, type2, maxHP, attack, defense, speed, moves, "sprites/" + name.toLowerCase() + ".png");
    }

    /**
     * Single-type constructor with default sprite reference.
     */
    public Pokemon(String name, String type1, int maxHP, int attack, int defense, int speed, Move[] moves) {
        this(name, type1, null, maxHP, attack, defense, speed, moves);
    }

    /**
     * Copy constructor for creating independent Pokémon instances for battles.
     *
     * @param other The Pokémon to copy
     */
    public Pokemon(Pokemon other) {
        this.name = other.name;
        this.type1 = other.type1;
        this.type2 = other.type2;
        this.maxHP = other.maxHP;
        this.currentHP = other.currentHP;
        this.attack = other.attack;
        this.defense = other.defense;
        this.speed = other.speed;
        this.spriteReference = other.spriteReference;

        this.moves = new Move[4];
        if (other.moves != null) {
            for (int i = 0; i < 4; i++) {
                this.moves[i] = other.moves[i] != null ? new Move(other.moves[i]) : null;
            }
        }
    }

    /**
     * Creates an independent copy of this Pokémon.
     */
    public Pokemon copy() {
        return new Pokemon(this);
    }

    // --- HP Handling & Battle State ---

    /**
     * Reduces current HP by specified damage amount, clamped at 0.
     *
     * @param damage Amount of damage to inflict
     */
    public void takeDamage(int damage) {
        if (damage < 0) {
            damage = 0;
        }
        this.currentHP = Math.max(0, this.currentHP - damage);
    }

    /**
     * Restores HP by specified amount. HP cannot exceed maximum HP.
     * (Supports special move effects such as Rest restoring 30 HP).
     *
     * @param amount Amount of HP to restore
     */
    public void heal(int amount) {
        if (amount < 0) {
            amount = 0;
        }
        this.currentHP = Math.min(this.maxHP, this.currentHP + amount);
    }

    /**
     * Checks if this Pokémon has fainted (current HP <= 0).
     */
    public boolean isFainted() {
        return this.currentHP <= 0;
    }

    /**
     * Restores current HP back to maximum HP.
     */
    public void resetHP() {
        this.currentHP = this.maxHP;
    }

    // --- Getters & Setters ---

    public String getName() {
        return name;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public boolean hasSecondType() {
        return type2 != null && !type2.trim().isEmpty();
    }

    /**
     * Checks if this Pokémon has the given type (either primary or secondary).
     */
    public boolean hasType(String type) {
        if (type == null) return false;
        if (type1 != null && type1.equalsIgnoreCase(type)) return true;
        return type2 != null && type2.equalsIgnoreCase(type);
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Sets current HP, bounded between 0 and maxHP.
     */
    public void setCurrentHP(int currentHP) {
        if (currentHP < 0) {
            this.currentHP = 0;
        } else if (currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        } else {
            this.currentHP = currentHP;
        }
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public Move[] getMoves() {
        return moves;
    }

    /**
     * Retrieves a move by slot index (0 to 3).
     */
    public Move getMove(int index) {
        if (index >= 0 && index < moves.length) {
            return moves[index];
        }
        return null;
    }

    /**
     * Sets a move in a specific slot (0 to 3).
     */
    public void setMove(int index, Move move) {
        if (index >= 0 && index < moves.length) {
            this.moves[index] = move;
        }
    }

    public List<Move> getMoveList() {
        return Arrays.asList(moves);
    }

    public String getSpriteReference() {
        return spriteReference;
    }

    public String getSpritePath() {
        return spriteReference;
    }

    public void setSpriteReference(String spriteReference) {
        this.spriteReference = spriteReference;
    }

    /**
     * Returns a user-friendly formatted string of the Pokémon's typing
     * (e.g. "Psychic / Fairy" or "Water").
     */
    public String getFormattedType() {
        return hasSecondType() ? (type1 + " / " + type2) : type1;
    }

    /**
     * Returns the current HP percentage as a ratio between 0.0 and 1.0
     * (convenient for UI health bars).
     */
    public double getHPPercent() {
        if (maxHP <= 0) return 0.0;
        return (double) currentHP / maxHP;
    }

    /**
     * Checks if the Pokémon is at maximum health.
     */
    public boolean isFullHP() {
        return currentHP == maxHP;
    }

    @Override
    public String toString() {
        String typeStr = hasSecondType() ? (type1 + "/" + type2) : type1;
        return name + " [" + typeStr + "] HP: " + currentHP + "/" + maxHP +
                " (ATK: " + attack + ", DEF: " + defense + ", SPD: " + speed + ")";
    }
}
