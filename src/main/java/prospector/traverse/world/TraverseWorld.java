package prospector.traverse.world;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.world.biomes.autumnalWoods.BiomeAutumnalWoods;

public class TraverseWorld {

    public static BiomeAutumnalWoods autumnalWoodsBiome;

    public static void init() {
        Biome.BiomeProperties properties = new Biome.BiomeProperties("Autumnal Woods");
        properties.setTemperature(Biomes.FOREST.getTemperature());
        properties.setRainfall(Biomes.FOREST.getRainfall());
        properties.setBaseHeight(Biomes.FOREST.getBaseHeight());
        properties.setHeightVariation(Biomes.FOREST.getHeightVariation());

        autumnalWoodsBiome = new BiomeAutumnalWoods(properties);
        autumnalWoodsBiome.setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, "autumnal_woods"));

        GameRegistry.register(autumnalWoodsBiome);

        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(autumnalWoodsBiome, 20));
        BiomeManager.addSpawnBiome(autumnalWoodsBiome);
        BiomeProvider.allowedBiomes.add(autumnalWoodsBiome);
    }

}
