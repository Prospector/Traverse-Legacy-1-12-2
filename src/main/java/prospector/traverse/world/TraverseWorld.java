package prospector.traverse.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.world.biomes.BiomeAutumnalWoods;
import prospector.traverse.world.biomes.BiomeMeadow;
import prospector.traverse.world.biomes.BiomeMiniJungle;
import prospector.traverse.world.biomes.BiomeWoodlands;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class TraverseWorld {

    public static Biome autumnalWoodsBiome = new BiomeAutumnalWoods();
    public static Biome woodlandsBiome = new BiomeWoodlands();
    public static Biome miniJungleBiome = new BiomeMiniJungle();
    public static Biome meadowBiome = new BiomeMeadow();

    public static void init() {
        register(autumnalWoodsBiome, BiomeManager.BiomeType.COOL, "autumnal_woods", 8, FOREST);
        register(woodlandsBiome, BiomeManager.BiomeType.WARM, "woodlands", 9, PLAINS);
        register(miniJungleBiome, BiomeManager.BiomeType.WARM, "mini_jungle", 3, DENSE, JUNGLE, HOT, WET);
        register(meadowBiome, BiomeManager.BiomeType.COOL, "meadow", 7, PLAINS, LUSH, WET);
    }

    public static void register(Biome biome, BiomeManager.BiomeType type, String name, int weight, BiomeDictionary.Type... biomeDictTypes) {
        biome.setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name));
        GameRegistry.register(biome);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        BiomeProvider.allowedBiomes.add(biome);
        for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
            BiomeDictionary.addTypes(biome, biomeDictType);
        }
    }
}
