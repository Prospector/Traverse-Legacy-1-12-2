package prospector.traverse.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.traverse.blocks.base.BlockTraverseLeaves;

import java.util.LinkedHashMap;

public class TraverseBlocks {

    public static LinkedHashMap<String, Block> blocks = new LinkedHashMap<>();

    static {
        addLeaves("red_autumnal");
        addLeaves("orange_autumnal");
        addLeaves("yellow_autumnal");
        addLeaves("brown_autumnal");
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

    static void addLeaves(String name, Item sapling) {
        Block block = new BlockTraverseLeaves(name, sapling);
        blocks.put(name + "_leaves", block);
    }

    static void addLeaves(String name) {
        addLeaves(name, null);
    }
}
