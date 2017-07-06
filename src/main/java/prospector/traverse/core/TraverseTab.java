package prospector.traverse.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import prospector.traverse.TraverseCommon;
import prospector.traverse.init.TraverseBlocks;

import static prospector.traverse.util.TUtils.getBlock;

public class TraverseTab {
    public static final CreativeTabs TAB = new CreativeTabs(TraverseConstants.MOD_ID) {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(getBlock("fir_sapling"));
        }
    };
}
