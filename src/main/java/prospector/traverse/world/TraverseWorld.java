package prospector.traverse.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.shootingstar.version.Version;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.world.biomes.*;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class TraverseWorld {

    public static List<BiomeEntry> biomeList = new ArrayList<>();
    public static Biome autumnalWoodsBiome = new BiomeAutumnalWoods();
    public static Biome woodlandsBiome = new BiomeWoodlands();
    public static Biome miniJungleBiome = new BiomeMiniJungle();
    public static Biome meadowBiome = new BiomeMeadow();
    public static Biome lushSwampBiome = new BiomeGreenSwamp();
    public static Biome redDesertBiome = new BiomeRedDesert();
    public static Biome temperateRainforestBiome = new BiomeTemperateRainforest();
    public static Biome badlandsBiome = new BiomeBadlands();
    public static Biome mountainousDesertBiome = new BiomeMountainousDesert();
    public static Biome rockyPlateauBiome = new BiomeRockyPlateau();
    public static Biome forestedHills = new BiomeForestedHills(BiomeForest.Type.NORMAL, "Forested Hills");
    public static Biome birchForestedHills = new BiomeForestedHills(BiomeForest.Type.BIRCH, "Birch Forested Hills");
    public static Biome autumnalWoodedHills = new BiomeAutumnalWoodedHills();

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
        register(new Version(1, 1, 0), forestedHills, BiomeManager.BiomeType.COOL, "forested_hills", 6, TraverseConfig.disableForestedHills, FOREST, HILLS);
        register(new Version(1, 1, 0), birchForestedHills, BiomeManager.BiomeType.COOL, "birch_forested_hills", 2, TraverseConfig.disableBirchForestedHills, FOREST, HILLS);
        register(new Version(1, 1, 0), autumnalWoodedHills, BiomeManager.BiomeType.COOL, "autumnal_wooded_hills", 1, TraverseConfig.disableAutumnalWoodedHills, FOREST, HILLS);
    }

    public static void register(Version versionAdded, Biome biome, BiomeManager.BiomeType type, String name, int weight, boolean disabled, BiomeDictionary.Type... biomeDictTypes) {
        if (!disabled) {
            biome.setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name));
            GameRegistry.register(biome);
            for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
                BiomeDictionary.addTypes(biome, biomeDictType);
            }
            biomeList.add(new BiomeEntry(biome, type, weight, versionAdded));
        }
    }

    public static class BiomeEntry {
        private Biome biome;
        private BiomeManager.BiomeType type;
        private Version versionAdded;
        private int weight;

        public BiomeEntry(Biome biome, BiomeManager.BiomeType type, int weight, Version versionAdded) {
            this.biome = biome;
            this.type = type;
            this.weight = weight;
            this.versionAdded = versionAdded;
        }

        public Biome getBiome() {
            return biome;
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
