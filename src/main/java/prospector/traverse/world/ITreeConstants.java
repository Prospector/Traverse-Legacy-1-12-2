package prospector.traverse.world;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import prospector.traverse.init.TraverseBlocks;

public interface ITreeConstants {
    IBlockState OAK_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
    IBlockState BIRCH_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
    IBlockState SPRUCE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
    IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    IBlockState ACACIA_LOG = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
    IBlockState DARK_OAK_LOG = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
    IBlockState FIR_LOG = TraverseBlocks.blocks.get("fir_log").getDefaultState();

    IBlockState OAK_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState BIRCH_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState SPRUCE_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState JUNGLE_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState ACACIA_LEAVES = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState DARK_OAK_LEAVES = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState RED_AUTUMNAL_LEAVES = TraverseBlocks.blocks.get("red_autumnal_leaves").getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState BROWN_AUTUMNAL_LEAVES = TraverseBlocks.blocks.get("brown_autumnal_leaves").getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState ORANGE_AUTUMNAL_LEAVES = TraverseBlocks.blocks.get("orange_autumnal_leaves").getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState YELLOW_AUTUMNAL_LEAVES = TraverseBlocks.blocks.get("yellow_autumnal_leaves").getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState FIR_LEAVES = TraverseBlocks.blocks.get("fir_leaves").getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);

}