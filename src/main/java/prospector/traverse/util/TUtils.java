package prospector.traverse.util;

import net.minecraft.block.Block;
import prospector.shootingstar.ShootingStar;
import prospector.traverse.core.TraverseConstants;

public class TUtils {
    public static Block getBlock(String name) {
        return ShootingStar.getBlock(TraverseConstants.MOD_ID, name);
    }
}
