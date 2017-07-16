package prospector.traverse.world.biomes;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
        theBiomeDecorator.treesPerChunk = 7;
        theBiomeDecorator.flowersPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 16;
        theBiomeDecorator.mushroomsPerChunk = 2;

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand) {
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

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS)) {
            int chance = rand.nextInt(5) - 3;
            addDoublePlants(worldIn, rand, pos, chance);
        }
        super.decorate(worldIn, rand, pos);
    }

    public void addDoublePlants(World world, Random rand, BlockPos pos, int chance) {
        for (int i = 0; i < chance; ++i) {
            int j = rand.nextInt(3);

            if (j == 0) {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);
            } else if (j == 1) {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.ROSE);
            } else if (j == 2) {
                DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.PAEONIA);
            }

            for (int k = 0; k < 5; ++k) {
                int l = rand.nextInt(16) + 8;
                int i1 = rand.nextInt(16) + 8;
                int j1 = rand.nextInt(world.getHeight(pos.add(l, 0, i1)).getY() + 32);

                if (DOUBLE_PLANT_GENERATOR.generate(world, rand, new BlockPos(pos.getX() + l, j1, pos.getZ() + i1))) {
                    break;
                }
            }
        }
    }
}
