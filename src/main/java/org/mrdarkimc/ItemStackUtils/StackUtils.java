package org.mrdarkimc.ItemStackUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mrdarkimc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StackUtils {
    //DIAMOND_SWORD 1 name:"Super name" lore:"Text 1 \ntext 2 \ntext3" enchant:"SHARPNESS:5,SMITE:5" //todo fix : in enchants
    public static ItemStack stringToStack(String item, FileConfiguration config) {
        ItemStack stack;
        if (item.startsWith("saved:")) {
            item = item.replace("saved:", "");
            stack = config.getItemStack("saves." + item);
            if (stack == null) {
                stack = new ItemStack(Material.STONE, 1);
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "ПРЕДМЕТ: " + item + " не найден!");
                stack.setItemMeta(meta);
            }
        } else {
            String[] parts = item.split(" ");
            Material material = Material.valueOf(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            stack = new ItemStack(material, amount);
            if (parts.length > 2) {
                ItemMeta meta = stack.getItemMeta();
                List<String> list = Arrays.stream(parts).collect(Collectors.toList());
                list.remove(0);
                list.remove(0);
                for (String tag : list) {
                    String[] contents = tag.split(":");
                    String tagExtracted = contents[0];
                    String value = contents[1].replaceAll("\"", ""); //Text 1 \ntext 2 \ntext3
                    switch (tagExtracted) {
                        case "name":
                            meta.setDisplayName(value);
                            break;
                        case "lore":
                            List<String> lore = new ArrayList<>(Arrays.stream(value.split("\n")).toList());
                            lore.replaceAll(line -> Utils.translateHex(line) + "\n");
                            meta.setLore(lore);
                            break;
                        case "enchant":
                            String[] enchants = value.split(",");
                            for (String string : enchants) {
                                String[] str = string.split("-");
                                Enchantment enc = Enchantment.getByName(str[0]);
                                int level = Integer.parseInt(str[1]);
                                meta.addEnchant(enc, level, true);
                            }
                            break;
                    }
                }
                stack.setItemMeta(meta);
            }
        }
        return stack;
    }
}
