package prospector.traverse.world.biomes;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import prospector.traverse.world.ITreeConstants;

public class BiomeMeadow extends Biome implements ITreeConstants {

    public static BiomeProperties properties = new BiomeProperties("Meadow");

    static {
        properties.setTemperature(0.8F);
        properties.setBaseHeight(0.1F);
        properties.setHeightVariation(0F);
    }

    public BiomeMeadow() {
        super(properties);
        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 6, 2, 4));
        this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values()) {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xFF4DD838;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFDAEBEF;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xFF4DD838;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new DecoratorMeadow();
    }

    public class DecoratorMeadow extends BiomeDecorator {

        public DecoratorMeadow() {
            treesPerChunk = 0;
            extraTreeChance = 0;
            flowersPerChunk = 15;
            grassPerChunk = 15;
            reedsPerChunk = 2;
        }
    }
}
