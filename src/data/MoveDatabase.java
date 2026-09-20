package data;

import model.Move;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Single official database storing all official Move definitions in the project.
 */
public class MoveDatabase {

    // --- Official Move Definitions ---
    public static final Move PSYCHIC = new Move("Psychic", "Psychic", 90, 100);
    public static final Move MOONBLAST = new Move("Moonblast", "Fairy", 95, 100);
    public static final Move SHADOW_BALL = new Move("Shadow Ball", "Ghost", 80, 100);
    public static final Move ENERGY_BALL = new Move("Energy Ball", "Grass", 90, 100);

    public static final Move FLAMETHROWER = new Move("Flamethrower", "Fire", 90, 100);
    public static final Move AIR_SLASH = new Move("Air Slash", "Flying", 75, 95);
    public static final Move DRAGON_CLAW = new Move("Dragon Claw", "Dragon", 80, 100);
    public static final Move THUNDER_PUNCH = new Move("Thunder Punch", "Electric", 75, 100);

    public static final Move AURA_SPHERE = new Move("Aura Sphere", "Fighting", 90, 100);
    public static final Move FLASH_CANNON = new Move("Flash Cannon", "Steel", 80, 100);
    public static final Move SHADOW_CLAW = new Move("Shadow Claw", "Ghost", 70, 100);
    public static final Move EXTREME_SPEED = new Move("Extreme Speed", "Normal", 80, 100);

    public static final Move SLUDGE_BOMB = new Move("Sludge Bomb", "Poison", 90, 100);
    public static final Move DARK_PULSE = new Move("Dark Pulse", "Dark", 80, 100);
    public static final Move THUNDERBOLT = new Move("Thunderbolt", "Electric", 90, 100);

    public static final Move DRAGON_RUSH = new Move("Dragon Rush", "Dragon", 100, 75);
    public static final Move AERIAL_ACE = new Move("Aerial Ace", "Flying", 60, 100);

    public static final Move SURF = new Move("Surf", "Water", 90, 100);
    public static final Move ICE_BEAM = new Move("Ice Beam", "Ice", 90, 100);
    public static final Move AQUA_TAIL = new Move("Aqua Tail", "Water", 90, 90);
    public static final Move MIRROR_COAT = new Move("Mirror Coat", "Psychic", 80, 100);

    public static final Move BODY_SLAM = new Move("Body Slam", "Normal", 85, 100);
    public static final Move CRUNCH = new Move("Crunch", "Dark", 80, 100);
    public static final Move EARTHQUAKE = new Move("Earthquake", "Ground", 100, 100);

    // Special rule: Rest restores 30 HP. HP cannot exceed maximum HP.
    public static final Move REST = new Move("Rest", "Normal", 0, 100, 30);

    public static final Move METEOR_MASH = new Move("Meteor Mash", "Steel", 100, 90);
    public static final Move ZEN_HEADBUTT = new Move("Zen Headbutt", "Psychic", 80, 90);
    public static final Move BULLET_PUNCH = new Move("Bullet Punch", "Steel", 40, 100);

    public static final Move EARTH_POWER = new Move("Earth Power", "Ground", 90, 100);
    public static final Move ROCK_SLIDE = new Move("Rock Slide", "Rock", 75, 90);

    public static final Move GIGA_DRAIN = new Move("Giga Drain", "Grass", 75, 100);
    public static final Move ANCIENT_POWER = new Move("Ancient Power", "Rock", 60, 100);

    public static final Move DAZZLING_GLEAM = new Move("Dazzling Gleam", "Fairy", 80, 100);
    public static final Move STONE_EDGE = new Move("Stone Edge", "Rock", 100, 80);
    public static final Move FIRE_FANG = new Move("Fire Fang", "Fire", 65, 95);

    public static final Move SUCKER_PUNCH = new Move("Sucker Punch", "Dark", 70, 100);

    private static final Map<String, Move> MOVES = new LinkedHashMap<>();

    static {
        register(PSYCHIC);
        register(MOONBLAST);
        register(SHADOW_BALL);
        register(ENERGY_BALL);
        register(FLAMETHROWER);
        register(AIR_SLASH);
        register(DRAGON_CLAW);
        register(THUNDER_PUNCH);
        register(AURA_SPHERE);
        register(FLASH_CANNON);
        register(SHADOW_CLAW);
        register(EXTREME_SPEED);
        register(SLUDGE_BOMB);
        register(DARK_PULSE);
        register(THUNDERBOLT);
        register(DRAGON_RUSH);
        register(AERIAL_ACE);
        register(SURF);
        register(ICE_BEAM);
        register(AQUA_TAIL);
        register(MIRROR_COAT);
        register(BODY_SLAM);
        register(CRUNCH);
        register(EARTHQUAKE);
        register(REST);
        register(METEOR_MASH);
        register(ZEN_HEADBUTT);
        register(BULLET_PUNCH);
        register(EARTH_POWER);
        register(ROCK_SLIDE);
        register(GIGA_DRAIN);
        register(ANCIENT_POWER);
        register(DAZZLING_GLEAM);
        register(STONE_EDGE);
        register(FIRE_FANG);
        register(SUCKER_PUNCH);
    }

    private static void register(Move move) {
        MOVES.put(move.getName().toLowerCase(), move);
    }

    /**
     * Looks up a move by name (case-insensitive).
     *
     * @param name Move name
     * @return The Move instance, or null if not found
     */
    public static Move getMove(String name) {
        if (name == null) return null;
        return MOVES.get(name.trim().toLowerCase());
    }

    /**
     * Checks if a move exists by name.
     */
    public static boolean hasMove(String name) {
        if (name == null) return false;
        return MOVES.containsKey(name.trim().toLowerCase());
    }

    /**
     * Returns an unmodifiable map of all registered moves (keyed by lowercase name).
     */
    public static Map<String, Move> getAllMoves() {
        return Collections.unmodifiableMap(MOVES);
    }
}
