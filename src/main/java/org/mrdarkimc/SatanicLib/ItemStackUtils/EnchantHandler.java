package org.mrdarkimc.SatanicLib.ItemStackUtils;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantHandler {
    public static ItemStack applyEnchantments(ItemStack stack, String enchantments){
        String[] enchants = enchantments.split(",");
        ItemMeta meta = stack.getItemMeta();
        for (String enchant : enchants) {
            String[] parts = enchant.split(":");
            String enchantmentString = parts[0].toLowerCase();
            int lvl = Integer.parseInt(parts[1]);

            Enchantment enc = Enchantment.getByKey(NamespacedKey.fromString(enchantmentString)); //todo might not work. where is list of namespacedKeys ?

            meta.addEnchant(enc,lvl,true);

        }
        stack.setItemMeta(meta);
        return stack;
    }
    public static void applyEnchantmentsToMeta(ItemMeta meta, String enchantments){
        String[] enchants = enchantments.split(",");
        for (String enchant : enchants) {
            String[] parts = enchant.split(":");
            String enchantmentString = parts[0];
            int lvl = Integer.parseInt(parts[1]);

            Enchantment enc = Enchantment.getByKey(NamespacedKey.fromString(enchantmentString)); //todo might not work. where is list of namespacedKeys ?

            meta.addEnchant(enc,lvl,true);
        }
    }
}
