package prospector.traverse.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.GameData;
import prospector.shootingstar.version.Version;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.world.biomes.*;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class TraverseWorld {

    public static List<TraverseBiomeEntry> biomeList = new ArrayList<>();
    public static Biome autumnalWoodsBiome = new BiomeAutumnalWoods();
    public static Biome woodlandsBiome = new BiomeWoodlands();
    public static Biome miniJungleBiome = new BiomeMiniJungle();
    public static Biome meadowBiome = new BiomeMeadow();
    public static Biome lushSwampBiome = new BiomeGreenSwamp();
    public static Biome redDesertBiome = new BiomeRedDesert();
    public static Biome temperateRainforestBiome = new BiomeTemperateRainforest(false);
    public static Biome badlandsBiome = new BiomeBadlands();
    public static Biome mountainousDesertBiome = new BiomeMountainousDesert();
    public static Biome rockyPlateauBiome = new BiomeRockyPlateau();
    public static Biome forestedHillsBiome = new BiomeForestedHills(BiomeForest.Type.NORMAL, "Forested Hills");
    public static Biome birchForestedHillsBiome = new BiomeForestedHills(BiomeForest.Type.BIRCH, "Birch Forested Hills");
    public static Biome autumnalWoodedHillsBiome = new BiomeAutumnalWoodedHills();
    public static Biome cliffsBiome = new BiomeCliffs();
    public static Biome glacierBiome = new BiomeGlacier(false);
    public static Biome glacierSpikesBiome = new BiomeGlacier(true);
    public static Biome snowyConiferousForestBiome = new BiomeTemperateRainforest(true);

    public static void init() {
        register(new Version(1, 0, 0), autumnalWoodsBiome, BiomeManager.BiomeType.COOL, "autumnal_woods", 8, TraverseConfig.disableAutumnalWoods, FOREST);
        register(new Version(1, 0, 0), woodlandsBiome, BiomeManager.BiomeType.WARM, "woodlands", 9, TraverseConfig.disableWoodlands, PLAINS);
        register(new Version(1, 0, 0), miniJungleBiome, BiomeManager.BiomeType.WARM, "mini_jungle", 3, TraverseConfig.disableMiniJungle, DENSE, JUNGLE, HOT, WET);
        register(new Version(1, 0, 0), meadowBiome, BiomeManager.BiomeType.COOL, "meadow", 7, TraverseConfig.disableMeadow, PLAINS, LUSH, WET);
        register(new Version(1, 0, 0), lushSwampBiome, BiomeManager.BiomeType.WARM, "green_swamp", 6, TraverseConfig.disableLushSwamp, LUSH, WET, SWAMP);
        register(new Version(1, 0, 0), redDesertBiome, BiomeManager.BiomeType.DESERT, "red_desert", 6, TraverseConfig.disableRedDesert, HOT, DRY, SANDY);
        register(new Version(1, 0, 0), temperateRainforestBiome, BiomeManager.BiomeType.COOL, "temperate_rainforest", 8, TraverseConfig.disableTemperateRainforest, FOREST, CONIFEROUS);
        register(new Version(1, 1, 0), badlandsBiome, BiomeManager.BiomeType.WARM, "badlands", 5, TraverseConfig.disableBadlands, PLAINS, DRY, HOT, SPARSE);
        register(new Version(1, 1, 0), mountainousDesertBiome, BiomeManager.BiomeType.DESERT, "mountainous_desert", 2, TraverseConfig.disableMountainousDesert, MOUNTAIN, DRY, HOT, SANDY);
        register(new Version(1, 1, 0), rockyPlateauBiome, BiomeManager.BiomeType.WARM, "rocky_plateau", 4, TraverseConfig.disableRockyPlateau);
        register(new Version(1, 1, 0), forestedHillsBiome, BiomeManager.BiomeType.COOL, "forested_hills", 6, TraverseConfig.disableForestedHills, FOREST, HILLS);
        register(new Version(1, 1, 0), birchForestedHillsBiome, BiomeManager.BiomeType.COOL, "birch_forested_hills", 2, TraverseConfig.disableBirchForestedHills, FOREST, HILLS);
        register(new Version(1, 1, 0), autumnalWoodedHillsBiome, BiomeManager.BiomeType.COOL, "autumnal_wooded_hills", 1, TraverseConfig.disableAutumnalWoodedHills, FOREST, HILLS);
        register(new Version(1, 2, 0), cliffsBiome, BiomeManager.BiomeType.COOL, "cliffs", 2, TraverseConfig.disableCliffs, MOUNTAIN, COLD, HILLS);
        register(new Version(1, 2, 0), glacierBiome, BiomeManager.BiomeType.ICY, "glacier", 6, TraverseConfig.disableGlacier, MOUNTAIN, COLD, SNOWY);
        register(new Version(1, 2, 0), glacierSpikesBiome, BiomeManager.BiomeType.ICY, "glacier_spikes", 2, TraverseConfig.disableGlacierSpikes, MOUNTAIN, COLD, SNOWY);
        register(new Version(1, 2, 0), snowyConiferousForestBiome, BiomeManager.BiomeType.ICY, "snowy_coniferous_forest", 5, TraverseConfig.disableGlacierSpikes, COLD, SNOWY, FOREST, CONIFEROUS);
    }

    public static void register(Version versionAdded, Biome biome, BiomeManager.BiomeType type, String name, int weight, boolean disabled, BiomeDictionary.Type... biomeDictTypes) {
        if (!disabled) {
            biome.setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name));
            GameData.register_impl(biome);
            for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
                BiomeDictionary.addTypes(biome, biomeDictType);
            }
            biomeList.add(new TraverseBiomeEntry(biome, type, weight, versionAdded));
        }
    }

    public static class TraverseBiomeEntry {
        private Biome biome;
        private BiomeManager.BiomeType type;
        private Version versionAdded;
        private int weight;
        private BiomeManager.BiomeEntry entry;

        public TraverseBiomeEntry(Biome biome, BiomeManager.BiomeType type, int weight, Version versionAdded) {
            this.biome = biome;
            this.type = type;
            this.weight = weight;
            this.versionAdded = versionAdded;
            this.entry = new BiomeManager.BiomeEntry(biome, weight);
        }

        public Biome getBiome() {
            return biome;
        }

        public BiomeManager.BiomeEntry getEntry() {
            return entry;
        }

        public BiomeManager.BiomeType getType() {
            return type;
        }

        public Version getVersionAdded() {
            return versionAdded;
        }

        public int getWeight() {
            return weight;
        }
    }
}
