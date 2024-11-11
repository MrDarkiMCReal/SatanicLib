package org.mrdarkimc.SatanicLib.ItemStackUtils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoreHandler {
    public static List<String> stringToLore(String lore, Map<String, String> placeholders){
        //make sure you parsed PAPI first
        for (String key : placeholders.keySet()) {
            lore = lore.replace(key,placeholders.get(key));
        }
        lore = lore.replace("\\n", "\n");
        List<String> list = Arrays.stream(lore.split("\n")).collect(Collectors.toList());
        list.replaceAll(Utils::translateHex);
        list.replaceAll( (line) -> line+ "\n");
       return list;
    }
    public static List<String> stringToLore(Player player, String lore, Map<String, String> placeholders){ //todo for removal or redo?
        lore = PlaceholderAPI.setPlaceholders(player,lore);
        for (String key : placeholders.keySet()) {
            lore = lore.replace(key,placeholders.get(key));
        }
        lore = Utils.translateHex(lore);
        return Arrays.stream(lore.split("\n")).collect(Collectors.toList());
    }
}
