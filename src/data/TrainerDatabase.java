package data;

import model.Pokemon;
import model.Trainer;

import java.util.ArrayList;
import java.util.Arrays;
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

    // --- Player Team Creation & Validation ---

    /**
     * Checks if a list of Pokémon represents a valid player team
     * (exactly 3 non-null Pokémon, all from the 6 eligible player Pokémon).
     */
    public static boolean isValidPlayerTeam(List<Pokemon> team) {
        if (team == null || team.size() != Trainer.TEAM_SIZE) {
            return false;
        }
        for (Pokemon p : team) {
            if (p == null || !PokemonDatabase.isPlayerPokemon(p)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates a player team, throwing an IllegalArgumentException if invalid.
     */
    public static void validatePlayerTeam(List<Pokemon> team) {
        if (team == null || team.size() != Trainer.TEAM_SIZE) {
            throw new IllegalArgumentException("Player team must contain exactly " + Trainer.TEAM_SIZE + " Pokémon.");
        }
        for (Pokemon p : team) {
            if (p == null) {
                throw new IllegalArgumentException("Pokémon in player team cannot be null.");
            }
            if (!PokemonDatabase.isPlayerPokemon(p)) {
                throw new IllegalArgumentException("Pokémon '" + p.getName() + "' is not among the 6 eligible player Pokémon.");
            }
        }
    }

    /**
     * Creates a Player Trainer with a validated team of 3 Pokémon.
     *
     * @param playerName   Name of the player (defaults to "Player" if null or blank)
     * @param selectedTeam Exactly 3 Pokémon selected from the 6 player Pokémon
     * @return New Trainer instance for the player
     * @throws IllegalArgumentException if the team does not meet player selection criteria
     */
    public static Trainer createPlayerTrainer(String playerName, List<Pokemon> selectedTeam) {
        validatePlayerTeam(selectedTeam);
        String name = (playerName != null && !playerName.trim().isEmpty()) ? playerName.trim() : "Player";
        return new Trainer(name, selectedTeam);
    }

    /**
     * Creates a Player Trainer with a validated team of 3 Pokémon (varargs overload).
     */
    public static Trainer createPlayerTrainer(String playerName, Pokemon... selectedTeam) {
        if (selectedTeam == null) {
            throw new IllegalArgumentException("Selected team cannot be null.");
        }
        return createPlayerTrainer(playerName, Arrays.asList(selectedTeam));
    }

    /**
     * Creates a Player Trainer by selecting 3 Pokémon by their names.
     *
     * @param playerName           Name of the player
     * @param selectedPokemonNames Exactly 3 names of eligible player Pokémon
     * @return New Trainer instance for the player
     * @throws IllegalArgumentException if invalid
     */
    public static Trainer createPlayerTrainerByNames(String playerName, List<String> selectedPokemonNames) {
        if (selectedPokemonNames == null || selectedPokemonNames.size() != Trainer.TEAM_SIZE) {
            throw new IllegalArgumentException("Must provide exactly " + Trainer.TEAM_SIZE + " Pokémon names.");
        }
        List<Pokemon> team = new ArrayList<>(Trainer.TEAM_SIZE);
        for (String pName : selectedPokemonNames) {
            if (pName == null || !PokemonDatabase.isPlayerPokemon(pName)) {
                throw new IllegalArgumentException("Pokémon name '" + pName + "' is not an eligible player Pokémon.");
            }
            team.add(PokemonDatabase.getPokemon(pName));
        }
        return createPlayerTrainer(playerName, team);
    }

    /**
     * Creates a Player Trainer by selecting 3 Pokémon by their names (varargs overload).
     */
    public static Trainer createPlayerTrainerByNames(String playerName, String... selectedPokemonNames) {
        if (selectedPokemonNames == null) {
            throw new IllegalArgumentException("Selected Pokémon names cannot be null.");
        }
        return createPlayerTrainerByNames(playerName, Arrays.asList(selectedPokemonNames));
    }
}
