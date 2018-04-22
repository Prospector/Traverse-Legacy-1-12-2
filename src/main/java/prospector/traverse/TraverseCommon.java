package prospector.traverse;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.version.VersionUtils;
import prospector.traverse.commands.CommandFindBiome;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.init.TraverseBlocks;
import prospector.traverse.world.TraverseWorld;
import prospector.traverse.world.TraverseWorldVersion;

import java.io.File;

import static prospector.traverse.util.TUtils.getBlock;

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

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		ShootingStar.registerBlocks(TraverseConstants.MOD_ID, event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ShootingStar.registerItems(TraverseConstants.MOD_ID, event);
	}

	public void preInit(FMLPreInitializationEvent event) {
		TraverseBlocks.test();
		TraverseConfig.initialize(event);
		MinecraftForge.TERRAIN_GEN_BUS.register(TraverseWorld.class);
	}

	public void init(FMLInitializationEvent event) {
		for (Block block : TraverseBlocks.oreDictNames.keySet()) {
			OreDictionary.registerOre(TraverseBlocks.oreDictNames.get(block), block);
		}
		registerFurnace(new ItemStack(Items.COAL, 1, 1), new ItemStack(getBlock("fir_log")), 0.15F);
		registerFurnace(new ItemStack(getBlock("red_rock")), new ItemStack(getBlock("red_rock_cobblestone")), 0.1F);
		registerFurnace(new ItemStack(getBlock("blue_rock")), new ItemStack(getBlock("blue_rock_cobblestone")), 0.1F);
	}

	public void postInit(FMLPostInitializationEvent event) {

	}

	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandFindBiome());
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
				if (traverseBiome.hasVillages()) {
					BiomeManager.addVillageBiome(traverseBiome.getBiome(), traverseBiome.canSpawn());
				}
				if (traverseBiome.canSpawn()) {
					BiomeManager.addSpawnBiome(traverseBiome.getBiome());
				}
				BiomeProvider.allowedBiomes.add(traverseBiome.getBiome());
			}
		}
	}

	public void serverStopping(FMLServerStoppingEvent event) {

	}
}
