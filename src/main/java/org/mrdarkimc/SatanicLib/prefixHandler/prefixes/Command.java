package org.mrdarkimc.SatanicLib.prefixHandler.prefixes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command {
    public Command(Player player, String command){
        Bukkit.dispatchCommand(player,command);
    }
}
