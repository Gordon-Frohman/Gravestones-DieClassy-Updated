
package net.subaraki.gravestone.handler;

import java.io.*;

import net.minecraftforge.common.config.*;

public class ConfigHandler {

    public static ConfigHandler instance;
    public static boolean enableGravesTroughKey;
    public static int graveLevel;
    public static int[] graveOrder;
    public static boolean allowDebug;

    public void loadConfig(final File file) {
        final Configuration config = new Configuration(file);
        config.load();
        this.loadSettings(config);
        config.save();
    }

    private void loadSettings(final Configuration config) {
        ConfigHandler.enableGravesTroughKey = config.get("GraveStones", "enable Graves Trough Key", true)
            .getBoolean(true);
        ConfigHandler.graveLevel = config.get("GraveStones", "change graves every x level", 5)
            .getInt(5);
        ConfigHandler.graveOrder = config
            .get("GraveStones", "grave orders", new int[] { 6, 1, 2, 3, 7, 4, 5, 10, 9, 8 })
            .getIntList();
        ConfigHandler.allowDebug = config.get("GraveStones", "Toggle debug lines to print in the console", false)
            .getBoolean(false);
    }

    static {
        ConfigHandler.instance = new ConfigHandler();
        ConfigHandler.enableGravesTroughKey = true;
        ConfigHandler.graveLevel = 5;
        ConfigHandler.graveOrder = new int[] { 6, 1, 2, 3, 7, 4, 5, 10, 9, 8 };
        ConfigHandler.allowDebug = true;
    }
}
