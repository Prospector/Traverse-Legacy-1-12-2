package prospector.traverse;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import prospector.shootingstar.version.VersionUtils;
import prospector.traverse.commands.CommandFindTest;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.init.TraverseBlocks;
import prospector.traverse.world.TraverseWorld;
import prospector.traverse.world.TraverseWorldVersion;

import java.io.File;

public class TraverseCommon {

    public static TraverseWorldVersion traverse_world_data = null;

    static void registerShaped(String name, ItemStack output, Object... inputs) {
//        GameRegistry.addRecipe(new ResourceLocation(TraverseConstants.MOD_ID, name), new ShapedOreRecipe(new ResourceLocation(TraverseConstants.MOD_ID, name), output, inputs));
    }

    static void registerShapeless(String name, ItemStack output, Object... inputs) {
//        GameRegistry.addRecipe(new ResourceLocation(TraverseConstants.MOD_ID, name), new ShapelessOreRecipe(new ResourceLocation(TraverseConstants.MOD_ID, name), output, inputs));
    }

    static void registerFurnace(ItemStack output, ItemStack input, float experience) {
        GameRegistry.addSmelting(input, output, experience);
    }

    public void preInit(FMLPreInitializationEvent event) {
        TraverseConfig.initialize(event);
        TraverseBlocks.initialize();
    }

    public void init(FMLInitializationEvent event) {
        TraverseWorld.init();
        for (String name : TraverseBlocks.oreDictNames.keySet()) {
            OreDictionary.registerOre(name, TraverseBlocks.oreDictNames.get(name));
        }
        registerShapeless("fir_planks1", new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks")), 4), new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_log"))));
        registerShaped("fir_slab1", new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_slab")), 6), "ppp", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped("fir_stairs1", new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_stairs")), 4), "p  ", "pp ", "ppp", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped("fir_door1", new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_door")), 3), "pp", "pp", "pp", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped("fir_fence1", new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_fence")), 3), "psp", "psp", 's', "stickWood", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerShaped("fir_fence_gate1", new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_fence_gate"))), "sps", "sps", 's', "stickWood", 'p', new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_planks"))));
        registerFurnace(new ItemStack(Items.COAL, 1, 1), new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_log"))), 0.15F);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFindTest());
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        traverse_world_data = null;
        for (TraverseWorld.BiomeEntry entry : TraverseWorld.biomeList) {
            BiomeProvider.allowedBiomes.remove(entry.getBiome());
            BiomeManager.removeSpawnBiome(entry.getBiome());
            BiomeManager.removeBiome(entry.getType(), new BiomeManager.BiomeEntry(entry.getBiome(), entry.getWeight()));
        }
        File serverDir = event.getServer().getDataDirectory();
        File savesDir = new File(serverDir, "saves");
        File worldDir = new File(savesDir, event.getServer().getFolderName());
        traverse_world_data = new TraverseWorldVersion(worldDir);
        for (TraverseWorld.BiomeEntry entry : TraverseWorld.biomeList) {
            boolean canRegister;
            if (!TraverseConfig.registerBiomesRegardless) {
                traverse_world_data.reloadVersionFile();
                canRegister = VersionUtils.isVersionLessOrEqual(entry.getVersionAdded(), traverse_world_data.version);
            } else {
                canRegister = true;
            }
            if (canRegister) {
                BiomeManager.addBiome(entry.getType(), new BiomeManager.BiomeEntry(entry.getBiome(), entry.getWeight()));
                BiomeManager.addSpawnBiome(entry.getBiome());
                BiomeProvider.allowedBiomes.add(entry.getBiome());
            }
        }
    }

    public void serverStopping(FMLServerStoppingEvent event) {
       
    }
}
