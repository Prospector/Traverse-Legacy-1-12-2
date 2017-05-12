package prospector.traverse.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.traverse.blocks.AutumnLeavesSapling;
import prospector.traverse.blocks.base.*;
import prospector.traverse.world.TraverseTreeGenerator;

import java.util.LinkedHashMap;

public class TraverseBlocks {

    public static LinkedHashMap<String, Block> blocks = new LinkedHashMap<>();

    static {
        addAutumnTreeStuff("red");
        addAutumnTreeStuff("brown");
        addAutumnTreeStuff("orange");
        addAutumnTreeStuff("yellow");
        registerBlock(new BlockTraverseLeafPile("autumnal"));
    }

    public static void initialize() {
        for (Block block : blocks.values()) {
            registerBlock(block);
        }
    }

    static void registerBlock(Block block) {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), block.getRegistryName());
    }

    static void registerBlock(Block block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
    }

    static Block addLeaves(String name, Item sapling) {
        Block block = new BlockTraverseLeaves(name, sapling);
        blocks.put(name + "_leaves", block);
        return block;
    }

    static Block addSapling(String name, TraverseTreeGenerator generator) {
        Block block = new BlockTraverseSapling(name, generator);
        blocks.put(name + "_sapling", block);
        return block;
    }

    static Block addLeaves(String name) {
        return addLeaves(name, null);
    }

    static void addTreeStuff(String name, boolean hasLeaves, boolean hasLog) {
        addTreeStuff(name, hasLeaves, null, hasLog);
    }

    static void addTreeStuff(String name, boolean hasLeaves, TraverseTreeGenerator generator, boolean hasLog) {
        if (name.isEmpty()) {
            return;
        }
        BlockTraverseLeaves leaves = null;
        BlockTraverseSapling sapling = null;
        BlockTraverseWoodLog log = null;
        BlockTraverseWoodPlanks planks = null;
        BlockTraverseWoodStairs stairs = null;
        BlockTraverseWoodSlab.Half half_slab = null;
        BlockTraverseWoodSlab.Double double_slab = null;
        if (hasLeaves) {
            leaves = new BlockTraverseLeaves(name);
        }
        if (hasLog) {
            //TODO: Log stuff needs to be done. Also do planks, slabs, and stairs here
        }
        if (generator != null) {
            sapling = new BlockTraverseSapling(name, generator);
        }
        leaves.sapling = Item.getItemFromBlock(sapling);
        blocks.put(name + "_leaves", leaves);
        blocks.put(name + "_sapling", sapling);
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
}
