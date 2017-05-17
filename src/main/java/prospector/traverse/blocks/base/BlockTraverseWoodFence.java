package prospector.traverse.blocks.base;

import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import prospector.shootingstar.model.ModelCompound;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;

public class BlockTraverseWoodFence extends BlockFence {

    public BlockTraverseWoodFence(String name) {
        super(Material.WOOD, MapColor.WOOD);
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_fence"));
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(TraverseTab.TAB);
        setSoundType(SoundType.WOOD);
        setHardness(2.0F);
        setResistance(5.0F);
        ShootingStar.registerModel(new ModelCompound(TraverseConstants.MOD_ID, this, "fence"));
    }
}
