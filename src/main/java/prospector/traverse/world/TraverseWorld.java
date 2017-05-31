package prospector.traverse.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.shootingstar.version.Version;
import prospector.shootingstar.version.VersionUtils;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.world.biomes.*;

import java.util.LinkedHashMap;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class TraverseWorld {

    public static LinkedHashMap<Biome, Version> biomeList = new LinkedHashMap<>();
    public static Biome autumnalWoodsBiome = new BiomeAutumnalWoods();
    public static Biome woodlandsBiome = new BiomeWoodlands();
    public static Biome miniJungleBiome = new BiomeMiniJungle();
    public static Biome meadowBiome = new BiomeMeadow();
    public static Biome greenSwampBiome = new BiomeGreenSwamp();
    public static Biome redDesertBiome = new BiomeRedDesert();
    public static Biome temperateRainforestBiome = new BiomeTemperateRainforest();
    public static Biome badlandsBiome = new BiomeBadlands();

    public static void init() {
        register(new Version(1, 0, 0), autumnalWoodsBiome, BiomeManager.BiomeType.COOL, "autumnal_woods", 8, FOREST);
        register(new Version(1, 0, 0), woodlandsBiome, BiomeManager.BiomeType.WARM, "woodlands", 9, PLAINS);
        register(new Version(1, 0, 0), miniJungleBiome, BiomeManager.BiomeType.WARM, "mini_jungle", 3, DENSE, JUNGLE, HOT, WET);
        register(new Version(1, 0, 0), meadowBiome, BiomeManager.BiomeType.COOL, "meadow", 7, PLAINS, LUSH, WET);
        register(new Version(1, 0, 0), greenSwampBiome, BiomeManager.BiomeType.WARM, "green_swamp", 6, LUSH, WET, SWAMP);
        register(new Version(1, 0, 0), redDesertBiome, BiomeManager.BiomeType.DESERT, "red_desert", 6, HOT, DRY, SANDY);
        register(new Version(1, 0, 0), temperateRainforestBiome, BiomeManager.BiomeType.COOL, "temperate_rainforest", 8, FOREST, CONIFEROUS);
        register(new Version(1, 1, 0), badlandsBiome, BiomeManager.BiomeType.WARM, "badlands", 7, PLAINS, SPARSE);
    }

    public static void register(Version versionAdded, Biome biome, BiomeManager.BiomeType type, String name, int weight, BiomeDictionary.Type... biomeDictTypes) {
        boolean canRegister;
        if (!TraverseConfig.registerBiomesRegardless) {
            TraverseConfig.reloadVersionConfig();
            canRegister = VersionUtils.isVersionLessOrEqual(versionAdded, TraverseConfig.version);
        } else {
            canRegister = true;
        }
        if (canRegister) {
            biome.setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name));
            GameRegistry.register(biome);
            BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, weight));
            BiomeManager.addSpawnBiome(biome);
            BiomeProvider.allowedBiomes.add(biome);
            for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
                BiomeDictionary.addTypes(biome, biomeDictType);
            }
            biomeList.put(biome, versionAdded);
        }
    }
}
