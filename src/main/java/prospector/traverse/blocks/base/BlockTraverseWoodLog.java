package prospector.traverse.blocks.base;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseMod;
import prospector.traverse.core.TraverseTab;

public class BlockTraverseWoodLog extends BlockLog {

    public BlockTraverseWoodLog(String name) {
        super();
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_log"));
        setCreativeTab(TraverseTab.TAB);
        setUnlocalizedName(getRegistryName().toString());
        setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        TraverseMod.blockModelsToRegister.add(this);
    }

    public int damageDropped(IBlockState state) {
        return 0;
    }
}
