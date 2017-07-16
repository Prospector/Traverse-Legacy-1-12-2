package prospector.traverse.world.biomes;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import prospector.traverse.world.ITreeConstants;

import java.util.Random;

public class BiomeMeadow extends Biome implements ITreeConstants {

    public static BiomeProperties properties = new BiomeProperties("Meadow");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.7F);
        properties.setBaseHeight(0.1F);
        properties.setHeightVariation(0F);
    }

    public BiomeMeadow() {
        super(properties);
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.extraTreeChance = -999;
        theBiomeDecorator.flowersPerChunk = 15;
        theBiomeDecorator.grassPerChunk = 15;
        theBiomeDecorator.reedsPerChunk = 2;

        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 1, 1, 2));

        this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values()) {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
        double d0 = GRASS_COLOR_NOISE.getValue((double) pos.getX() / 200.0D, (double) pos.getZ() / 200.0D);

        if (d0 < -0.8D) {
            int j = rand.nextInt(4);

            switch (j) {
                case 0:
                    return BlockFlower.EnumFlowerType.ORANGE_TULIP;
                case 1:
                    return BlockFlower.EnumFlowerType.RED_TULIP;
                case 2:
                    return BlockFlower.EnumFlowerType.PINK_TULIP;
                case 3:
                default:
                    return BlockFlower.EnumFlowerType.WHITE_TULIP;
            }
        } else if (rand.nextInt(3) > 0) {
            int i = rand.nextInt(3);
            return i == 0 ? BlockFlower.EnumFlowerType.POPPY : (i == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY);
        } else {
            return BlockFlower.EnumFlowerType.DANDELION;
        }
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xFF4DD838;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xFF4DD838;
    }


    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS)) {
            int chance = rand.nextInt(5) - 3;
            chance += 2;
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
