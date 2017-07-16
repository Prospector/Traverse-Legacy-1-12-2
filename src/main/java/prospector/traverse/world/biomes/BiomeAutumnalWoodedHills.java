package prospector.traverse.world.biomes;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.world.ITreeConstants;
import prospector.traverse.world.TraverseTreeGenerator;

import java.util.Random;

public class BiomeAutumnalWoodedHills extends Biome implements ITreeConstants {

    public static BiomeProperties properties = new BiomeProperties("Autumnal Wooded Hills");

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

        if (TraverseConfig.disableCustomSkies)
            return super.getSkyColorByTemp(currentTemperature);
        else
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
