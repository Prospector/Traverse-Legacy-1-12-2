package prospector.traverse;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prospector.shootingstar.ShootingStar;
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

//            if (block instanceof BlockTraverseWoodDoor)
//                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockDoor.POWERED).build());
//            if (block instanceof BlockTraverseWoodFenceGate)
//                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFenceGate.POWERED, BlockFenceGate.IN_WALL).build());
//            if (block instanceof BlockTraverseLeaves)
//                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
//            if (block instanceof BlockTraverseSapling)
//                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockSapling.STAGE, BlockSapling.TYPE).build());
}
