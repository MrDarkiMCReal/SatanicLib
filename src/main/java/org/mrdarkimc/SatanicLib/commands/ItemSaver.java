package org.mrdarkimc.SatanicLib.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

public class ItemSaver implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings.length==2 && strings[0].equals("save")){
                ItemStack stack = player.getInventory().getItemInMainHand();
                Configs.Defaults.itemSaverConfig().get().set("savedItems." + strings[1],stack);
            }
        }
        return true;
    }
}
