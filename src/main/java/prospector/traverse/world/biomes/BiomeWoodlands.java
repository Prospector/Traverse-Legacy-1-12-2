package prospector.traverse.world.biomes;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import prospector.traverse.world.features.WorldGenFallenTree;

import java.util.Random;

public class BiomeWoodlands extends Biome {

    protected static final WorldGenFallenTree FALLEN_TREE_FEATURE = new WorldGenFallenTree(true);
    protected static final IBlockState OAK_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
    protected static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, false);

    public static BiomeProperties properties = new BiomeProperties("Woodlands");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.4F);
        properties.setBaseHeight(0.2F);
        properties.setHeightVariation(0.05F);
    }

    public BiomeWoodlands() {
        super(properties);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2, 4, 4));
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xFF99A955;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFF88B4E1;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xFF849E4A;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new DecoratorWoodlands();
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        int num = rand.nextInt(3);
        if (num == 0) {
            return new WorldGenShrub(OAK_LOG, OAK_LEAF);
        }
        return TREE_FEATURE;
    }

    public class DecoratorWoodlands extends BiomeDecorator {

        public DecoratorWoodlands() {
            treesPerChunk = 4;
            flowersPerChunk = 6;
            grassPerChunk = 16;
        }

        @Override
        protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
            if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, chunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE)) {
                int genChance = random.nextInt(5);
                if (genChance == 0) {
                    int k6 = random.nextInt(16) + 8;
                    int l = random.nextInt(16) + 8;
                    BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));
                    FALLEN_TREE_FEATURE.generate(worldIn, random, blockpos);
                }
            }
            super.genDecorations(biomeIn, worldIn, random);
        }
    }
}
