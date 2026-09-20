package data;

import model.Pokemon;
import model.Trainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Single official database storing the three opponent trainers
 * (Red, Steven, Cynthia) and their exact 3-Pokémon teams.
 */
public class TrainerDatabase {

    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    /**
     * Creates and returns Red (Easy):
     * 1. Lapras
     * 2. Snorlax
     * 3. Charizard
     */
    public static Trainer getRed() {
        Pokemon[] team = new Pokemon[]{
                PokemonDatabase.createLapras(),
                PokemonDatabase.createSnorlax(),
                PokemonDatabase.createCharizard()
        };
        return new Trainer("Red", DIFFICULTY_EASY, team);
    }

    /**
     * Creates and returns Steven (Medium):
     * 1. Metagross
     * 2. Claydol
     * 3. Cradily
     */
    public static Trainer getSteven() {
        Pokemon[] team = new Pokemon[]{
                PokemonDatabase.createMetagross(),
                PokemonDatabase.createClaydol(),
                PokemonDatabase.createCradily()
        };
        return new Trainer("Steven", DIFFICULTY_MEDIUM, team);
    }

    /**
     * Creates and returns Cynthia (Hard):
     * 1. Togekiss
     * 2. Garchomp
     * 3. Spiritomb
     */
    public static Trainer getCynthia() {
        Pokemon[] team = new Pokemon[]{
                PokemonDatabase.createTogekiss(),
                PokemonDatabase.createGarchomp(),
                PokemonDatabase.createSpiritomb()
        };
        return new Trainer("Cynthia", DIFFICULTY_HARD, team);
    }

    /**
     * Looks up an opponent trainer by name (case-insensitive).
     *
     * @param name Trainer name ("Red", "Steven", or "Cynthia")
     * @return Fresh Trainer instance, or null if not found
     */
    public static Trainer getTrainer(String name) {
        if (name == null) return null;
        switch (name.trim().toLowerCase()) {
            case "red":
                return getRed();
            case "steven":
                return getSteven();
            case "cynthia":
                return getCynthia();
            default:
                return null;
        }
    }

    /**
     * Checks if an opponent trainer exists by name.
     */
    public static boolean hasTrainer(String name) {
        if (name == null) return false;
        String n = name.trim().toLowerCase();
        return "red".equals(n) || "steven".equals(n) || "cynthia".equals(n);
    }

    /**
     * Returns a list containing fresh instances of all three opponent trainers
     * ordered by difficulty: Red (Easy), Steven (Medium), Cynthia (Hard).
     */
    public static List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>(3);
        trainers.add(getRed());
        trainers.add(getSteven());
        trainers.add(getCynthia());
        return Collections.unmodifiableList(trainers);
    }
}
