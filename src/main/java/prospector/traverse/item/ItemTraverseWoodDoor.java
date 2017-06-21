package prospector.traverse.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// Stolen from Nomagi
public class ItemTraverseWoodDoor extends ItemBlock {

    public ItemTraverseWoodDoor(Block block) {
        super(block);
        setRegistryName(block.getRegistryName());
    }

    public static void placeDoor(World world, BlockPos pos, EnumFacing facing, Block door, boolean isRightHinge) {
        BlockPos blockpos = pos.offset(facing.rotateY());
        BlockPos blockpos1 = pos.offset(facing.rotateYCCW());

        IBlockState state = world.getBlockState(blockpos);
        IBlockState stateUp = world.getBlockState(blockpos.up());
        IBlockState state1 = world.getBlockState(blockpos1);
        IBlockState state1Up = world.getBlockState(blockpos1.up());

        int j = (state.isNormalCube() ? 1 : 0) + (stateUp.isNormalCube() ? 1 : 0);
        int i = (state1.isNormalCube() ? 1 : 0) + (state1Up.isNormalCube() ? 1 : 0);

        boolean flag1 = state.getBlock() == door || stateUp.getBlock() == door;
        boolean flag = state1.getBlock() == door || state1Up.getBlock() == door;

        if ((!flag || flag1) && j <= i)
            isRightHinge = !(flag1 && !flag || j < i);

        boolean powered = world.isBlockPowered(pos) || world.isBlockPowered(pos.up());
        IBlockState placeSate = door.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HINGE, isRightHinge ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.POWERED, powered).withProperty(BlockDoor.OPEN, powered);
        world.setBlockState(pos, placeSate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
        world.setBlockState(pos.up(), placeSate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);
        world.notifyNeighborsOfStateChange(pos, door, false);
        world.notifyNeighborsOfStateChange(pos.up(), door, false);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (facing != EnumFacing.UP)
            return EnumActionResult.FAIL;

        IBlockState state = world.getBlockState(pos);
        ItemStack held = player.getHeldItem(hand);

        if (!state.getBlock().isReplaceable(world, pos)) {
            pos = pos.offset(facing);
            state = world.getBlockState(pos);
        }

        if (player.canPlayerEdit(pos, facing, held) && block.canPlaceBlockAt(world, pos)) {
            EnumFacing enumfacing = EnumFacing.fromAngle((double) player.rotationYaw);
            int offsetX = enumfacing.getFrontOffsetX();
            int offsetZ = enumfacing.getFrontOffsetZ();
            boolean isRightHinge = offsetX < 0 && hitZ < 0.5F || offsetX > 0 && hitZ > 0.5F || offsetZ < 0 && hitX > 0.5F || offsetZ > 0 && hitX < 0.5F;

            placeDoor(world, pos, enumfacing, block, isRightHinge);
            SoundType soundtype = SoundType.WOOD;
            world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            held.shrink(1);
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }
}