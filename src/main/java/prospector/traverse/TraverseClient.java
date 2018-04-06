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

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@SubscribeEvent
	public void tooltip(RenderTooltipEvent.Pre event) {
		if (Minecraft.getMinecraft().player.getUniqueID().toString().equals("221141c3-340d-4c3b-8b36-6351b6ea6182") || Minecraft.getMinecraft().player.getUniqueID().toString().equals("9927b75a-89d9-4ff7-8bac-58aaed08911a")) {
			boolean render = false;
			ItemStack stack = event.getStack();
			if (stack.getItem() instanceof ItemBlock) {
				if (((ItemBlock) stack.getItem()).getBlock() instanceof BlockSapling)
					render = true;
				if (((ItemBlock) stack.getItem()).getBlock() instanceof BlockLeaves)
					render = true;
				if (((ItemBlock) stack.getItem()).getBlock() instanceof BlockFlower)
					render = true;
				if (((ItemBlock) stack.getItem()).getBlock() instanceof BlockDoublePlant)
					render = true;
				if (((ItemBlock) stack.getItem()).getBlock() == Blocks.WATERLILY)
					render = true;
				if (((ItemBlock) stack.getItem()).getBlock() == Blocks.VINE)
					render = true;
				if (((ItemBlock) stack.getItem()).getBlock() == Blocks.TALLGRASS)
					render = true;
				if (((ItemBlock) stack.getItem()).getBlock() == Blocks.DEADBUSH)
					render = true;
			} else {

			}
			if (!render)
				return;
			;
			event.setCanceled(true);
			List<String> textLines = new ArrayList<>();
			textLines.addAll(event.getLines());
			int mouseX = event.getX();
			int mouseY = event.getY();
			int screenWidth = event.getScreenWidth();
			int screenHeight = event.getScreenHeight();
			int maxTextWidth = event.getMaxWidth();
			FontRenderer font = event.getFontRenderer();

			GlStateManager.disableRescaleNormal();
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.disableDepth();
			int tooltipTextWidth = 0;

			for (String textLine : textLines) {
				int textLineWidth = font.getStringWidth(textLine);

				if (textLineWidth > tooltipTextWidth) {
					tooltipTextWidth = textLineWidth;
				}
			}

			boolean needsWrap = false;

			int titleLinesCount = 1;
			int tooltipX = mouseX + 12;
			if (tooltipX + tooltipTextWidth + 4 > screenWidth) {
				tooltipX = mouseX - 16 - tooltipTextWidth;
				if (tooltipX < 4) // if the tooltip doesn't fit on the screen
				{
					if (mouseX > screenWidth / 2) {
						tooltipTextWidth = mouseX - 12 - 8;
					} else {
						tooltipTextWidth = screenWidth - 16 - mouseX;
					}
					needsWrap = true;
				}
			}

			if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth) {
				tooltipTextWidth = maxTextWidth;
				needsWrap = true;
			}

			if (needsWrap) {
				int wrappedTooltipWidth = 0;
				List<String> wrappedTextLines = new ArrayList<>();
				for (int i = 0; i < textLines.size(); i++) {
					String textLine = textLines.get(i);
					List<String> wrappedLine = font.listFormattedStringToWidth(textLine, tooltipTextWidth);
					if (i == 0) {
						titleLinesCount = wrappedLine.size();
					}

					for (String line : wrappedLine) {
						int lineWidth = font.getStringWidth(line);
						if (lineWidth > wrappedTooltipWidth) {
							wrappedTooltipWidth = lineWidth;
						}
						wrappedTextLines.add(line);
					}
				}
				tooltipTextWidth = wrappedTooltipWidth;
				textLines = wrappedTextLines;

				if (mouseX > screenWidth / 2) {
					tooltipX = mouseX - 16 - tooltipTextWidth;
				} else {
					tooltipX = mouseX + 12;
				}
			}

			int tooltipY = mouseY - 12;
			int tooltipHeight = 8;

			if (textLines.size() > 1) {
				tooltipHeight += (textLines.size() - 1) * 10;
				if (textLines.size() > titleLinesCount) {
					tooltipHeight += 2; // gap between title lines and next lines
				}
			}

			if (tooltipY + tooltipHeight + 6 > screenHeight) {
				tooltipY = screenHeight - tooltipHeight - 6;
			}

			final int zLevel = 300;
			int backgroundX = tooltipX - 3;
			int backgroundY = tooltipY - 3;
			int backgroundWidth = tooltipTextWidth + 5;
			int backgroundHeight = tooltipHeight + 6;
			final int backgroundColor = 0xF0100010;
			GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
			GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
			GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			GuiUtils.drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
			GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);

			final int borderColorStart = new Color(0x24B528).getRGB();
			final int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;
			GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
			GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
			GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
			GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

			int tooltipTop = tooltipY;

			for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber) {
				String line = textLines.get(lineNumber);
				font.drawStringWithShadow(line, tooltipX, tooltipY, -1);

				if (lineNumber + 1 == titleLinesCount) {
					tooltipY += 2;
				}

				tooltipY += 10;
			}

			MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.PostText(event.getStack(), textLines, tooltipX, tooltipTop, font, tooltipTextWidth, tooltipHeight));

			GlStateManager.enableLighting();
			GlStateManager.enableDepth();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableRescaleNormal();
		}
	}
}
