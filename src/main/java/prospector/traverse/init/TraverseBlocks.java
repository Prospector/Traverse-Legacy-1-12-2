package prospector.traverse.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.traverse.blocks.AutumnLeavesSapling;
import prospector.traverse.blocks.FirLeavesSapling;
import prospector.traverse.blocks.base.BlockTraverseWoodLog;
import prospector.traverse.blocks.base.BlockTraverseWoodPlanks;
import prospector.traverse.blocks.base.BlockTraverseWoodSlab;
import prospector.traverse.blocks.base.BlockTraverseWoodStairs;

import java.util.LinkedHashMap;

public class TraverseBlocks {

    public static LinkedHashMap<String, Block> blocks = new LinkedHashMap<>();
    public static LinkedHashMap<BlockTraverseWoodSlab, BlockTraverseWoodSlab> slabs = new LinkedHashMap<>();

    static {
        addAutumnTreeStuff("red");
        addAutumnTreeStuff("brown");
        addAutumnTreeStuff("orange");
        addAutumnTreeStuff("yellow");
        addFirTreeStuff();
    }

    public static void initialize() {
        for (Block block : blocks.values()) {
            if (!(block instanceof BlockTraverseWoodSlab)) {
                registerBlock(block);
            }
        }
        for (BlockTraverseWoodSlab halfSlab : slabs.keySet()) {
            BlockTraverseWoodSlab doubleSlab = slabs.get(halfSlab);
            registerBlockWithoutItem(halfSlab);
            registerBlockWithoutItem(doubleSlab);
            GameRegistry.register(new ItemSlab(blocks.get(halfSlab.name + "_slab"), halfSlab, doubleSlab), halfSlab.getRegistryName());
        }
    }

    static void registerBlock(Block block) {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), block.getRegistryName());
    }

    static void registerBlockWithoutItem(Block block) {
        GameRegistry.register(block);
    }

    static void registerBlock(Block block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
    }

    static void addAutumnTreeStuff(String colour) {
        if (colour.isEmpty()) {
            return;
        }
        colour = colour + "_autumnal";
        AutumnLeavesSapling leavesSapling = new AutumnLeavesSapling(colour);
        blocks.put(colour + "_leaves", leavesSapling.lsLeaves);
        blocks.put(colour + "_sapling", leavesSapling.lsSapling);
    }

    static void addFirTreeStuff() {
        String fir = "fir";
        FirLeavesSapling leavesSapling = new FirLeavesSapling();
        blocks.put(fir + "_leaves", leavesSapling.lsLeaves);
        blocks.put(fir + "_sapling", leavesSapling.lsSapling);

        BlockTraverseWoodLog log;
        BlockTraverseWoodPlanks planks;
        BlockTraverseWoodStairs stairs;
        BlockTraverseWoodSlab.Half half_slab;
        BlockTraverseWoodSlab.Double double_slab;
        log = new BlockTraverseWoodLog(fir);
        planks = new BlockTraverseWoodPlanks(fir);
        stairs = new BlockTraverseWoodStairs(planks.getDefaultState(), fir);
        half_slab = new BlockTraverseWoodSlab.Half(fir);
        double_slab = new BlockTraverseWoodSlab.Double(fir, half_slab);
        slabs.put(half_slab, double_slab);
        blocks.put(fir + "_log", log);
        blocks.put(fir + "_planks", planks);
        blocks.put(fir + "_stairs", stairs);
        blocks.put(fir + "_slab", half_slab);
        blocks.put(fir + "_double_slab", double_slab);
    }
}
