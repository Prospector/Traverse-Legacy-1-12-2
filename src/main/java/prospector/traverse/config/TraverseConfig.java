package prospector.traverse.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prospector.shootingstar.version.Version;
import prospector.traverse.core.TraverseConstants;

import java.io.*;

public class TraverseConfig {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static File configDir;
    public static File versionConfig;
    public static File mainConfig;
    public static Configuration config;
    public static TraverseConfig traverseConfiguration;

    public static Version version;

    public static boolean useVanillaWood = false;
    public static boolean registerBiomesRegardless = false;

    private static TraverseConfig instance = null;

    private TraverseConfig() {
        config = new Configuration(mainConfig);
        config.load();

        useVanillaWood = config.get(Configuration.CATEGORY_GENERAL, "useVanillaWood", useVanillaWood, "Use vanilla logs for Traverse trees (might not look as nice)").getBoolean();
        registerBiomesRegardless = config.get(Configuration.CATEGORY_GENERAL, "registerBiomesRegardless", registerBiomesRegardless, "All biomes will always be registered, ignoring the instance version (WARNING: This will cause ugly world generation borders at the edge of what has previously been generated and what is new!!)").getBoolean();

        config.save();
    }

    public static TraverseConfig initialize() {
        if (instance == null)
            instance = new TraverseConfig();
        else
            throw new IllegalStateException("Cannot initialize config twice");

        return instance;
    }

    public static TraverseConfig getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance of config requested before initialization");
        }
        return instance;
    }

    public static void initialize(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory(), TraverseConstants.MOD_ID);
        if (!configDir.exists()) {
            configDir.mkdir();
        }

        mainConfig = new File(configDir, "traverse.cfg");
        traverseConfiguration = initialize();

        versionConfig = new File(configDir, "instance_version.json");

        if (TraverseConstants.MOD_VERSION_MAJOR.equals("@major@")) {
            version = TraverseConstants.DEV_VERSION;
        } else {
            version = new Version(Integer.parseInt(TraverseConstants.MOD_VERSION_MAJOR), Integer.parseInt(TraverseConstants.MOD_VERSION_MINOR), Integer.parseInt(TraverseConstants.MOD_VERSION_PATCH));
        }

        reloadVersionConfig();
    }

    public static void reloadVersionConfig() {
        if (!versionConfig.exists()) {
            writeVersionConfig(new VersionConfig());
        }
        if (versionConfig.exists()) {
            VersionConfig config = null;
            try (Reader reader = new FileReader(versionConfig)) {
                config = GSON.fromJson(reader, VersionConfig.class);
                version = config.version;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (config == null) {
                config = new VersionConfig();
                writeVersionConfig(config);
            }
        }
    }

    public static void writeVersionConfig(VersionConfig config) {
        try (Writer writer = new FileWriter(versionConfig)) {
            GSON.toJson(config, writer);
        } catch (Exception e) {

        }
        reloadVersionConfig();
    }

    public static class VersionConfig {

        public Version version = TraverseConfig.version;

        public Version getVersion() {
            return version;
        }

        public void setVersion(Version version) {
            this.version = version;
        }
    }
}
