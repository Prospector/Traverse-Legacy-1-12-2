package prospector.traverse.blocks.base;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import prospector.shootingstar.model.ModelCompound;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;

import java.util.Random;

public class BlockTraverseWoodDoor extends BlockDoor {

    public BlockTraverseWoodDoor(String name) {
        super(Material.WOOD);
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_door"));
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(TraverseTab.TAB);
        setSoundType(SoundType.WOOD);
        setHardness(3.0F);
        setHarvestLevel("axe", 0);
        ShootingStar.registerModel(new ModelCompound(TraverseConstants.MOD_ID, this, "door", POWERED));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : getDoorItem().getItem();
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return getDoorItem();
    }

    private ItemStack getDoorItem() {
        return new ItemStack(Item.getItemFromBlock(this));
    }
}
