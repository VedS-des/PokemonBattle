package data;

import model.Move;
import model.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * Single official database storing all 15 Pokémon in the project
 * with their exact stats, typings, and 4 fixed moves.
 */
public class PokemonDatabase {

    private static final Map<String, Pokemon> TEMPLATES = new LinkedHashMap<>();

    // --- Player Pokémon (6) ---

    // Gardevoir: Psychic / Fairy | HP 110 | Atk 75 | Def 70 | Spd 90
    private static final Pokemon GARDEVOIR_TEMPLATE = new Pokemon(
            "Gardevoir", "Psychic", "Fairy", 110, 75, 70, 90,
            new Move[]{
                    MoveDatabase.PSYCHIC,
                    MoveDatabase.MOONBLAST,
                    MoveDatabase.SHADOW_BALL,
                    MoveDatabase.ENERGY_BALL
            },
            "sprites/gardevoir.png"
    );

    // Charizard: Fire / Flying | HP 120 | Atk 90 | Def 75 | Spd 100
    // (Used by both Player selection and Red's team)
    private static final Pokemon CHARIZARD_TEMPLATE = new Pokemon(
            "Charizard", "Fire", "Flying", 120, 90, 75, 100,
            new Move[]{
                    MoveDatabase.FLAMETHROWER,
                    MoveDatabase.AIR_SLASH,
                    MoveDatabase.DRAGON_CLAW,
                    MoveDatabase.THUNDER_PUNCH
            },
            "sprites/charizard.png"
    );

    // Lucario: Fighting / Steel | HP 115 | Atk 95 | Def 85 | Spd 95
    private static final Pokemon LUCARIO_TEMPLATE = new Pokemon(
            "Lucario", "Fighting", "Steel", 115, 95, 85, 95,
            new Move[]{
                    MoveDatabase.AURA_SPHERE,
                    MoveDatabase.FLASH_CANNON,
                    MoveDatabase.SHADOW_CLAW,
                    MoveDatabase.EXTREME_SPEED
            },
            "sprites/lucario.png"
    );

    // Gengar: Ghost / Poison | HP 105 | Atk 85 | Def 70 | Spd 110
    private static final Pokemon GENGAR_TEMPLATE = new Pokemon(
            "Gengar", "Ghost", "Poison", 105, 85, 70, 110,
            new Move[]{
                    MoveDatabase.SHADOW_BALL,
                    MoveDatabase.SLUDGE_BOMB,
                    MoveDatabase.DARK_PULSE,
                    MoveDatabase.THUNDERBOLT
            },
            "sprites/gengar.png"
    );

    // Dragonite: Dragon / Flying | HP 140 | Atk 105 | Def 95 | Spd 80
    private static final Pokemon DRAGONITE_TEMPLATE = new Pokemon(
            "Dragonite", "Dragon", "Flying", 140, 105, 95, 80,
            new Move[]{
                    MoveDatabase.DRAGON_CLAW,
                    MoveDatabase.DRAGON_RUSH,
                    MoveDatabase.AERIAL_ACE,
                    MoveDatabase.THUNDER_PUNCH
            },
            "sprites/dragonite.png"
    );

    // Milotic: Water | HP 135 | Atk 80 | Def 100 | Spd 75
    private static final Pokemon MILOTIC_TEMPLATE = new Pokemon(
            "Milotic", "Water", null, 135, 80, 100, 75,
            new Move[]{
                    MoveDatabase.SURF,
                    MoveDatabase.ICE_BEAM,
                    MoveDatabase.AQUA_TAIL,
                    MoveDatabase.MIRROR_COAT
            },
            "sprites/milotic.png"
    );

    // --- Red's Pokémon (Easy) ---

    // Lapras: Water / Ice | HP 140 | Atk 80 | Def 90 | Spd 65
    private static final Pokemon LAPRAS_TEMPLATE = new Pokemon(
            "Lapras", "Water", "Ice", 140, 80, 90, 65,
            new Move[]{
                    MoveDatabase.SURF,
                    MoveDatabase.ICE_BEAM,
                    MoveDatabase.THUNDERBOLT,
                    MoveDatabase.BODY_SLAM
            },
            "sprites/lapras.png"
    );

    // Snorlax: Normal | HP 150 | Atk 100 | Def 85 | Spd 45 (Rest restores 30 HP)
    private static final Pokemon SNORLAX_TEMPLATE = new Pokemon(
            "Snorlax", "Normal", null, 150, 100, 85, 45,
            new Move[]{
                    MoveDatabase.BODY_SLAM,
                    MoveDatabase.CRUNCH,
                    MoveDatabase.EARTHQUAKE,
                    MoveDatabase.REST
            },
            "sprites/snorlax.png"
    );

    // --- Steven's Pokémon (Medium) ---

    // Metagross: Steel / Psychic | HP 140 | Atk 110 | Def 110 | Spd 70
    private static final Pokemon METAGROSS_TEMPLATE = new Pokemon(
            "Metagross", "Steel", "Psychic", 140, 110, 110, 70,
            new Move[]{
                    MoveDatabase.METEOR_MASH,
                    MoveDatabase.ZEN_HEADBUTT,
                    MoveDatabase.EARTHQUAKE,
                    MoveDatabase.BULLET_PUNCH
            },
            "sprites/metagross.png"
    );

    // Claydol: Ground / Psychic | HP 115 | Atk 75 | Def 100 | Spd 75
    private static final Pokemon CLAYDOL_TEMPLATE = new Pokemon(
            "Claydol", "Ground", "Psychic", 115, 75, 100, 75,
            new Move[]{
                    MoveDatabase.EARTH_POWER,
                    MoveDatabase.PSYCHIC,
                    MoveDatabase.SHADOW_BALL,
                    MoveDatabase.ROCK_SLIDE
            },
            "sprites/claydol.png"
    );

    // Cradily: Rock / Grass | HP 130 | Atk 80 | Def 105 | Spd 55
    private static final Pokemon CRADILY_TEMPLATE = new Pokemon(
            "Cradily", "Rock", "Grass", 130, 80, 105, 55,
            new Move[]{
                    MoveDatabase.GIGA_DRAIN,
                    MoveDatabase.ANCIENT_POWER,
                    MoveDatabase.SLUDGE_BOMB,
                    MoveDatabase.EARTH_POWER
            },
            "sprites/cradily.png"
    );

    // --- Cynthia's Pokémon (Hard) ---

    // Togekiss: Fairy / Flying | HP 125 | Atk 80 | Def 85 | Spd 80
    private static final Pokemon TOGEKISS_TEMPLATE = new Pokemon(
            "Togekiss", "Fairy", "Flying", 125, 80, 85, 80,
            new Move[]{
                    MoveDatabase.AIR_SLASH,
                    MoveDatabase.DAZZLING_GLEAM,
                    MoveDatabase.AURA_SPHERE,
                    MoveDatabase.FLAMETHROWER
            },
            "sprites/togekiss.png"
    );

    // Garchomp: Dragon / Ground | HP 140 | Atk 110 | Def 95 | Spd 105
    private static final Pokemon GARCHOMP_TEMPLATE = new Pokemon(
            "Garchomp", "Dragon", "Ground", 140, 110, 95, 105,
            new Move[]{
                    MoveDatabase.EARTHQUAKE,
                    MoveDatabase.DRAGON_CLAW,
                    MoveDatabase.STONE_EDGE,
                    MoveDatabase.FIRE_FANG
            },
            "sprites/garchomp.png"
    );

    // Spiritomb: Ghost / Dark | HP 120 | Atk 85 | Def 90 | Spd 60
    private static final Pokemon SPIRITOMB_TEMPLATE = new Pokemon(
            "Spiritomb", "Ghost", "Dark", 120, 85, 90, 60,
            new Move[]{
                    MoveDatabase.SHADOW_BALL,
                    MoveDatabase.DARK_PULSE,
                    MoveDatabase.PSYCHIC,
                    MoveDatabase.SUCKER_PUNCH
            },
            "sprites/spiritomb.png"
    );

    static {
        register(GARDEVOIR_TEMPLATE);
        register(CHARIZARD_TEMPLATE);
        register(LUCARIO_TEMPLATE);
        register(GENGAR_TEMPLATE);
        register(DRAGONITE_TEMPLATE);
        register(MILOTIC_TEMPLATE);
        register(LAPRAS_TEMPLATE);
        register(SNORLAX_TEMPLATE);
        register(METAGROSS_TEMPLATE);
        register(CLAYDOL_TEMPLATE);
        register(CRADILY_TEMPLATE);
        register(TOGEKISS_TEMPLATE);
        register(GARCHOMP_TEMPLATE);
        register(SPIRITOMB_TEMPLATE);
    }

    private static void register(Pokemon pokemon) {
        TEMPLATES.put(pokemon.getName().toLowerCase(), pokemon);
    }

    /**
     * Looks up and returns a fresh, independent instance of a Pokémon by name (case-insensitive).
     *
     * @param name Pokémon name (e.g., "Gardevoir", "Charizard")
     * @return A new Pokémon instance, or null if not found
     */
    public static Pokemon getPokemon(String name) {
        if (name == null) return null;
        Pokemon template = TEMPLATES.get(name.trim().toLowerCase());
        return template != null ? template.copy() : null;
    }

    /**
     * Checks if a Pokémon exists in the database.
     */
    public static boolean hasPokemon(String name) {
        if (name == null) return false;
        return TEMPLATES.containsKey(name.trim().toLowerCase());
    }

    // The 6 Pokémon permitted for player selection
    private static final List<String> PLAYER_POKEMON_NAMES = Collections.unmodifiableList(
            Arrays.asList("Gardevoir", "Charizard", "Lucario", "Gengar", "Dragonite", "Milotic")
    );

    /**
     * Returns an unmodifiable list of the names of the 6 Pokémon available for player selection.
     */
    public static List<String> getPlayerPokemonNames() {
        return PLAYER_POKEMON_NAMES;
    }

    /**
     * Checks if a Pokémon name belongs to the 6 allowed player Pokémon (case-insensitive).
     */
    public static boolean isPlayerPokemon(String name) {
        if (name == null) return false;
        for (String pName : PLAYER_POKEMON_NAMES) {
            if (pName.equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a Pokémon instance belongs to the 6 allowed player Pokémon.
     */
    public static boolean isPlayerPokemon(Pokemon pokemon) {
        return pokemon != null && isPlayerPokemon(pokemon.getName());
    }

    /**
     * Returns fresh copies of the 6 Pokémon available for player selection:
     * Gardevoir, Charizard, Lucario, Gengar, Dragonite, Milotic.
     */
    public static List<Pokemon> getPlayerPokemon() {
        List<Pokemon> playerList = new ArrayList<>(6);
        playerList.add(createGardevoir());
        playerList.add(createCharizard());
        playerList.add(createLucario());
        playerList.add(createGengar());
        playerList.add(createDragonite());
        playerList.add(createMilotic());
        return playerList;
    }

    /**
     * Returns fresh copies of all Pokémon templates.
     */
    public static List<Pokemon> getAllPokemon() {
        List<Pokemon> all = new ArrayList<>(TEMPLATES.size());
        for (Pokemon template : TEMPLATES.values()) {
            all.add(template.copy());
        }
        return Collections.unmodifiableList(all);
    }

    // --- Individual Factory Methods (Always return fresh instances) ---

    public static Pokemon createGardevoir() { return GARDEVOIR_TEMPLATE.copy(); }
    public static Pokemon createCharizard() { return CHARIZARD_TEMPLATE.copy(); }
    public static Pokemon createLucario() { return LUCARIO_TEMPLATE.copy(); }
    public static Pokemon createGengar() { return GENGAR_TEMPLATE.copy(); }
    public static Pokemon createDragonite() { return DRAGONITE_TEMPLATE.copy(); }
    public static Pokemon createMilotic() { return MILOTIC_TEMPLATE.copy(); }
    public static Pokemon createLapras() { return LAPRAS_TEMPLATE.copy(); }
    public static Pokemon createSnorlax() { return SNORLAX_TEMPLATE.copy(); }
    public static Pokemon createMetagross() { return METAGROSS_TEMPLATE.copy(); }
    public static Pokemon createClaydol() { return CLAYDOL_TEMPLATE.copy(); }
    public static Pokemon createCradily() { return CRADILY_TEMPLATE.copy(); }
    public static Pokemon createTogekiss() { return TOGEKISS_TEMPLATE.copy(); }
    public static Pokemon createGarchomp() { return GARCHOMP_TEMPLATE.copy(); }
    public static Pokemon createSpiritomb() { return SPIRITOMB_TEMPLATE.copy(); }
}
