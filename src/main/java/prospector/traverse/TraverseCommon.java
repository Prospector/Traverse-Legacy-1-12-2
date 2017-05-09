package prospector.traverse;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prospector.traverse.init.TraverseBlocks;
import prospector.traverse.world.TraverseWorld;

public class TraverseCommon {

    public void preInit(FMLPreInitializationEvent event) {
        TraverseBlocks.initialize();
    }

    public void init(FMLInitializationEvent event) {
        TraverseWorld.init();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
