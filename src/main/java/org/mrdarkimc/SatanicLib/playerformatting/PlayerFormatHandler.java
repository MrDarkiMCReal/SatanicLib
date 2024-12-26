package org.mrdarkimc.SatanicLib.playerformatting;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.VaultHandler;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

public class PlayerFormatHandler {
    public String format(Player player){
        FileConfiguration config = Configs.Defaults.mainConfig.get();
        String rawvalue = config.getString("SatanicLib.playerformat.default");
        if (rawvalue==null)
            return null;
        rawvalue = rawvalue.replace("{player}",player.getName());
        return PlaceholderAPI.setPlaceholders(player,rawvalue);
    }

    /**
     *
     * Trying to find player primary group
     *
     */
    public String formatByGroup(Player player){
        FileConfiguration config = Configs.Defaults.mainConfig.get();
        String group = VaultHandler.getChat().getPrimaryGroup(player);
        String rawvalue = config.getString("SatanicLib.playerformat." + group);
        if (rawvalue==null) {
            Bukkit.getLogger().info("[SatanicLib] Cannot define player's group: " + group + " for player: " + player.getName());
            Bukkit.getLogger().info("[SatanicLib] Make sure group is 1 word without spaces and it has in config by path: ");
            Bukkit.getLogger().info("SatanicLib:");
            Bukkit.getLogger().info("  playerformat:");
            Bukkit.getLogger().info("    default: \"%luckperms_prefix%{player}\"");
            Bukkit.getLogger().info("    " + group +": \"%luckperms_prefix%{player}\"");
            return null;
        }
        rawvalue = rawvalue.replace("{player}",player.getName());
        return PlaceholderAPI.setPlaceholders(player,rawvalue);
    }
}
