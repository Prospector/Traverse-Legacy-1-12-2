package prospector.traverse.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import prospector.traverse.blocks.base.BlockTraverseLeaves;
import prospector.traverse.blocks.base.BlockTraverseSapling;
import prospector.traverse.world.TraverseTreeGenerator;

import java.util.Random;

public class AutumnLeavesSapling {
    public String name;
    public LSLeaves lsLeaves;
    public LSSapling lsSapling;

    public AutumnLeavesSapling(String name) {
        this.name = name;
        lsLeaves = new LSLeaves();
        lsSapling = new LSSapling();
    }

    public class LSLeaves extends BlockTraverseLeaves {

        public LSLeaves() {
            super(name);
        }

        @Override
        public Item getItemDropped(IBlockState state, Random rand, int fortune) {
            return Item.getItemFromBlock(lsSapling);
        }

        protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
            spawnAsEntity(worldIn, pos, new ItemStack(Items.APPLE));
        }
    }

    public class LSSapling extends BlockTraverseSapling {

        public LSSapling() {
            super(name, new TraverseTreeGenerator(false));
        }

        @Override
        public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
            if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
            worldIn.setBlockToAir(pos);
            if (!new TraverseTreeGenerator(true, 4, TraverseTreeGenerator.OAK_LOG, lsLeaves.getDefaultState()).generate(worldIn, rand, pos)) {
                worldIn.setBlockState(pos, state);
            }
        }
    }

}
