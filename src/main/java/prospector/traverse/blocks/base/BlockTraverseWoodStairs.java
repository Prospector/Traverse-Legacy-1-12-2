package prospector.traverse.blocks.base;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import prospector.shootingstar.model.ModelCompound;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;

public class BlockTraverseWoodStairs extends BlockStairs {

    public BlockTraverseWoodStairs(IBlockState modelState, String name) {
        super(modelState);
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_stairs"));
        setCreativeTab(TraverseTab.TAB);
        setUnlocalizedName(getRegistryName().toString());
        ShootingStar.registerModel(new ModelCompound(TraverseConstants.MOD_ID, this, "stairs"));
    }
}