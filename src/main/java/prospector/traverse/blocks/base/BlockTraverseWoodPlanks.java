package prospector.traverse.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import prospector.shootingstar.model.ModelCompound;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;

public class BlockTraverseWoodPlanks extends Block {

    public BlockTraverseWoodPlanks(String name) {
        super(Material.WOOD);
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_planks"));
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(TraverseTab.TAB);
        setDefaultState(blockState.getBaseState());
        setHarvestLevel("axe", 0);
        setHardness(2.0F);
        setResistance(15);
        setSoundType(SoundType.WOOD);
        ShootingStar.registerModel(new ModelCompound(TraverseConstants.MOD_ID, this, "planks"));
    }

    public MapColor getMapColor(IBlockState state) {
        return MapColor.WOOD;
    }
}
