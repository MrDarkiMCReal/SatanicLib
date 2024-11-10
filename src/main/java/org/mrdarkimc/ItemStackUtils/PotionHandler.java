package org.mrdarkimc.ItemStackUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionHandler {
    public static ItemStack applyToItemStack(ItemStack stack, String value){
        ItemMeta meta = stack.getItemMeta();
        String[] potions = value.split(",");
        for (String potion : potions) {
            String[] parts = potion.split(":");
            String potionTypeString = parts[0];
            int amplifirer = Integer.parseInt(parts[1]);
            int duration = Integer.parseInt(parts[2]);

            PotionEffectType effectType = PotionEffectType.getByKey(NamespacedKey.fromString(potionTypeString)); //todo
            if (effectType!=null) {
                ((PotionMeta) meta).addCustomEffect(new PotionEffect(effectType, duration, amplifirer), true);
            }else {
                Bukkit.getLogger().info(ChatColor.RED + "[SatanicLib] Cant find potion: " + potionTypeString);
            }

        }
        stack.setItemMeta(meta);
        return stack;
    }
    public static void applyToMeta(ItemMeta meta, String value) {
        String[] potions = value.split(",");
        for (String potion : potions) {
            String[] parts = potion.split(":");
            String potionTypeString = parts[0].toLowerCase();
            int amplifirer = Integer.parseInt(parts[1]);
            int duration = Integer.parseInt(parts[2]);

            PotionEffectType effectType = PotionEffectType.getByKey(NamespacedKey.fromString(potionTypeString)); //todo
            if (effectType!=null) {
                ((PotionMeta) meta).addCustomEffect(new PotionEffect(effectType, duration, amplifirer), true);
            }else {
                Bukkit.getLogger().info(ChatColor.RED + "[SatanicLib] Cant find potion: " + potionTypeString);
            }
        }
    }
}
