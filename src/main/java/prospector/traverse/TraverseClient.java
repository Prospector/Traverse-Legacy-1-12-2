package prospector.traverse;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.core.TraverseConstants;

public class TraverseClient extends TraverseCommon {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ShootingStar.registerModels(TraverseConstants.MOD_ID);
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
