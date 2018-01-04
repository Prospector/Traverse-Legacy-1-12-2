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
    //public static Biome cragFlatsBiome = new BiomeCragFlats();
    public static Biome cragCliffsBiome = new BiomeCragCliffs();
    public static Biome redwoodForest = new BiomeRedwoodForest();

    public static final Version V1 = new Version(1,0,0);
    public static final Version V11 = new Version(1,1,0);
    public static final Version V12 = new Version(1,2,0);
    public static final Version V13 = new Version(1,3,0);
    public static final Version V131= new Version(1,3,1);
    public static final Version V14 = new Version(1,4,0);

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        register(V1, autumnalWoodsBiome, BiomeType.COOL, "autumnal_woods", 8, TraverseConfig.disableAutumnalWoods, event, FOREST);
        register(V1, woodlandsBiome, BiomeType.WARM, "woodlands", 9, TraverseConfig.disableWoodlands, event, PLAINS);
        register(V1, miniJungleBiome, BiomeType.WARM, "mini_jungle", 3, TraverseConfig.disableMiniJungle, event, DENSE, JUNGLE, HOT, WET);
        register(V1, meadowBiome, BiomeType.COOL, "meadow", 7, TraverseConfig.disableMeadow, event, PLAINS, LUSH, WET);
        register(V1, lushSwampBiome, BiomeType.WARM, "green_swamp", 6, TraverseConfig.disableLushSwamp, event, LUSH, WET, SWAMP);
        register(V1, redDesertBiome, BiomeType.DESERT, "red_desert", 6, TraverseConfig.disableRedDesert, event, HOT, DRY, SANDY);
        register(V1, temperateRainforestBiome, BiomeType.COOL, "temperate_rainforest", 8, TraverseConfig.disableTemperateRainforest, event, FOREST, CONIFEROUS);
        register(V11, badlandsBiome, BiomeType.WARM, "badlands", 5, TraverseConfig.disableBadlands, event, PLAINS, DRY, HOT, SPARSE);
        register(V11, mountainousDesertBiome, BiomeType.DESERT, "mountainous_desert", 2, TraverseConfig.disableMountainousDesert, event, MOUNTAIN, DRY, HOT, SANDY);
        register(V11, rockyPlateauBiome, BiomeType.WARM, "rocky_plateau", 4, TraverseConfig.disableRockyPlateau, event, HOT, MOUNTAIN);
        register(V11, forestedHillsBiome, BiomeType.COOL, "forested_hills", 6, TraverseConfig.disableForestedHills, event, FOREST, HILLS);
        register(V11, birchForestedHillsBiome, BiomeType.COOL, "birch_forested_hills", 2, TraverseConfig.disableBirchForestedHills, event, FOREST, HILLS);
        register(V11, autumnalWoodedHillsBiome, BiomeType.COOL, "autumnal_wooded_hills", 1, TraverseConfig.disableAutumnalWoodedHills, event, FOREST, HILLS);
        register(V12, cliffsBiome, BiomeType.COOL, "cliffs", 2, TraverseConfig.disableCliffs, event, MOUNTAIN, COLD, HILLS);
        register(V12, glacierBiome, BiomeType.ICY, "glacier", 6, TraverseConfig.disableGlacier, event, MOUNTAIN, COLD, SNOWY);
        register(V12, glacierSpikesBiome, BiomeType.ICY, "glacier_spikes", 2, TraverseConfig.disableGlacierSpikes, event, MOUNTAIN, COLD, SNOWY);
        register(V12, snowyConiferousForestBiome, BiomeType.ICY, "snowy_coniferous_forest", 5, TraverseConfig.disableSnowyConiferousForest, event, COLD, SNOWY, FOREST, CONIFEROUS);
        register(V13, lushHillsBiome, BiomeType.COOL, "lush_hills", 6, TraverseConfig.disableLushHills, event, LUSH, HILLS, SPARSE, WET);
        register(V13, canyonBiome, BiomeType.DESERT, "canyon", 5, TraverseConfig.disableCanyon, event, DRY, DEAD);
        register(V13, cragCliffsBiome, BiomeType.COOL, "crag_cliffs", 4, TraverseConfig.disableCragCliffs, event, COLD, DEAD);
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
