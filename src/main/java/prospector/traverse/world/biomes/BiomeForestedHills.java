package prospector.traverse.world.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeForest;
import prospector.traverse.world.ITreeConstants;

public class BiomeForestedHills extends BiomeForest implements ITreeConstants {

    public BiomeForestedHills(BiomeForest.Type type, String name) {
        super(type, new BiomeProperties(name).setTemperature(Biomes.FOREST.getTemperature()).setRainfall(Biomes.FOREST.getRainfall()).setBaseHeight(Biomes.EXTREME_HILLS.getBaseHeight()).setHeightVariation(Biomes.EXTREME_HILLS.getHeightVariation()));
    }
}
