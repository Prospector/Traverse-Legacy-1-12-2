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

public class CommandFindTest extends CommandBase {

    //Based off https://github.com/Glitchfiend/BiomesOPlenty/blob/4977b0100ca55f96de50337f46ed673512cf503a/src/main/java/biomesoplenty/common/util/biome/BiomeUtils.java
    public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ, int timeout) {
        double a = 16 / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x;
        double z;
        double dist = 0;
        int n;
        long start = System.currentTimeMillis();
        for (n = 0; dist < Integer.MAX_VALUE; ++n) {
            if ((System.currentTimeMillis() - start) > 10000) {
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
        return "findtest";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "findtest";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Biome biome = null;
        for (Biome b : ForgeRegistries.BIOMES) {
            String name = b.getBiomeName().replaceAll(" ", "_").toLowerCase();
            if (args[0].equalsIgnoreCase(name)) {
                biome = b;
            }
        }
        if (biome == null) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' cannot be found!"));
            return;
        }
        long start = System.currentTimeMillis();
        int timeout = 10000;
        BlockPos pos = spiralOutwardsLookingForBiome(sender.getEntityWorld(), biome, sender.getPosition().getX(), sender.getPosition().getZ(), timeout);
        if (pos == null) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' could not be found after " + TextFormatting.GRAY + timeout + "ms" + TextFormatting.RED + "."));
            return;
        }
        System.out.println(pos);
        System.out.println();
        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) sender;
            playerMP.connection.setPlayerLocation(pos.getX(), 150, pos.getZ(), 0, 0);
        }
        sender.sendMessage(new TextComponentString(TextFormatting.WHITE + "Found '" + biome.getBiomeName() + "' Biome! " + TextFormatting.GRAY + "(" + (System.currentTimeMillis() - start) + "ms)"));
    }
}
