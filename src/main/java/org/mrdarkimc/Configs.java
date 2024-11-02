package org.mrdarkimc;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Configs{
    private final JavaPlugin plugin;
    private File configFile;
    private FileConfiguration config;


    public Configs(JavaPlugin plugin,String configName){
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), configName + ".yml");
        if (!this.configFile.exists()){
            plugin.saveResource(configName + ".yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void loadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public FileConfiguration get() {
        if (config == null) {
            loadConfig();
        }
        return config;
    }
    public String getKeyedText(String path){
        return this.get().getString(path);
    }
    public void saveConfig () {
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
