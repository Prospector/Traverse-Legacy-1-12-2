package prospector.traverse.blocks;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.model.ModelCompound;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;
import prospector.traverse.init.TraverseBlocks;

public class BlockTraverseDeadGrass extends BlockTallGrass {

    public static final AxisAlignedBB AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public BlockTraverseDeadGrass() {
        super();
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, "dead_grass"));
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(TraverseTab.TAB);
        setSoundType(SoundType.PLANT);
        ShootingStar.registerModel(new ModelCompound(TraverseConstants.MOD_ID, this, "plant", TYPE));
    }

    public boolean canSustainBush(IBlockState state) {
        return state.getBlock() == TraverseBlocks.blocks.get("red_rock") || state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.HARDENED_CLAY || state.getBlock() == Blocks.STAINED_HARDENED_CLAY;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
    }

    public int damageDropped(IBlockState state) {
        return 0;
    }

    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }
}
