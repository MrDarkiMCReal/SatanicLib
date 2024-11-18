package org.mrdarkimc.SatanicLib;

import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.commands.ItemSaver;

import java.util.Map;

public class SatanicLib {
private static JavaPlugin plugin;
public static JavaPlugin getPlugin(){
    return plugin;
}
    public static void setupLib(JavaPlugin plugin2) {
        plugin = plugin2;
    }
    public void setupCommands(){
    plugin.getServer().getPluginCommand("SatanicLib").setExecutor(new ItemSaver());
    }
}