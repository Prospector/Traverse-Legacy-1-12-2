package prospector.traverse.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TraverseTab {
    public static final CreativeTabs TAB = new CreativeTabs(TraverseConstants.MOD_ID) {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.LEAVES);
        }
    };
}
