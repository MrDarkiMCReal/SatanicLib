package org.mrdarkimc.SatanicLib;

import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.commands.ItemSaver;
import org.mrdarkimc.SatanicLib.objectManager.ObjectManager;

import java.util.Map;

public class SatanicLib {
private static JavaPlugin plugin;
private final ObjectManager objectManager = new ObjectManager();
public static JavaPlugin getPlugin(){
    return plugin;
}
private static final SatanicLib instance = new SatanicLib();

    public static SatanicLib getInstance() {
        return instance;
    }
    public static void setupLib(JavaPlugin plugin2) {
        plugin = plugin2;
    }
    public void setupCommands(){
    plugin.getServer().getPluginCommand("SatanicLib").setExecutor(new ItemSaver());
    }
    public ObjectManager getObjectManager(){
    return objectManager;
    }
}