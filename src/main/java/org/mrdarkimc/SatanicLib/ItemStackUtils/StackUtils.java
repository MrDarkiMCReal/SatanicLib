package org.mrdarkimc.SatanicLib.ItemStackUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mrdarkimc.SatanicLib.Utils;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StackUtils {
    public static ItemStack stringToItemStack(String input, Map<String, String> placeholders) {
        // Входная строка с парой ключ-значение, где одно значение имеет запятые
        //stone 1 name:"peska penis" lore:"supa \nlore \nbitch" enchants:"sharpness:5,protection:5"
        //potion 1 potion:"speed:5,weakness"
        //input = "STONE 1 name:\"custom name\" lore:\"custom lore\" enchants:\"sharpness:5,protection:5\"";
        if (input.startsWith("[saved]")){
            String saveditem = input.split(" ")[1];
            return Configs.Defaults.itemSaverConfig().get().getItemStack("savedItems." + saveditem);
        }
        String[] parts = input.split(" ");
        Material material = Material.valueOf(parts[0].toUpperCase());
        int amount = Integer.parseInt(parts[1]);
        String regex = "(\\w+):\"([^\"]*)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);


        Map<String, String> tagsMap = new HashMap<>();

        while (matcher.find()) {

            String key = matcher.group(1);
            String value = matcher.group(2);

            tagsMap.put(key, value);
            Bukkit.getLogger().info("Putting key: " + key + " And value: " + value);

        }
        ItemStack stack = new ItemStack(material,amount);
        return applyTagsToStack(stack, tagsMap, placeholders);
    }
    //stone 1 name:"peska penis" lore:"supa \nlore \nbitch" enchants:"sharpness:5,protection:5"
    public static ItemStack applyTagsToStack(ItemStack stack, Map<String, String> tagMap,Map<String, String> placeholders){
        ItemMeta meta = stack.getItemMeta();
        for (String tag : tagMap.keySet()) {
            String value = tagMap.get(tag);
            switch (tag){
                case "name":
                    meta.setDisplayName(Utils.translateHex(value));
                    break;
                case "lore":
                    meta.setLore(LoreHandler.stringToLore(value,placeholders));
                    break;
                case "enchants":
                    EnchantHandler.applyEnchantmentsToMeta(meta,value);
                    break;
                case "head":
                    HeadHandler.applyToMeta(meta,value);
                    break;
                case "book":
                    EnchantedBookHandler.applyToMeta(meta,value);
                    break;
                case "flags":
                    //todo
                    break;
                case "CMD":
                    //todo
                    break;
                case "PDC":
                    //todo
                    break;
                case "potion":
                    PotionHandler.applyToMeta(meta,value);
                    break;
            }

        }
        stack.setItemMeta(meta);
        return stack;
    }
    //DIAMOND_SWORD 1 name:"Super name" lore:"Text 1 \ntext 2 \ntext3" enchant:"SHARPNESS:5,SMITE:5" //todo fix : in enchants
//    public static ItemStack stringToStack(String item, FileConfiguration config) {
//        ItemStack stack;
//        if (item.startsWith("saved:")) {
//            item = item.replace("saved:", "");
//            stack = config.getItemStack("saves." + item);
//            if (stack == null) {
//                stack = new ItemStack(Material.STONE, 1);
//                ItemMeta meta = stack.getItemMeta();
//                meta.setDisplayName(ChatColor.RED + "ПРЕДМЕТ: " + item + " не найден!");
//                stack.setItemMeta(meta);
//            }
//        } else {
//            String[] parts = item.split(" ");
//            Material material = Material.valueOf(parts[0]);
//            int amount = Integer.parseInt(parts[1]);
//            stack = new ItemStack(material, amount);
//            if (parts.length > 2) {
//                ItemMeta meta = stack.getItemMeta();
//                List<String> list = Arrays.stream(parts).collect(Collectors.toList());
//                list.remove(0);
//                list.remove(0);
//                for (String tag : list) {
//                    String[] contents = tag.split(":");
//                    String tagExtracted = contents[0];
//                    String value = contents[1].replaceAll("\"", ""); //Text 1 \ntext 2 \ntext3
//                    switch (tagExtracted) {
//                        case "name":
//                            meta.setDisplayName(value);
//                            break;
//                        case "lore":
//                            List<String> lore = Arrays.stream(value.split("\n")).collect(Collectors.toList());
//                            lore.replaceAll(line -> Utils.translateHex(line) + "\n");
//                            meta.setLore(lore);
//                            break;
//                        case "enchant":
//                            String[] enchants = value.split(",");
//                            for (String string : enchants) {
//                                String[] str = string.split("-");
//                                Enchantment enc = Enchantment.getByName(str[0]);
//                                int level = Integer.parseInt(str[1]);
//                                meta.addEnchant(enc, level, true);
//                            }
//                            break;
//                    }
//                }
//                stack.setItemMeta(meta);
//            }
//        }
//        return stack;
//    }
}
