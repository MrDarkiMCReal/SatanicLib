package org.mrdarkimc.SatanicLib;

import org.bukkit.plugin.java.JavaPlugin;

public class SatanicLib {
private static JavaPlugin plugin;
public static JavaPlugin getPlugin(){
    return plugin;
}
    public static void setupLib(JavaPlugin plugin2) {
        plugin = plugin2;
    }
}