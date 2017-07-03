package prospector.traverse;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.version.VersionUtils;
import prospector.traverse.commands.CommandFindTest;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.init.TraverseBlocks;
import prospector.traverse.world.TraverseWorld;
import prospector.traverse.world.TraverseWorldVersion;

import java.io.File;

@Mod.EventBusSubscriber
public class TraverseCommon {

    public static TraverseWorldVersion traverse_world_data = null;

    static void registerFurnace(ItemStack output, ItemStack input, float experience) {
        GameRegistry.addSmelting(input, output, experience);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ShootingStar.registerModels(TraverseConstants.MOD_ID);
    }

    public void preInit(FMLPreInitializationEvent event) {
        TraverseConfig.initialize(event);
    }

    public void init(FMLInitializationEvent event) {
        for (String name : TraverseBlocks.oreDictNames.keySet()) {
            OreDictionary.registerOre(name, TraverseBlocks.oreDictNames.get(name));
        }
        registerFurnace(new ItemStack(Items.COAL, 1, 1), new ItemStack(Item.getItemFromBlock(TraverseBlocks.blocks.get("fir_log"))), 0.15F);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFindTest());
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        traverse_world_data = null;
        File rootDir = FMLCommonHandler.instance().getSavesDirectory();
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }
        File worldDir = new File(rootDir, event.getServer().getFolderName());
        if (!worldDir.exists()) {
            worldDir.mkdir();
        }
        traverse_world_data = new TraverseWorldVersion(worldDir);
        for (TraverseWorld.TraverseBiomeEntry traverseBiome : TraverseWorld.biomeList) {
            BiomeProvider.allowedBiomes.remove(traverseBiome.getBiome());
            BiomeManager.removeSpawnBiome(traverseBiome.getBiome());
            BiomeManager.removeBiome(traverseBiome.getType(), traverseBiome.getEntry());

            boolean canRegister;
            if (!TraverseConfig.registerBiomesRegardless) {
                traverse_world_data.reloadVersionFile();
                canRegister = VersionUtils.isVersionLessOrEqual(traverseBiome.getVersionAdded(), traverse_world_data.version);
            } else {
                canRegister = true;
            }
            if (canRegister) {
                BiomeManager.addBiome(traverseBiome.getType(), traverseBiome.getEntry());
                BiomeManager.addSpawnBiome(traverseBiome.getBiome());
                BiomeProvider.allowedBiomes.add(traverseBiome.getBiome());
            }
        }
    }

    public void serverStopping(FMLServerStoppingEvent event) {

    }
}
