package prospector.traverse.blocks.base;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseMod;
import prospector.traverse.core.TraverseTab;
import prospector.traverse.world.TraverseTreeGenerator;

import java.util.Random;

public class BlockTraverseSapling extends BlockSapling {

    public TraverseTreeGenerator generator;

    public BlockTraverseSapling(String name, TraverseTreeGenerator generator) {
        super();
        this.generator = generator;
        setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name + "_sapling"));
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(TraverseTab.TAB);
        TraverseMod.blockModelsToRegister.add(this);

    }

    @Override
    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        worldIn.setBlockToAir(pos);
        if (!generator.growTree(worldIn, rand, pos.getX(), pos.getY(), pos.getZ())) {
            worldIn.setBlockState(pos, state);
        }
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, 0));
    }
}
