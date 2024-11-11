package org.mrdarkimc.SatanicLib.ItemStackUtils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadHandler {
    public static ItemStack applyToStack(ItemStack stack, String value){
    ItemMeta meta = stack.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(value);
        ((SkullMeta) meta).setOwningPlayer(offlinePlayer);
        stack.setItemMeta(meta);
        return stack;
    }
    public static void applyToMeta(ItemMeta meta, String value){
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(value);
        ((SkullMeta) meta).setOwningPlayer(offlinePlayer);
    }
}
