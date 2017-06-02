package prospector.traverse.world.biomes;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import prospector.traverse.world.ITreeConstants;
import prospector.traverse.world.TraverseTreeGenerator;

import java.util.Random;

public class BiomeAutumnalWoodedHills extends Biome implements ITreeConstants {

    public static BiomeProperties properties = new BiomeProperties("Autumnal Wooded Forest");

    static {
        properties.setTemperature(Biomes.FOREST.getTemperature());
        properties.setRainfall(Biomes.FOREST.getRainfall());
        properties.setBaseHeight(Biomes.EXTREME_HILLS.getBaseHeight());
        properties.setHeightVariation(Biomes.EXTREME_HILLS.getHeightVariation());
    }

    public BiomeAutumnalWoodedHills() {
        super(properties);
        theBiomeDecorator.treesPerChunk = 10;
        theBiomeDecorator.flowersPerChunk = 4;
        theBiomeDecorator.grassPerChunk = 6;

        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xFFD6C23D;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFEFECD9;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xFFD2D31F;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        int num = rand.nextInt(5);
        if (num == 0) {
            return new TraverseTreeGenerator(true, 4, OAK_LOG, RED_AUTUMNAL_LEAVES);
        } else if (num == 1) {
            return new TraverseTreeGenerator(true, 4, OAK_LOG, BROWN_AUTUMNAL_LEAVES);
        } else if (num == 2) {
            return new TraverseTreeGenerator(true, 4, OAK_LOG, ORANGE_AUTUMNAL_LEAVES);
        } else if (num == 3) {
            return new TraverseTreeGenerator(true, 4, OAK_LOG, YELLOW_AUTUMNAL_LEAVES);
        } else {
            return TREE_FEATURE;
        }
    }
}
