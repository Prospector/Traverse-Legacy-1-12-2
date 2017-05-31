package prospector.traverse;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import prospector.traverse.init.TraverseBlocks;
import prospector.traverse.world.TraverseWorld;

public class TraverseCommon {

    static void registerShaped(ItemStack output, Object... inputs) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, inputs));
    }

    static void registerShapeless(ItemStack output, Object... inputs) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, inputs));
    }

    static void registerFurnace(ItemStack output, ItemStack input, float experience) {
        GameRegistry.addSmelting(input, output, experience);
    }

    public void preInit(FMLPreInitializationEvent event) {
        TraverseBlocks.initialize();
    }

    public void init(FMLInitializationEvent event) {
        TraverseWorld.init();
        for (String name : TraverseBlocks.oreDictNames.keySet()) {
            OreDictionary.registerOre(name, TraverseBlocks.oreDictNames.get(name));
        }
        registerShapeless(new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks")), 4), new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_log"))));
        registerShaped(new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_slab")), 6), "ppp", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped(new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_stairs")), 4), "p  ", "pp ", "ppp", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped(new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_door")), 3), "pp", "pp", "pp", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped(new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_fence")), 3), "psp", "psp", 's', "stickWood", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped(new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_fence_gate"))), "sps", "sps", 's', "stickWood", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerFurnace(new ItemStack(Items.COAL, 1, 1), new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_log"))), 0.15F);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
