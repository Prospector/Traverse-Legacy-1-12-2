package prospector.traverse.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import prospector.traverse.TraverseCommon;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = TraverseConstants.MOD_ID, name = TraverseConstants.MOD_NAME, version = TraverseConstants.MOD_VERSION, acceptedMinecraftVersions = TraverseConstants.MINECRAFT_VERSIONS)
public class TraverseMod {

    @Mod.Instance(TraverseConstants.MOD_ID)
    public static TraverseMod instance;
    @SidedProxy(clientSide = TraverseConstants.CLIENT_PROXY_CLASS, serverSide = TraverseConstants.SERVER_PROXY_CLASS)
    public static TraverseCommon proxy;
    public static List<Item> itemModelsToRegister = new ArrayList<>();
    public static List<Block> blockModelsToRegister = new ArrayList<>();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    @Mod.EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        proxy.serverAboutToStart(event);
    }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        proxy.serverStopping(event);
    }

}
