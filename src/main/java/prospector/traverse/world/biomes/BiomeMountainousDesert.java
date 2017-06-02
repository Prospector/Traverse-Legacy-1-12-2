package prospector.traverse.world.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeDesert;
import prospector.traverse.world.ITreeConstants;

public class BiomeMountainousDesert extends BiomeDesert implements ITreeConstants {

    public static BiomeProperties properties = new BiomeProperties("Mountainous Desert");

    static {
        properties.setTemperature(Biomes.DESERT.getTemperature());
        properties.setRainfall(Biomes.DESERT.getRainfall());
        properties.setRainDisabled();
        properties.setBaseHeight(0.4F);
        properties.setHeightVariation(1.1F);
    }

    public BiomeMountainousDesert() {
        super(properties);
    }
}
