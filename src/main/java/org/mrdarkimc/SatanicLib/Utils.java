package org.mrdarkimc.SatanicLib;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.ItemStackUtils.StackUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static List<MerchantRecipe> deserealizeRecipes(JavaPlugin plugin, Configs config) {
        List<MerchantRecipe> recipes = new ArrayList<>();
        List<Map<?, ?>> trades = plugin.getConfig().getMapList("trades");


        for (Map<?, ?> trade : trades) {
            String result = (String) trade.get("item");
            List<String> ingridients = (List<String>) trade.get("recipe");
            String[] arrayofRecipeItems = ingridients.toArray(new String[2]);

            MerchantRecipe recipeTemp = new MerchantRecipe(StackUtils.stringToStack(result, config.get()), 1);
            for (String recipe : arrayofRecipeItems) {
                recipeTemp.addIngredient(StackUtils.stringToStack(recipe, config.get()));
            }
            recipes.add(recipeTemp);
        }
        return recipes;
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
        System.out.println("=======================================================");
        System.out.println("  __  __      _____             _    _ __  __  _____ ");
        System.out.println(" |  \\/  |    |  __ \\           | |  (_)  \\/  |/ ____|");
        System.out.println(" | \\  / |_ __| |  | | __ _ _ __| | ___| \\  / | |     ");
        System.out.println(" | |\\/| | '__| |  | |/ _` | '__| |/ / | |\\/| | |     ");
        System.out.println(" | |  | | |  | |__| | (_| | |  |   <| | |  | | |____ ");
        System.out.println(" |_|  |_|_|  |_____/ \\__,_|_|  |_|\\_\\_|_|  |_|\\_____|");

        System.out.println("  _____                 _                                  _   ");
        System.out.println(" |  __ \\               | |                                | |  ");
        System.out.println(" | |  | | _____   _____| | ___  _ __  _ __ ___   ___ _ __ | |_ ");
        System.out.println(" | |  | |/ _ \\ \\ / / _ \\ |/ _ \\| '_ \\| '_ ` _ \\ / _ \\ '_ \\| __|");
        System.out.println(" | |__| |  __/\\ V /  __/ | (_) | |_) | | | | | |  __/ | | | |_ ");
        System.out.println(" |_____/ \\___| \\_/ \\___|_|\\___/| .__/|_| |_| |_|\\___|_| |_|\\__|");
        System.out.println("                               | |                             ");
        System.out.println("                               |_|                             ");
        System.out.println("Enabling plugin " + pluginname + " by @MrDarkiMC");
        System.out.println("=======================================================");
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

