package prospector.traverse.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.world.biomes.BiomeAutumnalWoods;
import prospector.traverse.world.biomes.BiomeWoodlands;

public class TraverseWorld {

    public static Biome autumnalWoodsBiome = new BiomeAutumnalWoods();
    public static Biome woodlandsBiome = new BiomeWoodlands();

    public static void init() {
        register(autumnalWoodsBiome, BiomeManager.BiomeType.WARM, "autumnal_woods", 8);
        register(woodlandsBiome, BiomeManager.BiomeType.COOL, "woodlands", 10);
    }

    public static void register(Biome biome, BiomeManager.BiomeType type, String name, int weight) {
        biome.setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name));
        GameRegistry.register(biome);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        BiomeProvider.allowedBiomes.add(biome);
    }
}
