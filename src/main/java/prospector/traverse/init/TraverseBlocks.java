package prospector.traverse.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import prospector.traverse.blocks.AutumnLeavesSapling;
import prospector.traverse.blocks.FirLeavesSapling;
import prospector.traverse.blocks.base.*;
import prospector.traverse.item.ItemTraverseWoodDoor;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Mod.EventBusSubscriber
public class TraverseBlocks {

    public static LinkedHashMap<String, Block> blocks = new LinkedHashMap<>();
    public static LinkedHashMap<BlockTraverseWoodSlab, BlockTraverseWoodSlab> slabs = new LinkedHashMap<>();
    public static HashMap<String, Block> oreDictNames = new HashMap<>();

    static {
        addAutumnTreeStuff("red");
        addAutumnTreeStuff("brown");
        addAutumnTreeStuff("orange");
        addAutumnTreeStuff("yellow");
        addFirTreeStuff();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : blocks.values()) {
            if (!(block instanceof BlockTraverseWoodSlab || block instanceof BlockTraverseWoodDoor)) {
                registerBlock(block, event);
            }
            if (block instanceof BlockTraverseWoodDoor) {
                registerBlock(block, new ItemTraverseWoodDoor(block), event);
            }
        }
        for (BlockTraverseWoodSlab halfSlab : slabs.keySet()) {
            BlockTraverseWoodSlab doubleSlab = slabs.get(halfSlab);
            registerBlockWithoutItem(halfSlab, event);
            registerBlockWithoutItem(doubleSlab, event);
            ForgeRegistries.ITEMS.register(new ItemSlab(blocks.get(halfSlab.name + "_slab"), halfSlab, doubleSlab).setRegistryName(halfSlab.getRegistryName()));
        }
        for (String name : oreDictNames.keySet()) {
            OreDictionary.registerOre(name, oreDictNames.get(name));
        }
    }

    static void registerBlock(Block block, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    static void registerBlockWithoutItem(Block block, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);
    }

    static void registerBlock(Block block, ItemBlock itemBlock, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
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
        BlockTraverseWoodSlab.Half halfSlab;
        BlockTraverseWoodSlab.Double doubleSlab;
        BlockTraverseWoodDoor door;
        BlockTraverseWoodFence fence;
        BlockTraverseWoodFenceGate fenceGate;
        log = new BlockTraverseWoodLog(fir);
        planks = new BlockTraverseWoodPlanks(fir);
        stairs = new BlockTraverseWoodStairs(planks.getDefaultState(), fir);
        halfSlab = new BlockTraverseWoodSlab.Half(fir);
        doubleSlab = new BlockTraverseWoodSlab.Double(fir, halfSlab);
        door = new BlockTraverseWoodDoor(fir);
        fence = new BlockTraverseWoodFence(fir);
        fenceGate = new BlockTraverseWoodFenceGate(fir);
        slabs.put(halfSlab, doubleSlab);
        blocks.put(fir + "_log", log);
        blocks.put(fir + "_planks", planks);
        blocks.put(fir + "_stairs", stairs);
        blocks.put(fir + "_slab", halfSlab);
        blocks.put(fir + "_double_slab", doubleSlab);
        blocks.put(fir + "_door", door);
        blocks.put(fir + "_fence", fence);
        blocks.put(fir + "_fence_gate", fenceGate);

        oreDictNames.put("logWood", log);
        oreDictNames.put("plankWood", planks);
        oreDictNames.put("slabWood", halfSlab);
        oreDictNames.put("stairWood", stairs);
        oreDictNames.put("treeSapling", leavesSapling.lsSapling);
        oreDictNames.put("treeLeaves", leavesSapling.lsLeaves);
    }
}
