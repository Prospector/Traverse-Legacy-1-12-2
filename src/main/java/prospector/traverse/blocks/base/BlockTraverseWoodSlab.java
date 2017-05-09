package prospector.traverse.blocks.base;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import prospector.traverse.core.TraverseMod;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;

import java.util.Random;

public abstract class BlockTraverseWoodSlab extends BlockSlab {

    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<BlockTraverseWoodSlab.Variant>create("variant", BlockTraverseWoodSlab.Variant.class);
    private Item item = Item.getItemFromBlock(Blocks.PURPUR_SLAB);

    public BlockTraverseWoodSlab(String name) {
        super(Material.WOOD, MapColor.WOOD);
        IBlockState iblockstate = this.blockState.getBaseState();

        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
            setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_slab"));
        } else {
            setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_double_slab"));
        }
        setCreativeTab(TraverseTab.TAB);
        setUnlocalizedName(getRegistryName().toString());
        setHarvestLevel("axe", 0);
        setHardness(2.0F);
        setResistance(15);
        setSoundType(SoundType.WOOD);
        TraverseMod.blockModelsToRegister.add(this);

        this.setDefaultState(iblockstate.withProperty(VARIANT, BlockTraverseWoodSlab.Variant.DEFAULT));
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return item;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(item);
    }

    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockTraverseWoodSlab.Variant.DEFAULT);

        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[]{VARIANT}) : new BlockStateContainer(this, new IProperty[]{HALF, VARIANT});
    }

    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack) {
        return BlockTraverseWoodSlab.Variant.DEFAULT;
    }

    public static enum Variant implements IStringSerializable {
        DEFAULT;

        public String getName() {
            return "default";
        }
    }

    public static class Double extends BlockTraverseWoodSlab {
        public Double(String name) {
            super(name);
        }

        public boolean isDouble() {
            return true;
        }
    }

    public static class Half extends BlockTraverseWoodSlab {
        public Half(String name) {
            super(name);
        }

        public boolean isDouble() {
            return false;
        }
    }
}