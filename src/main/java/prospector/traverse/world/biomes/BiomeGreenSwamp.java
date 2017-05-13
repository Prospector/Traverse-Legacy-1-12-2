package prospector.traverse.world.biomes;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFossils;
import prospector.traverse.world.ITreeConstants;
import prospector.traverse.world.features.WorldGenCustomSwampTree;

import java.util.Random;

public class BiomeGreenSwamp extends Biome implements ITreeConstants {

    protected static final WorldGenCustomSwampTree CUSTOM_SWAMP_TREE_FEATURE = new WorldGenCustomSwampTree(true, 7);
    protected static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();
    public static BiomeProperties properties = new BiomeProperties("Green Swamp");

    static {
        properties.setTemperature(Biomes.SWAMPLAND.getTemperature());
        properties.setBaseHeight(Biomes.SWAMPLAND.getBaseHeight());
        properties.setHeightVariation(Biomes.SWAMPLAND.getHeightVariation());
    }

    public BiomeGreenSwamp() {
        super(properties);
        theBiomeDecorator.treesPerChunk = 2;
        theBiomeDecorator.flowersPerChunk = 5;
        theBiomeDecorator.deadBushPerChunk = 1;
        theBiomeDecorator.mushroomsPerChunk = 4;
        theBiomeDecorator.reedsPerChunk = 10;
        theBiomeDecorator.clayPerChunk = 1;
        theBiomeDecorator.waterlilyPerChunk = 4;
        theBiomeDecorator.sandPerChunk2 = 0;
        theBiomeDecorator.sandPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 10;

        spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }

    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        return CUSTOM_SWAMP_TREE_FEATURE;
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        double d0 = GRASS_COLOR_NOISE.getValue((double) x * 0.25D, (double) z * 0.25D);

        if (d0 > 0.0D) {
            int i = x & 15;
            int j = z & 15;

            for (int k = 255; k >= 0; --k) {
                if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR) {
                    if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER) {
                        chunkPrimerIn.setBlockState(j, k, i, WATER);

                        if (d0 < 0.12D) {
                            chunkPrimerIn.setBlockState(j, k + 1, i, WATER_LILY);
                        }
                    }

                    break;
                }
            }
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
            if (rand.nextInt(64) == 0) {
                (new WorldGenFossils()).generate(worldIn, rand, pos);
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
    public void addDefaultFlowers() {
        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.BLUE_ORCHID), 10);
    }
}
