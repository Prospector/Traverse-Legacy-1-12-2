package prospector.traverse.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prospector.traverse.core.TraverseConstants;

import java.io.File;

public class TraverseConfig {
    public static final String CATEGORY_BIOMES = "Biomes";
    public static File configDir;
    public static File mainConfig;
    public static Configuration config;
    public static TraverseConfig traverseConfiguration;

    public static boolean useVanillaWood = false;
    public static boolean registerBiomesRegardless = false;
    public static boolean solidLeaves = false;

    public static boolean disableAutumnalWoods = false;
    public static boolean disableWoodlands = false;
    public static boolean disableMiniJungle = false;
    public static boolean disableMeadow = false;
    public static boolean disableLushSwamp = false;
    public static boolean disableRedDesert = false;
    public static boolean disableTemperateRainforest = false;
    public static boolean disableBadlands = false;
    public static boolean disableMountainousDesert = false;
    public static boolean disableRockyPlateau = false;
    public static boolean disableForestedHills = false;
    public static boolean disableBirchForestedHills = false;
    public static boolean disableAutumnalWoodedHills = false;
    public static boolean disableCliffs = false;
    public static boolean disableGlacier = false;
    public static boolean disableGlacierSpikes = false;
    public static boolean disableSnowyConiferousForest = false;

    private static TraverseConfig instance = null;

    private TraverseConfig() {
        config = new Configuration(mainConfig);
        config.load();

        useVanillaWood = config.get(Configuration.CATEGORY_GENERAL, "useVanillaWood", useVanillaWood, "Use vanilla logs for Traverse trees (might not look as nice)").getBoolean();
        registerBiomesRegardless = config.get(Configuration.CATEGORY_GENERAL, "registerBiomesRegardless", registerBiomesRegardless, "All biomes will always be registered, ignoring the instance traverse_world_data (WARNING: This will cause ugly world generation borders at the edge of what has previously been generated and what is new!!)").getBoolean();
        solidLeaves = config.get(Configuration.CATEGORY_GENERAL, "solidLeaves", solidLeaves, "Leaves will not render transparent. This will help on old/crappy computers.").getBoolean();

        disableAutumnalWoods = config.get(CATEGORY_BIOMES, "disableAutumnalWoods", disableAutumnalWoods, "Force disable the Autumnal Woods biome").getBoolean();
        disableWoodlands = config.get(CATEGORY_BIOMES, "disableWoodlands", disableWoodlands, "Force disable the Woodlands biome").getBoolean();
        disableMiniJungle = config.get(CATEGORY_BIOMES, "disableMiniJungle", disableMiniJungle, "Force disable the Mini Jungle biome").getBoolean();
        disableMeadow = config.get(CATEGORY_BIOMES, "disableMeadow", disableMeadow, "Force disable the Meadow biome").getBoolean();
        disableLushSwamp = config.get(CATEGORY_BIOMES, "disableLushSwamp", disableLushSwamp, "Force disable the Lush Swamp biome").getBoolean();
        disableRedDesert = config.get(CATEGORY_BIOMES, "disableRedDesert", disableRedDesert, "Force disable the Red Desert biome").getBoolean();
        disableTemperateRainforest = config.get(CATEGORY_BIOMES, "disableTemperateRainforest", disableTemperateRainforest, "Force disable the Temperate Rainforest biome").getBoolean();
        disableBadlands = config.get(CATEGORY_BIOMES, "disableBadlands", disableBadlands, "Force disable the Badlands biome").getBoolean();
        disableMountainousDesert = config.get(CATEGORY_BIOMES, "disableMountainousDesert", disableMountainousDesert, "Force disable the Mountainous Desert biome").getBoolean();
        disableRockyPlateau = config.get(CATEGORY_BIOMES, "disableRockyPlateau", disableRockyPlateau, "Force disable the Rocky Plateau biome").getBoolean();
        disableForestedHills = config.get(CATEGORY_BIOMES, "disableForestedHills", disableForestedHills, "Force disable the Forested Hills biome").getBoolean();
        disableBirchForestedHills = config.get(CATEGORY_BIOMES, "disableBirchForestedHills", disableBirchForestedHills, "Force disable the Birch Forested Hills biome").getBoolean();
        disableAutumnalWoodedHills = config.get(CATEGORY_BIOMES, "disableAutumnalWoodedHills", disableAutumnalWoodedHills, "Force disable the Autumnal Wooded Hills biome").getBoolean();
        disableCliffs = config.get(CATEGORY_BIOMES, "disableCliffs", disableCliffs, "Force disable the Cliffs biome").getBoolean();
        disableGlacier = config.get(CATEGORY_BIOMES, "disableGlacier", disableGlacier, "Force disable the Glacier biome").getBoolean();
        disableGlacierSpikes = config.get(CATEGORY_BIOMES, "disableGlacierSpikes", disableGlacierSpikes, "Force disable the Glacier Spikes biome").getBoolean();
        disableSnowyConiferousForest = config.get(CATEGORY_BIOMES, "disableSnowyConiferousForest", disableSnowyConiferousForest, "Force disable the Snowy Coniferous Forest biome").getBoolean();

        config.save();
    }

    public static TraverseConfig initialize() {
        if (instance == null)
            instance = new TraverseConfig();
        else
            throw new IllegalStateException("Cannot initialize config twice");

        return instance;
    }

    public static TraverseConfig getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance of config requested before initialization");
        }
        return instance;
    }

    public static void initialize(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory(), TraverseConstants.MOD_ID);
        if (!configDir.exists()) {
            configDir.mkdir();
        }

        mainConfig = new File(configDir, "traverse.cfg");
        traverseConfiguration = initialize();
    }
}
