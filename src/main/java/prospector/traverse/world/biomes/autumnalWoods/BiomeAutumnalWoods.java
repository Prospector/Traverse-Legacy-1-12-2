package prospector.traverse.world.biomes.autumnalWoods;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import prospector.traverse.init.TraverseBlocks;

import java.util.Random;

public class BiomeAutumnalWoods extends Biome {
    public BiomeAutumnalWoods(BiomeProperties properties) {
        super(properties);
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
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
    public BiomeDecorator createBiomeDecorator() {
        return new DecoratorAutumnalWoods();
    }

    public class DecoratorAutumnalWoods extends BiomeDecorator {

        public int rubberTreesPerChunk;
        GeneratorAutumnalTree redTreeGenerator = new GeneratorAutumnalTree(TraverseBlocks.blocks.get("red_autumnal_leaves").getDefaultState());
        GeneratorAutumnalTree brownTreeGenerator = new GeneratorAutumnalTree(TraverseBlocks.blocks.get("brown_autumnal_leaves").getDefaultState());
        GeneratorAutumnalTree orangeTreeGenerator = new GeneratorAutumnalTree(TraverseBlocks.blocks.get("orange_autumnal_leaves").getDefaultState());
        GeneratorAutumnalTree yellowTreeGenerator = new GeneratorAutumnalTree(TraverseBlocks.blocks.get("yellow_autumnal_leaves").getDefaultState());

        public DecoratorAutumnalWoods() {
            rubberTreesPerChunk = 4;
            treesPerChunk = 0;
            grassPerChunk = 2;
        }

        @Override
        protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
            int k1 = this.rubberTreesPerChunk;

            if (random.nextFloat() < this.extraTreeChance) {
                ++k1;
            }

            if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, chunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE))
                for (int j2 = 0; j2 < k1; ++j2) {
                    int k6 = random.nextInt(16) + 8;
                    int l = random.nextInt(16) + 8;
                    WorldGenAbstractTree worldgenabstracttree = biomeIn.genBigTreeChance(random);
                    worldgenabstracttree.setDecorationDefaults();
                    BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));
                    int colour = random.nextInt(3);
                    switch (colour) {
                        case 0:
                            redTreeGenerator.generate(worldIn, random, blockpos);
                        case 1:
                            brownTreeGenerator.generate(worldIn, random, blockpos);
                        case 2:
                            orangeTreeGenerator.generate(worldIn, random, blockpos);
                        default:
                            yellowTreeGenerator.generate(worldIn, random, blockpos);
                    }
                }
            super.genDecorations(biomeIn, worldIn, random);
        }
    }

    public class GeneratorAutumnalTree extends WorldGenerator {

        public int treeBaseHeight = 4;
        public IBlockState leaves;
        boolean isWorldGen = true;

        public GeneratorAutumnalTree(IBlockState leaves) {
            super();
            this.leaves = leaves;
        }

        public GeneratorAutumnalTree(IBlockState leaves, boolean isWorldGen) {
            super(!isWorldGen);
            this.leaves = leaves;
            this.isWorldGen = isWorldGen;
        }

        @Override
        public boolean generate(World worldIn, Random rand, BlockPos position) {
            int retries = rand.nextInt(3) + 2;
            for (int c = 0; c < retries; c++) {
                int x = position.getX() + 8 + rand.nextInt(16);
                int z = position.getZ() + 8 + rand.nextInt(16);
                int y = worldIn.getHeight(x, z) - 1;
                while (worldIn.isAirBlock(new BlockPos(x, y, z)) && y > 0) {
                    y--;
                }
                if (!growTree(worldIn, rand, x, y + 1, z)) {
                    retries--;
                }
            }

            return false;
        }

        public boolean growTree(World world, Random rand, int x, int y, int z) {
            int treeHeight = rand.nextInt(5) + treeBaseHeight;
            int worldHeight = world.getHeight();
            if (y >= 1 && y + treeHeight + 1 <= worldHeight) {
                int xOffset;
                int yOffset;
                int zOffset;
                IBlockState baseSate = world.getBlockState(new BlockPos(x, y - 1, z));
                Block baseBlock = baseSate.getBlock();
                boolean hasPlacedBlock = false;
                if (baseBlock != null && baseBlock.canSustainPlant(baseSate, world, new BlockPos(x, y - 1, z),
                        EnumFacing.UP, (IPlantable) Blocks.SAPLING) && y < worldHeight - treeHeight - 1) {
                    for (yOffset = y; yOffset <= y + 1 + treeHeight; ++yOffset) {
                        byte radius = 1;
                        if (yOffset == y) {
                            radius = 0;
                        }
                        if (yOffset >= y + 1 + treeHeight - 2) {
                            radius = 2;
                        }
                        if (yOffset >= 0 & yOffset < worldHeight) {
                            for (xOffset = x - radius; xOffset <= x + radius; ++xOffset) {
                                for (zOffset = z - radius; zOffset <= z + radius; ++zOffset) {
                                    IBlockState state = world.getBlockState(new BlockPos(xOffset, yOffset, zOffset));
                                    Block block = state.getBlock();

                                    if (block != null
                                            && !(block.isLeaves(state, world, new BlockPos(xOffset, yOffset, zOffset))
                                            || block.isAir(state, world, new BlockPos(xOffset, yOffset, zOffset))
                                            || block.canBeReplacedByLeaves(state, world,
                                            new BlockPos(xOffset, yOffset, zOffset)))) {
                                        return false;
                                    }
                                }
                            }
                        } else {
                            return false;
                        }
                    }

                    BlockPos treeBase = new BlockPos(x, y, z);
                    BlockPos treeRoot = treeBase.down();
                    IBlockState state = world.getBlockState(treeRoot);
                    state.getBlock().onPlantGrow(state, world, treeRoot, treeBase);
                    for (yOffset = y - 3 + treeHeight; yOffset <= y + treeHeight; ++yOffset) {
                        int var12 = yOffset - (y + treeHeight), center = 1 - var12 / 2;
                        for (xOffset = x - center; xOffset <= x + center; ++xOffset) {
                            int xPos = xOffset - x, t = xPos >> 15;
                            xPos = (xPos + t) ^ t;
                            for (zOffset = z - center; zOffset <= z + center; ++zOffset) {
                                int zPos = zOffset - z;
                                zPos = (zPos + (t = zPos >> 31)) ^ t;
                                IBlockState state1 = world.getBlockState(new BlockPos(xOffset, yOffset, zOffset));
                                Block block = state1.getBlock();
                                if (((xPos != center | zPos != center) || rand.nextInt(2) != 0 && var12 != 0)
                                        && (block == null
                                        || block.isLeaves(state1, world, new BlockPos(xOffset, yOffset, zOffset))
                                        || block.isAir(state1, world, new BlockPos(xOffset, yOffset, zOffset))
                                        || block.canBeReplacedByLeaves(state1, world,
                                        new BlockPos(xOffset, yOffset, zOffset)))) {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(xOffset, yOffset, zOffset),
                                            leaves);
                                    hasPlacedBlock = true;
                                }
                            }
                        }
                    }

                    for (yOffset = 0; yOffset < treeHeight; ++yOffset) {
                        BlockPos blockpos = new BlockPos(x, y + yOffset, z);
                        IBlockState state1 = world.getBlockState(blockpos);
                        Block block = state1.getBlock();
                        if (block == null || block.isAir(state1, world, blockpos) || block.isLeaves(state1, world, blockpos) || block.isReplaceable(world, blockpos)) {
                            IBlockState log = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
                            this.setBlockAndNotifyAdequately(world, blockpos, log);
                            hasPlacedBlock = true;
                        }
                    }
                }
                return hasPlacedBlock;
            }
            return false;
        }
    }
}
