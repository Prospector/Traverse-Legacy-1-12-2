package prospector.traverse.world.biomes;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import prospector.traverse.world.ITreeConstants;
import prospector.traverse.world.features.WorldGenFallenTree;

import java.util.Random;

public class BiomeBadlands extends Biome implements ITreeConstants {

    protected static final WorldGenFallenTree FALLEN_TREE_FEATURE = new WorldGenFallenTree(true);

    public static BiomeProperties properties = new BiomeProperties("Badlands");

    static {
        properties.setTemperature(0.6F);
        properties.setRainfall(0.1F);
        properties.setBaseHeight(0.6F);
        properties.setHeightVariation(0.55F);
    }

    public BiomeBadlands() {
        super(properties);
        theBiomeDecorator.treesPerChunk = 0;
        theBiomeDecorator.extraTreeChance = 4;
        theBiomeDecorator.flowersPerChunk = 0;
        theBiomeDecorator.grassPerChunk = 16;

        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2, 4, 4));

        this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values()) {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
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
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE)) {
            int genChance = rand.nextInt(5);
            if (genChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                FALLEN_TREE_FEATURE.generate(worldIn, rand, blockpos);
            }
        }
        super.decorate(worldIn, rand, pos);
    }
}
