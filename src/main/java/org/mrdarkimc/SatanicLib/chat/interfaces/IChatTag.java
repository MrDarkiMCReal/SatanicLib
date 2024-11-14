package org.mrdarkimc.SatanicLib.chat.interfaces;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public interface IChatTag {
    void send();
    TextComponent getComponent();
}
