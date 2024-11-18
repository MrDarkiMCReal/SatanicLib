package org.mrdarkimc.SatanicLib;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MerchantRecipe;
import org.mrdarkimc.SatanicLib.ItemStackUtils.StackUtils;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static void sendMessage(Player player, String message, Map<String, String> placeholders){
        if (placeholders != null){
            for (Map.Entry<String, String> s : placeholders.entrySet()) {
                message = message.replaceAll(s.getKey(),s.getValue());
            }
        }
        message = PlaceholderAPI.setPlaceholders(player, message);
        message = Utils.translateHex(message);
        player.sendMessage(message);
    }

    public static String translateHex(String message) {
        Pattern pattern = Pattern.compile("&#[0-9A-Fa-f]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, net.md_5.bungee.api.ChatColor.of(color.replaceAll("&", "")) + "");
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static void startUp(String pluginname) {
        Logger log = SatanicLib.getPlugin().getLogger();
        log.info("=======================================================");
        log.info("  __  __      _____             _    _ __  __  _____ ");
        log.info(" |  \\/  |    |  __ \\           | |  (_)  \\/  |/ ____|");
        log.info(" | \\  / |_ __| |  | | __ _ _ __| | ___| \\  / | |     ");
        log.info(" | |\\/| | '__| |  | |/ _` | '__| |/ / | |\\/| | |     ");
        log.info(" | |  | | |  | |__| | (_| | |  |   <| | |  | | |____ ");
        log.info(" |_|  |_|_|  |_____/ \\__,_|_|  |_|\\_\\_|_|  |_|\\_____|");

        log.info("  _____                 _                                  _   ");
        log.info(" |  __ \\               | |                                | |  ");
        log.info(" | |  | | _____   _____| | ___  _ __  _ __ ___   ___ _ __ | |_ ");
        log.info(" | |  | |/ _ \\ \\ / / _ \\ |/ _ \\| '_ \\| '_ ` _ \\ / _ \\ '_ \\| __|");
        log.info(" | |__| |  __/\\ V /  __/ | (_) | |_) | | | | | |  __/ | | | |_ ");
        log.info(" |_____/ \\___| \\_/ \\___|_|\\___/| .__/|_| |_| |_|\\___|_| |_|\\__|");
        log.info("                               | |                             ");
        log.info("                               |_|                             ");
        log.info("Enabling plugin " + pluginname + " by @MrDarkiMC");
        log.info("=======================================================");
    }



    protected static String extractQuotedValue(String input) {
        String regex = "\"([^\"]*)\"";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "null";
    }
}

