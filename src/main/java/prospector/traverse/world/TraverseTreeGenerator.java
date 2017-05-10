package prospector.traverse.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class TraverseTreeGenerator extends WorldGenerator {

    public boolean isWorldGen = true;

    public TraverseTreeGenerator(boolean isWorldGen) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
    }

    @Override
    public abstract boolean generate(World worldIn, Random rand, BlockPos position);

    public boolean growTree(World world, Random rand, int x, int y, int z) {
        return false;
    }
}