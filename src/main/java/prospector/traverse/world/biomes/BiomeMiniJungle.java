package prospector.traverse.world.biomes;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import prospector.traverse.world.ITreeConstants;

import java.util.Random;

public class BiomeMiniJungle extends Biome implements ITreeConstants {

    public static BiomeProperties properties = new BiomeProperties("Mini Jungle");

    static {
        properties.setTemperature(Biomes.JUNGLE.getTemperature());
        properties.setRainfall(Biomes.JUNGLE.getRainfall());
        properties.setBaseHeight(-0.1F);
        properties.setHeightVariation(0.5F);
        properties.setWaterColor(0xFF24B01C);
    }

    public BiomeMiniJungle() {
        super(properties);
        theBiomeDecorator.treesPerChunk = 30;
        theBiomeDecorator.flowersPerChunk = 5;
        theBiomeDecorator.grassPerChunk = 25;
        theBiomeDecorator.reedsPerChunk = 2;
        theBiomeDecorator.clayPerChunk = 3;
        theBiomeDecorator.waterlilyPerChunk = 12;

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 1, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 4, 4, 4));
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFC2FFEB;
    }

    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }


    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        return rand.nextInt(10) == 0 ? BIG_TREE_FEATURE : new WorldGenTrees(false, 2 + rand.nextInt(3), JUNGLE_LOG, JUNGLE_LEAVES, true);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
        int i = rand.nextInt(16) + 8;
        int j = rand.nextInt(16) + 8;
        int height = worldIn.getHeight(pos.add(i, 0, j)).getY() * 2; // could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int k = rand.nextInt(height);
        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN))
            (new WorldGenMelon()).generate(worldIn, rand, pos.add(i, k, j));
        WorldGenVines worldgenvines = new WorldGenVines();

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
            for (j = 0; j < 50; ++j) {
                k = rand.nextInt(16) + 8;
                int l = 128;
                int i1 = rand.nextInt(16) + 8;
                worldgenvines.generate(worldIn, rand, pos.add(k, 128, i1));
                EntityPig pig = new EntityPig(worldIn);
            }
    }
}
