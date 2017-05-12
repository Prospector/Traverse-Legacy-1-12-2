package prospector.traverse.world;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class TraverseTreeGenerator extends WorldGenTrees {

    public static final IBlockState OAK_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
    public static final IBlockState OAK_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

    public boolean isWorldGen = true;

    public TraverseTreeGenerator(boolean isWorldGen) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
    }

    public TraverseTreeGenerator(boolean isWorldGen, int minTreeHeight, IBlockState logState, IBlockState leavesState, boolean vinesGrow) {
        super(!isWorldGen, minTreeHeight, logState, leavesState, vinesGrow);
        this.isWorldGen = isWorldGen;
    }

    public TraverseTreeGenerator(boolean isWorldGen, int minTreeHeight, IBlockState logState, IBlockState leavesState) {
        super(!isWorldGen, minTreeHeight, logState, leavesState, false);
        this.isWorldGen = isWorldGen;
    }

    public TraverseTreeGenerator(boolean isWorldGen, IBlockState logState, IBlockState leavesState) {
        super(!isWorldGen, 4, logState, leavesState, false);
        this.isWorldGen = isWorldGen;
    }
}