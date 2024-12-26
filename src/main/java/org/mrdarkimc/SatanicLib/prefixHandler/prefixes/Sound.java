package org.mrdarkimc.SatanicLib.prefixHandler.prefixes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Sound {
    public Sound(Player player, String sound) {
        player.playSound(player.getLocation(), org.bukkit.Sound.valueOf(sound.toUpperCase()),1,1);
    }
}
