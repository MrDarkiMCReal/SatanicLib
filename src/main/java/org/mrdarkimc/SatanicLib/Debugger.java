package org.mrdarkimc.SatanicLib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Debugger {
    public static Map<Integer, ChatColor> colores = new HashMap<>();
    {
        colores.put(0,ChatColor.DARK_GREEN);
        colores.put(1,ChatColor.RED);
        colores.put(2,ChatColor.YELLOW);
        colores.put(3,ChatColor.WHITE);
        colores.put(4,ChatColor.GRAY);
        colores.put(5,ChatColor.GREEN);
        colores.put(6,ChatColor.DARK_AQUA);
        colores.put(7,ChatColor.DARK_GRAY);
        colores.put(8,ChatColor.DARK_PURPLE);
        colores.put(9,ChatColor.DARK_RED);
        colores.put(10,ChatColor.AQUA);
        colores.put(11,ChatColor.DARK_BLUE);


    }
    public static void chat(String message){
        if (Configs.Defaults.mainConfig.get().getBoolean("SatanicLib.debugger")) {
            Server server = SatanicLib.getPlugin().getServer();
            ChatColor color = colores.get(ThreadLocalRandom.current().nextInt(0,colores.size()));
            server.getOperators().stream()
                    .filter(OfflinePlayer::isOnline)
                    .map(OfflinePlayer::getPlayer)
                    .forEach(player -> player.sendMessage(color + message));
        }
    }
    public static void chat(String message, int colorgroup){
        if (Configs.Defaults.mainConfig.get().getBoolean("SatanicLib.debugger")) {
            Server server = SatanicLib.getPlugin().getServer();
            ChatColor color = colores.get(colorgroup);
            StringBuilder finalMessage = new StringBuilder();
            finalMessage.append(ChatColor.GRAY + "---------------------------------------------\n");
            finalMessage.append(ChatColor.RED + "                ONLY FOR OPS                \n");
            finalMessage.append(color + message + "\n");
            finalMessage.append(ChatColor.GRAY + "---------------------------------------------\n");
            server.getOperators().stream()
                    .filter(OfflinePlayer::isOnline)
                    .map(OfflinePlayer::getPlayer)
                    .forEach(player -> player.sendMessage(color + message));
            Bukkit.getLogger().info("[SatanicDebug] " + message);
        }
    }
    public static void chat(Player target, String message){
        if (Configs.Defaults.mainConfig.get().getBoolean("SatanicLib.debugger")) {
            ChatColor color = colores.get(ThreadLocalRandom.current().nextInt(0,colores.size()));
            target.sendMessage(color + message);
        }
    }
}
