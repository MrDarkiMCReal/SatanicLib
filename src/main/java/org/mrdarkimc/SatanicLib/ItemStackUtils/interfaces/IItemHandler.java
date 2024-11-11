package org.mrdarkimc.SatanicLib.ItemStackUtils.interfaces;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface IItemHandler {
    //todo is this really need it?
    ItemStack applyToItemStack(ItemStack stack, String value);
    void applyToItemMeta(ItemMeta meta, String value);
}
