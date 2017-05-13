package prospector.traverse.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import prospector.traverse.world.TraverseWorld;

public class CommandFindTest extends CommandBase {
    public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ) {
        int sampleSpacing = 16;
        int maxDist = 10000;
        return spiralOutwardsLookingForBiome(world, biomeToFind, startX, startZ, maxDist, sampleSpacing);
    }

    //Based off https://github.com/Glitchfiend/BiomesOPlenty/blob/4977b0100ca55f96de50337f46ed673512cf503a/src/main/java/biomesoplenty/common/util/biome/BiomeUtils.java
    public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ, int maxDist, int sampleSpace) {
        double a = sampleSpace / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x;
        double z;
        double dist = 0;
        int n;
        for (n = 0; dist < maxDist; ++n) {
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
        Biome biome;
        switch (args[0]) {
            case "woodlands":
                biome = TraverseWorld.woodlandsBiome;
                break;
            case "miniJungle":
                biome = TraverseWorld.miniJungleBiome;
                break;
            case "meadow":
                biome = TraverseWorld.meadowBiome;
                break;
            default:
                biome = TraverseWorld.autumnalWoodsBiome;
                break;
        }
        long start = System.currentTimeMillis();
        BlockPos pos = spiralOutwardsLookingForBiome(sender.getEntityWorld(), biome, sender.getPosition().getX(), sender.getPosition().getZ());
        System.out.println(pos);
        System.out.println((System.currentTimeMillis() - start) + "ms");
        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) sender;
            playerMP.connection.setPlayerLocation(pos.getX(), 150, pos.getZ(), 0, 0);
        }
    }
}
