package prospector.traverse.blocks.base;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import prospector.shootingstar.model.ModelCompound;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseMod;
import prospector.traverse.core.TraverseTab;

public class BlockTraverseWoodFenceGate extends BlockFenceGate {

    public BlockTraverseWoodFenceGate(String name) {
        super(BlockPlanks.EnumType.OAK);
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_fence_gate"));
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(TraverseTab.TAB);
        setSoundType(SoundType.WOOD);
        setHardness(2.0F);
        setResistance(5.0F);
        TraverseMod.blockModelsToRegister.add(this);
        ShootingStar.registerModel(new ModelCompound(TraverseConstants.MOD_ID, this, "fence", POWERED, IN_WALL));
    }
}
