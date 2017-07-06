package prospector.traverse.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import prospector.shootingstar.BlockCompound;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.blocks.AutumnalLSCompound;
import prospector.traverse.blocks.BlockTraverseDeadGrass;
import prospector.traverse.blocks.FirLSCompound;
import prospector.traverse.blocks.base.*;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.item.ItemTraverseWoodDoor;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TraverseBlocks {

    public static LinkedHashMap<String, Block> blocks = new LinkedHashMap<>();
    public static HashMap<String, Block> oreDictNames = new HashMap<>();

    static {
        addAutumnTreeStuff("red");
        addAutumnTreeStuff("brown");
        addAutumnTreeStuff("orange");
        addAutumnTreeStuff("yellow");
        addFirTreeStuff();
        addStone("red_rock", true, true, true);
        register(new BlockTraverseDeadGrass());
    }

    static void addAutumnTreeStuff(String colour) {
        if (colour.isEmpty()) {
            return;
        }
        AutumnalLSCompound lsCompound = new AutumnalLSCompound(colour);
        register(lsCompound.lsLeaves);
        register(lsCompound.lsSapling);
    }

    public static void register(Block block) {
        blocks.put(block.getRegistryName().getResourcePath(), block);
        ShootingStar.registerBlock(new BlockCompound(TraverseConstants.MOD_ID, block));
    }

    public static void register(Block block, boolean noItemBlock) {
        blocks.put(block.getRegistryName().getResourcePath(), block);
        ShootingStar.registerBlock(new BlockCompound(TraverseConstants.MOD_ID, block, noItemBlock));
    }

    public static void register(Block block, ItemBlock itemBlock) {
        blocks.put(block.getRegistryName().getResourcePath(), block);
        ShootingStar.registerBlock(new BlockCompound(TraverseConstants.MOD_ID, block, itemBlock));
    }

    static void addFirTreeStuff() {
        String fir = "fir";
        FirLSCompound lsCompound = new FirLSCompound();
        register(lsCompound.lsLeaves);
        oreDictNames.put("treeLeaves", lsCompound.lsLeaves);
        register(lsCompound.lsSapling);
        oreDictNames.put("treeSapling", lsCompound.lsSapling);

        BlockTraverseWoodLog log = new BlockTraverseWoodLog(fir);
        register(log);
        oreDictNames.put("logWood", log);

        BlockTraverseWoodPlanks planks = new BlockTraverseWoodPlanks(fir);
        register(planks);
        oreDictNames.put("plankWood", planks);

        BlockTraverseStairs stairs = new BlockTraverseStairs(planks.getDefaultState(), fir);
        register(stairs);
        oreDictNames.put("stairWood", stairs);

        BlockTraverseWoodSlab.Half halfSlab = new BlockTraverseWoodSlab.Half(fir);
        register(halfSlab, true);
        oreDictNames.put("slabWood", halfSlab);
        BlockTraverseWoodSlab.Double doubleSlab = new BlockTraverseWoodSlab.Double(fir, halfSlab);
        register(doubleSlab, (ItemBlock) new ItemSlab(blocks.get(halfSlab.name + "_slab"), halfSlab, doubleSlab).setRegistryName(halfSlab.getRegistryName()));

        register(new BlockTraverseWoodFence(fir));
        register(new BlockTraverseWoodFenceGate(fir));
        BlockTraverseWoodDoor door = new BlockTraverseWoodDoor(fir);
        register(door, new ItemTraverseWoodDoor(door));

    }

    static void addStone(String name, boolean hasBricks, boolean hasSlab, boolean hasCobblestone) {
        String cobbleName = name + "_cobblestone";
        BlockTraverse stone;
        if (hasCobblestone)
            stone = new BlockTraverse(name, Material.ROCK, SoundType.STONE, new ResourceLocation("traverse", cobbleName));
        else
            stone = new BlockTraverse(name, Material.ROCK, SoundType.STONE);
        register(stone);
        oreDictNames.put("stone", stone);

        if (hasBricks) {
            BlockTraverse bricks = new BlockTraverse(name + "_bricks", Material.ROCK, SoundType.STONE);
            register(bricks);
            register(new BlockTraverseStairs(bricks.getDefaultState(), name + "_bricks"));

            BlockTraverseSlab.Half halfSlab = new BlockTraverseSlab.Half(name + "_bricks", Material.ROCK, SoundType.STONE);
            register(halfSlab, true);
            BlockTraverseSlab.Double doubleSlab = new BlockTraverseSlab.Double(name + "_bricks", Material.ROCK, SoundType.STONE, halfSlab);
            register(doubleSlab, (ItemBlock) new ItemSlab(blocks.get(halfSlab.name + "_slab"), halfSlab, doubleSlab).setRegistryName(halfSlab.getRegistryName()));
        }
        if (hasSlab) {
            if (hasBricks) {
                register(new BlockTraverse(name + "_bricks_chiseled", Material.ROCK, SoundType.STONE));
            }
            BlockTraverseSlab.Half halfSlab = new BlockTraverseSlab.Half(name, Material.ROCK, SoundType.STONE);
            register(halfSlab, true);
            BlockTraverseSlab.Double doubleSlab = new BlockTraverseSlab.Double(name, Material.ROCK, SoundType.STONE, halfSlab);
            register(doubleSlab, (ItemBlock) new ItemSlab(blocks.get(halfSlab.name + "_slab"), halfSlab, doubleSlab).setRegistryName(halfSlab.getRegistryName()));
        }

        if (hasCobblestone) {
            BlockTraverse cobblestone = new BlockTraverse(cobbleName, Material.ROCK, SoundType.STONE);
            register(cobblestone);
            oreDictNames.put("cobblestone", cobblestone);

            register(new BlockTraverseStairs(cobblestone.getDefaultState(), cobbleName));

            BlockTraverseSlab.Half halfSlab = new BlockTraverseSlab.Half(cobbleName, Material.ROCK, SoundType.STONE);
            register(halfSlab, true);
            BlockTraverseSlab.Double doubleSlab = new BlockTraverseSlab.Double(cobbleName, Material.ROCK, SoundType.STONE, halfSlab);
            register(doubleSlab, (ItemBlock) new ItemSlab(blocks.get(halfSlab.name + "_slab"), halfSlab, doubleSlab).setRegistryName(halfSlab.getRegistryName()));

            register(new BlockTraverseWall(cobblestone, cobbleName));

        } else {
            register(new BlockTraverse(name, Material.ROCK, SoundType.STONE));

        }
    }
}
