package prospector.traverse.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import prospector.shootingstar.version.Version;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.core.TraverseConstants;
import prospector.traverse.world.biomes.*;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@Mod.EventBusSubscriber
public class TraverseWorld {

    public static List<TraverseBiomeEntry> biomeList = new ArrayList<>();
    public static Biome autumnalWoodsBiome = new BiomeAutumnalWoods();
    public static Biome woodlandsBiome = new BiomeWoodlands();
    public static Biome miniJungleBiome = new BiomeMiniJungle();
    public static Biome meadowBiome = new BiomeMeadow();
    public static Biome lushSwampBiome = new BiomeGreenSwamp();
    public static Biome redDesertBiome = new BiomeRedDesert();
    public static Biome temperateRainforestBiome = new BiomeTemperateRainforest(false);
    public static Biome badlandsBiome = new BiomeBadlands();
    public static Biome mountainousDesertBiome = new BiomeMountainousDesert();
    public static Biome rockyPlateauBiome = new BiomeRockyPlateau();
    public static Biome forestedHillsBiome = new BiomeForestedHills(BiomeForest.Type.NORMAL, "Forested Hills");
    public static Biome birchForestedHillsBiome = new BiomeForestedHills(BiomeForest.Type.BIRCH, "Birch Forested Hills");
    public static Biome autumnalWoodedHillsBiome = new BiomeAutumnalWoodedHills();
    public static Biome cliffsBiome = new BiomeCliffs();
    public static Biome glacierBiome = new BiomeGlacier(false);
    public static Biome glacierSpikesBiome = new BiomeGlacier(true);
    public static Biome snowyConiferousForestBiome = new BiomeTemperateRainforest(true);
    public static Biome lushHillsBiome = new BiomeLushHills();
    public static Biome canyonBiome = new BiomeCanyon();
    public static Biome cragCliffsBiome = new BiomeCragCliffs();
    public static Biome desertShrublandBiome = new BiomeDesertShrubland();

    public static final Version V0 = new Version(1, 0, 0);
    public static final Version V1 = new Version(1, 1, 0);
    public static final Version V2 = new Version(1, 2, 0);
    public static final Version V3 = new Version(1, 3, 0);
    public static final Version V4 = new Version(1, 4, 0);

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        register(V0, autumnalWoodsBiome, BiomeType.COOL, "autumnal_woods", 8, TraverseConfig.disableAutumnalWoods, event, FOREST);
        register(V0, woodlandsBiome, BiomeType.WARM, "woodlands", 9, TraverseConfig.disableWoodlands, event, PLAINS);
        register(V0, miniJungleBiome, BiomeType.WARM, "mini_jungle", 3, TraverseConfig.disableMiniJungle, event, DENSE, JUNGLE, HOT, WET);
        register(V0, meadowBiome, BiomeType.COOL, "meadow", 7, TraverseConfig.disableMeadow, event, PLAINS, LUSH, WET);
        register(V0, lushSwampBiome, BiomeType.WARM, "green_swamp", 6, TraverseConfig.disableLushSwamp, event, LUSH, WET, SWAMP);
        register(V0, redDesertBiome, BiomeType.DESERT, "red_desert", 6, TraverseConfig.disableRedDesert, event, HOT, DRY, SANDY);
        register(V0, temperateRainforestBiome, BiomeType.COOL, "temperate_rainforest", 8, TraverseConfig.disableTemperateRainforest, event, FOREST, CONIFEROUS);
        register(V1, badlandsBiome, BiomeType.WARM, "badlands", 5, TraverseConfig.disableBadlands, event, PLAINS, DRY, HOT, SPARSE);
        register(V1, mountainousDesertBiome, BiomeType.DESERT, "mountainous_desert", 2, TraverseConfig.disableMountainousDesert, event, MOUNTAIN, DRY, HOT, SANDY);
        register(V1, rockyPlateauBiome, BiomeType.WARM, "rocky_plateau", 4, TraverseConfig.disableRockyPlateau, event, HOT, MOUNTAIN);
        register(V1, forestedHillsBiome, BiomeType.COOL, "forested_hills", 6, TraverseConfig.disableForestedHills, event, FOREST, HILLS);
        register(V1, birchForestedHillsBiome, BiomeType.COOL, "birch_forested_hills", 2, TraverseConfig.disableBirchForestedHills, event, FOREST, HILLS);
        register(V1, autumnalWoodedHillsBiome, BiomeType.COOL, "autumnal_wooded_hills", 1, TraverseConfig.disableAutumnalWoodedHills, event, FOREST, HILLS);
        register(V2, cliffsBiome, BiomeType.COOL, "cliffs", 2, TraverseConfig.disableCliffs, event, MOUNTAIN, COLD, HILLS);
        register(V2, glacierBiome, BiomeType.ICY, "glacier", 6, TraverseConfig.disableGlacier, event, MOUNTAIN, COLD, SNOWY);
        register(V2, glacierSpikesBiome, BiomeType.ICY, "glacier_spikes", 2, TraverseConfig.disableGlacierSpikes, event, MOUNTAIN, COLD, SNOWY);
        register(V2, snowyConiferousForestBiome, BiomeType.ICY, "snowy_coniferous_forest", 5, TraverseConfig.disableSnowyConiferousForest, event, COLD, SNOWY, FOREST, CONIFEROUS);
        register(V3, lushHillsBiome, BiomeType.COOL, "lush_hills", 6, TraverseConfig.disableLushHills, event, LUSH, HILLS, SPARSE, WET);
        register(V3, canyonBiome, BiomeType.DESERT, "canyon", 5, TraverseConfig.disableCanyon, event, DRY, DEAD);
        register(V3, cragCliffsBiome, BiomeType.COOL, "crag_cliffs", 4, TraverseConfig.disableCragCliffs, event, COLD, DEAD);
        register(V4, desertShrublandBiome, BiomeType.DESERT, "desert_shrubland", 5, TraverseConfig.disableDesertShrubland, event, HOT, DRY, SANDY, DEAD);
    }

    public static void register(Version versionAdded, Biome biome, BiomeType type, String name, int weight, boolean disabled, RegistryEvent.Register<Biome> event, BiomeDictionary.Type... biomeDictTypes) {
        if (!disabled) {
            biome.setRegistryName(new ResourceLocation(TraverseConstants.MOD_ID, name));
            event.getRegistry().register(biome);
            for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
                BiomeDictionary.addTypes(biome, biomeDictType);
            }
            biomeList.add(new TraverseBiomeEntry(biome, type, weight, versionAdded));
        }
    }

    public static class TraverseBiomeEntry {
        private Biome biome;
        private BiomeType type;
        private Version versionAdded;
        private int weight;
        private BiomeManager.BiomeEntry entry;

        public TraverseBiomeEntry(Biome biome, BiomeType type, int weight, Version versionAdded) {
            this.biome = biome;
            this.type = type;
            this.weight = weight;
            this.versionAdded = versionAdded;
            this.entry = new BiomeManager.BiomeEntry(biome, weight);
        }

        public Biome getBiome() {
            return biome;
        }

        public BiomeManager.BiomeEntry getEntry() {
            return entry;
        }

        public BiomeType getType() {
            return type;
        }

        public Version getVersionAdded() {
            return versionAdded;
        }

        public int getWeight() {
            return weight;
        }
    }
}
