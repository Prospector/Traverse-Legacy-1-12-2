package prospector.traverse.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import prospector.traverse.config.TraverseConfig;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandFindBiome extends CommandBase {

	//Based off https://github.com/Glitchfiend/BiomesOPlenty/blob/4977b0100ca55f96de50337f46ed673512cf503a/src/main/java/biomesoplenty/common/util/biome/BiomeUtils.java
	public static BlockPos spiralOutwardsLookingForBiome(ICommandSender sender, World world, Biome biomeToFind, double startX, double startZ, int timeout) {
		double a = 16 / Math.sqrt(Math.PI);
		double b = 2 * Math.sqrt(Math.PI);
		double x;
		double z;
		double dist = 0;
		int n;
		long start = System.currentTimeMillis();
		for (n = 0; dist < Integer.MAX_VALUE; ++n) {
			if ((System.currentTimeMillis() - start) > timeout) {
				return null;
			}
			double rootN = Math.sqrt(n);
			dist = a * rootN;
			x = startX + (dist * Math.sin(b * rootN));
			z = startZ + (dist * Math.cos(b * rootN));
			if (world.getBiome(new BlockPos(x, 0, z)).equals(biomeToFind)) {
				return new BlockPos((int) x, 0, (int) z);
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return "findbiome";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/findbiome <biome>";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server,
	                                      ICommandSender sender,
	                                      String[] args,
	                                      @Nullable
		                                      BlockPos targetPos) {
		List<String> strings = new ArrayList<>();
		for (Biome b : ForgeRegistries.BIOMES.getValues()) {
			String s = b.getRegistryName().toString();
			if (s.toLowerCase().contains(args[0].toLowerCase()))
				strings.add(s);
		}
		return strings;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		Biome biome = null;
		if (args.length == 0) {
			sender.sendMessage(new TextComponentString("No biome specified"));
			return;
		}
		for (Biome b : ForgeRegistries.BIOMES.getValues()) {
			String name = b.getRegistryName().toString().replaceAll(" ", "_").toLowerCase();
			if (args[0].equalsIgnoreCase(name)) {
				biome = b;
			}
		}
		if (biome == null) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' cannot be found!"));
			return;
		}
		long start = System.currentTimeMillis();
		BlockPos pos = spiralOutwardsLookingForBiome(sender, sender.getEntityWorld(), biome, sender.getPosition().getX(), sender.getPosition().getZ(), TraverseConfig.findBiomeCommandTimeout);
		if (pos == null) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' could not be found after " + TextFormatting.GRAY + TraverseConfig.findBiomeCommandTimeout + "ms" + TextFormatting.RED + "."));
			return;
		}
		if (sender instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) sender;
			playerMP.connection.setPlayerLocation(pos.getX(), 150, pos.getZ(), 0, 0);
		}

		sender.sendMessage(new TextComponentString(TextFormatting.WHITE + "Found '" + biome.getRegistryName().toString() + "' Biome! " + TextFormatting.GRAY + "(" + (System.currentTimeMillis() - start) + "ms)"));
	}
}
