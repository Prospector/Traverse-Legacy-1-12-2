package prospector.traverse.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.model.ModelCompound;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseTab;

import java.util.Random;

public abstract class BlockTraverseWoodSlab extends BlockTraverseSlab {

    public BlockTraverseWoodSlab(String name) {
        super(name, Material.WOOD, SoundType.WOOD);
        setHarvestLevel("axe", 0);
        setHardness(2.0F);
        setResistance(15);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    public static class Double extends BlockTraverseWoodSlab {
        public Double(String name, Block half) {
            super(name);
            this.halfslab = half;
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