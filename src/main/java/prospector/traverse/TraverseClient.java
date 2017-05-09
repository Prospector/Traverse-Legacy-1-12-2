package prospector.traverse;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.core.TraverseMod;

public class TraverseClient extends TraverseCommon {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        registerRenders();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    void registerItemModel(Item i, int meta) {
        ResourceLocation loc = i.getRegistryName();
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "inventory"));
    }

    void registerItemModel(Item i, int meta, String variant) {
        registerItemModel(i, meta, variant, "type");
    }

    void registerItemModel(Item i, int meta, String variant, String property) {
        ResourceLocation loc = i.getRegistryName();
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, property + "=" + variant));
    }

    void register(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(TraverseConstants.MOD_ID + name, "inventory"));
    }

    void registerItemModel(Block b, int meta) {
        registerItemModel(Item.getItemFromBlock(b), meta);
    }

    void registerItemModel(Block b, int meta, String variant) {
        registerItemModel(b, meta, variant);
    }

    void registerItemModel(Block b, int meta, String variant, String property) {
        registerItemModel(Item.getItemFromBlock(b), meta, variant, property);
    }

    void register(Item item, String name) {
        register(item, 0, name);
    }

    void register(Block block, int meta, String name) {
        register(Item.getItemFromBlock(block), meta, name);
    }

    void register(Block block, String name) {
        register(Item.getItemFromBlock(block), 0, name);
    }

    void registerBlockstate(Item i, int meta, String variant) {
        registerBlockstate(i, meta, variant, "");
    }

    void registerBlockstate(Item i, int meta, String variant, String dir) {
        ResourceLocation loc = new ResourceLocation(TraverseConstants.MOD_ID, dir + i.getRegistryName().getResourcePath());
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
    }

    void registerBlockstate(Block i, int meta, String variant) {
        registerBlockstate(i, meta, variant, "");
    }

    void registerBlockstate(Block i, int meta, String variant, String dir) {
        registerBlockstate(Item.getItemFromBlock(i), meta, variant, dir);
    }

    void registerBlockstateMultiItem(Item item, String variantName, String path) {
        ResourceLocation loc = new ResourceLocation(TraverseConstants.MOD_ID, path);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(loc, "type=" + variantName));
    }

    public void registerRenders() {
        for (Item item : TraverseMod.itemModelsToRegister) {
            registerItemModel(item, 0);
        }
        for (Block block : TraverseMod.blockModelsToRegister) {
            registerItemModel(block, 0);
        }
    }

    void registerItemModel(String modid, Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(modid + ":" + id, "inventory"));
    }

    void registerCustomBlockStateLocation(Block block, final String resourceLocation, boolean item) {
        ModelLoader.setCustomStateMapper(block, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                String resourceDomain = Block.REGISTRY.getNameForObject(state.getBlock()).getResourceDomain();
                String propertyString = getPropertyString(state.getProperties());
                return new ModelResourceLocation(resourceDomain + ':' + resourceLocation, propertyString);
            }
        });
        if (item) {
            String resourceDomain = Block.REGISTRY.getNameForObject(block).getResourceDomain();
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(resourceDomain + ':' + resourceLocation, "inventory"));
        }
    }

    void registerCustomBlockStateLocation(Item item, String resourceLocation) {
        String resourceDomain = Item.REGISTRY.getNameForObject(item).getResourceDomain();
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(resourceDomain + ':' + resourceLocation, "inventory"));

    }
}
