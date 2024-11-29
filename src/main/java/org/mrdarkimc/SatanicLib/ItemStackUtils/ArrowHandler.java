package org.mrdarkimc.SatanicLib.ItemStackUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArrowHandler {
    //tipped_arrow 2 arrow:"strength:2,speed:2"
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
