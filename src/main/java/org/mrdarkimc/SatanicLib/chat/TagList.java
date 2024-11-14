package org.mrdarkimc.SatanicLib.chat;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

import java.util.Map;

public class TagList{
    TextComponent component;
    Player player;

    public TagList(Player player, String path, Map<String, String> localPlaceholders, Configs config) {
        this.player = player;
        //this.component = ChatUtils.stackChatTags(chatTagList);
        //todo
    }

    public void send() {

    }
}
