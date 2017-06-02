package prospector.traverse.world.biomes;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import prospector.traverse.world.ITreeConstants;
import prospector.traverse.world.features.WorldGenFallenTree;
import prospector.traverse.world.features.WorldGenPatch;

import java.util.Random;

public class BiomeBadlands extends Biome implements ITreeConstants {

    protected static final WorldGenFallenTree FALLEN_TREE_FEATURE = new WorldGenFallenTree(true);
    protected static final WorldGenPatch STONE_PATCH_FEATURE = new WorldGenPatch(Blocks.STONE.getDefaultState(), 22);
    protected static final WorldGenBlockBlob COBBLESTONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.COBBLESTONE, 2);
    protected static final WorldGenLakes LAVA_LAKE_FEATURE = new WorldGenLakes(Blocks.LAVA);

    public static BiomeProperties properties = new BiomeProperties("Badlands");

    static {
        properties.setTemperature(0.6F);
        properties.setRainfall(0.1F);
        properties.setBaseHeight(0.6F);
        properties.setHeightVariation(0.55F);
    }

    public BiomeBadlands() {
        super(properties);
        theBiomeDecorator.treesPerChunk = 1;
        theBiomeDecorator.extraTreeChance = 4;
        theBiomeDecorator.flowersPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 16;

        spawnableCreatureList.clear();
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 4, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 3, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 3, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 2, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 1, 2, 7));
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xFFDBC77C;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFFFCE96;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xFFC2C168;
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        int num = rand.nextInt(2);
        if (num == 0) {
            return new WorldGenShrub(OAK_LOG, OAK_LEAVES);
        }

        return TREE_FEATURE;
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int i = worldIn.getSeaLevel();
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j1 = 255; j1 >= 0; --j1) {
            if (j1 <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (iblockstate2.getBlock() == Blocks.STONE) {
                    if (j == -1) {
                        if (k <= 0) {
                            iblockstate = AIR;
                            iblockstate1 = STONE;
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
                            if (this.getFloatTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
                                iblockstate = ICE;
                            } else {
                                iblockstate = WATER;
                            }
                        }

                        j = k;

                        if (j1 >= i - 1) {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        } else if (j1 < i - 7 - k) {
                            iblockstate = AIR;
                            iblockstate1 = STONE;
                            chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
                        } else {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

                        if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {

        int stonePatchChance = rand.nextInt(2);
        if (stonePatchChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
            STONE_PATCH_FEATURE.generate(worldIn, rand, blockpos);
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE)) {
            int fallenTreeChance = rand.nextInt(3);
            if (fallenTreeChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                FALLEN_TREE_FEATURE.generate(worldIn, rand, blockpos);
            }
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
            int boulderChance = rand.nextInt(5);
            if (boulderChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                COBBLESTONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
            }
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA)) {
            int boulderChance = rand.nextInt(12);
            if (boulderChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                LAVA_LAKE_FEATURE.generate(worldIn, rand, blockpos);
            }
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);
            for (int i = 0; i < 7; ++i) {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
            }
        }

        super.decorate(worldIn, rand, pos);
    }
}
