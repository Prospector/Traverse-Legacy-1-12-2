package prospector.traverse.world.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import prospector.traverse.world.ITreeConstants;
import prospector.traverse.world.features.WorldGenFirTree;

import java.util.Random;

public class BiomeTemperateRainforest extends Biome implements ITreeConstants {

    protected static final WorldGenFirTree FIR_TREE_FEATURE = new WorldGenFirTree(true);

    public static BiomeProperties properties = new BiomeProperties("Temperate Rainforest");

    static {
        properties.setTemperature(0.6F);
        properties.setRainfall(0.9F);
        properties.setBaseHeight(0.4F);
        properties.setHeightVariation(1.1F);
    }

    public BiomeTemperateRainforest() {
        super(properties);
        decorator.treesPerChunk = 7;
        decorator.flowersPerChunk = 0;
        decorator.grassPerChunk = 16;
        decorator.mushroomsPerChunk = 2;

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return FIR_TREE_FEATURE;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xFF338235;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xFF338235;
    }
}
