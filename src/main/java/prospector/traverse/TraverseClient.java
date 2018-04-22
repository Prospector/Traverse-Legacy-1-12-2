package prospector.traverse;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import prospector.traverse.client.Traverse2Textures;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TraverseClient extends TraverseCommon {

	static Traverse2Textures traverse2textures;

	static {
		List<IResourcePack> packs = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), new String[] { "aD", "field_110449_ao", "defaultResourcePacks" });
		traverse2textures = new Traverse2Textures();
		packs.add(traverse2textures);

		traverse2textures.addTextureOverride("block/planks", "fir_planks");

		traverse2textures.addTextureOverride("block/red_rock", "brick");
		traverse2textures.addTextureOverride("block/red_rock", "brick1");
		traverse2textures.addTextureOverride("block/red_rock", "brick2");
		traverse2textures.addTextureOverride("block/red_rock", "chiseled");
		traverse2textures.addTextureOverride("block/red_rock", "cobblestone");
		//		traverse2textures.addTextureOverride("block/red_rock", "cracked");
		traverse2textures.addTextureOverride("block/red_rock", "slab");
		traverse2textures.addTextureOverride("block/red_rock", "slab_side");
		traverse2textures.addTextureOverride("block/red_rock", "smooth");

		traverse2textures.addTextureOverride("block/blue_rock", "brick");
		traverse2textures.addTextureOverride("block/blue_rock", "brick1");
		traverse2textures.addTextureOverride("block/blue_rock", "brick2");
		traverse2textures.addTextureOverride("block/blue_rock", "chiseled");
		traverse2textures.addTextureOverride("block/blue_rock", "cobblestone");
		//		traverse2textures.addTextureOverride("block/blue_rock", "cracked");
		traverse2textures.addTextureOverride("block/blue_rock", "slab");
		traverse2textures.addTextureOverride("block/blue_rock", "slab_side");
		traverse2textures.addTextureOverride("block/blue_rock", "smooth");

		traverse2textures.addTextureOverride("block/leaves", "red_autumnal_leaves");
		traverse2textures.addTextureOverride("block/leaves", "yellow_autumnal_leaves");
		traverse2textures.addTextureOverride("block/leaves", "orange_autumnal_leaves");
		traverse2textures.addTextureOverride("block/leaves", "brown_autumnal_leaves");

		traverse2textures.addTextureOverride("block/sapling", "red_autumnal_sapling");
		traverse2textures.addTextureOverride("block/sapling", "yellow_autumnal_sapling");
		traverse2textures.addTextureOverride("block/sapling", "orange_autumnal_sapling");
		traverse2textures.addTextureOverride("block/sapling", "brown_autumnal_sapling");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	
			
}
