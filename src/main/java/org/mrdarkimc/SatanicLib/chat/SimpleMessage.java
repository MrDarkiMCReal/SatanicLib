package org.mrdarkimc.SatanicLib.chat;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.Utils;

import java.util.List;
import java.util.Map;

public class SimpleMessage {
    public static class ToPlayer{
        public static void sendListMessages(Player player, List<String> messages, Map<String, String> localPlaceholders){
            messages.replaceAll(Utils::translateHex);
            for (String key : localPlaceholders.keySet()) {
                messages.replaceAll(line -> line.replace(key,localPlaceholders.get(key)));
            }
            PlaceholderAPI.setPlaceholders(player,messages);
            for (String message : messages) {
                player.sendMessage(message);
            }
        }
    }
    public static class Broadcast{
        public static void sendListMessages(List<String> messages, Map<String, String> localPlaceholders){
            messages.replaceAll(Utils::translateHex);
            for (String key : localPlaceholders.keySet()) {
                messages.replaceAll(line -> line.replace(key,localPlaceholders.get(key)));
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                PlaceholderAPI.setPlaceholders(player,messages);
                for (String message : messages) {
                    player.sendMessage(message);
                }
            }

        }
    }

}
