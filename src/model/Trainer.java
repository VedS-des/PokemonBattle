package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Trainer with a name, optional difficulty, and a team of exactly 3 Pokémon.
 */
public class Trainer {
    public static final int TEAM_SIZE = 3;

    private final String name;
    private final String difficulty;
    private final Pokemon[] team;

    /**
     * Constructor for Trainer with name, difficulty, and array of Pokémon.
     */
    public Trainer(String name, String difficulty, Pokemon[] team) {
        this.name = name;
        this.difficulty = difficulty;
        this.team = new Pokemon[TEAM_SIZE];
        if (team != null) {
            int count = Math.min(team.length, TEAM_SIZE);
            for (int i = 0; i < count; i++) {
                this.team[i] = team[i] != null ? team[i].copy() : null;
            }
        }
    }

    /**
     * Constructor for Trainer with name, difficulty, and list of Pokémon.
     */
    public Trainer(String name, String difficulty, List<Pokemon> teamList) {
        this.name = name;
        this.difficulty = difficulty;
        this.team = new Pokemon[TEAM_SIZE];
        if (teamList != null) {
            int count = Math.min(teamList.size(), TEAM_SIZE);
            for (int i = 0; i < count; i++) {
                Pokemon p = teamList.get(i);
                this.team[i] = p != null ? p.copy() : null;
            }
        }
    }

    /**
     * Constructor without difficulty (e.g., for player trainers).
     */
    public Trainer(String name, List<Pokemon> teamList) {
        this(name, null, teamList);
    }

    /**
     * Constructor without difficulty using an array.
     */
    public Trainer(String name, Pokemon[] team) {
        this(name, null, team);
    }

    /**
     * Copy constructor for creating independent trainer instances for battles.
     */
    public Trainer(Trainer other) {
        this.name = other.name;
        this.difficulty = other.difficulty;
        this.team = new Pokemon[TEAM_SIZE];
        if (other.team != null) {
            for (int i = 0; i < TEAM_SIZE; i++) {
                this.team[i] = other.team[i] != null ? other.team[i].copy() : null;
            }
        }
    }

    /**
     * Creates an independent deep copy of this Trainer.
     */
    public Trainer copy() {
        return new Trainer(this);
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Returns an unmodifiable list of the Pokémon in the team.
     */
    public List<Pokemon> getTeam() {
        return Collections.unmodifiableList(Arrays.asList(team));
    }

    /**
     * Returns the raw array of team Pokémon.
     */
    public Pokemon[] getTeamArray() {
        return team;
    }

    /**
     * Retrieves a Pokémon from the team by slot index (0 to 2).
     */
    public Pokemon getPokemon(int index) {
        if (index >= 0 && index < TEAM_SIZE) {
            return team[index];
        }
        return null;
    }

    /**
     * Sets a Pokémon in the team at the specified slot index (0 to 2).
     */
    public void setPokemon(int index, Pokemon pokemon) {
        if (index >= 0 && index < TEAM_SIZE) {
            this.team[index] = pokemon != null ? pokemon.copy() : null;
        }
    }

    /**
     * Returns the first non-fainted Pokémon in the team (active battler),
     * or null if all Pokémon are fainted.
     */
    public Pokemon getActivePokemon() {
        for (Pokemon p : team) {
            if (p != null && !p.isFainted()) {
                return p;
            }
        }
        return null;
    }

    /**
     * Checks if all Pokémon in the team have fainted.
     */
    public boolean isDefeated() {
        for (Pokemon p : team) {
            if (p != null && !p.isFainted()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the number of non-fainted Pokémon remaining in the team.
     */
    public int getRemainingCount() {
        int count = 0;
        for (Pokemon p : team) {
            if (p != null && !p.isFainted()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of fainted Pokémon in the team.
     */
    public int getFaintedCount() {
        int count = 0;
        for (Pokemon p : team) {
            if (p != null && p.isFainted()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trainer: ").append(name);
        if (difficulty != null) {
            sb.append(" (").append(difficulty).append(")");
        }
        sb.append(" [Team: ");
        for (int i = 0; i < TEAM_SIZE; i++) {
            if (i > 0) sb.append(", ");
            sb.append(team[i] != null ? team[i].getName() : "Empty");
        }
        sb.append("]");
        return sb.toString();
    }
}
