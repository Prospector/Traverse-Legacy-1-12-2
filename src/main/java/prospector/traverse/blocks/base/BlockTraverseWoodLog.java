package prospector.traverse.blocks.base;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.model.ModelCompound;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;

public class BlockTraverseWoodLog extends BlockLog {

    public BlockTraverseWoodLog(String name) {
        super();
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_log"));
        setCreativeTab(TraverseTab.TAB);
        setUnlocalizedName(getRegistryName().toString());
        setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        ShootingStar.registerModel(new ModelCompound(TraverseConstants.MOD_ID, this, "log"));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{LOG_AXIS});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LOG_AXIS).ordinal();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        for (EnumAxis axis : EnumAxis.values()) {
            if (axis.ordinal() == meta) {
                return getDefaultState().withProperty(LOG_AXIS, axis);
            }
        }
        return getDefaultState();
    }

    public int damageDropped(IBlockState state) {
        return 0;
    }
}
