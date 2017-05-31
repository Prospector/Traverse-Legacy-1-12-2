package prospector.traverse.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import prospector.traverse.init.TraverseBlocks;

public class TraverseTab {
    public static final CreativeTabs TAB = new CreativeTabs(TraverseConstants.MOD_ID) {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(TraverseBlocks.blocks.get("fir_sapling"));
        }
    };
}
