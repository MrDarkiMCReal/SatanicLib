package org.mrdarkimc.ItemStackUtils;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantedBookHandler {
    public static ItemStack applyToItemStack(ItemStack stack, String value) {
        String[] enchants = value.split(",");
        ItemMeta meta = stack.getItemMeta();
        EnchantmentStorageMeta storedEnc = (EnchantmentStorageMeta) meta;
        for (String enchant : enchants) {
            String[] parts = enchant.split(":");
            String enchantmentString = parts[0].toLowerCase();
            int lvl = Integer.parseInt(parts[1]);
            Enchantment enc = Enchantment.getByKey(NamespacedKey.fromString(enchantmentString));


            storedEnc.addStoredEnchant(enc,lvl,true);
            stack.setItemMeta(storedEnc);

        }
        return stack;
    }
    public static void applyToMeta(ItemMeta meta, String value) {
        String[] enchants = value.split(",");
        for (String enchant : enchants) {
            String[] parts = enchant.split(":");
            String enchantmentString = parts[0];
            int lvl = Integer.parseInt(parts[1]);
            Enchantment enc = Enchantment.getByKey(NamespacedKey.fromString(enchantmentString));


            ((EnchantmentStorageMeta)meta).addStoredEnchant(enc,lvl,true);
        }
    }
}
