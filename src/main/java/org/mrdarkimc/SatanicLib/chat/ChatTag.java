package org.mrdarkimc.SatanicLib.chat;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatExtra;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatTag;

import java.util.List;


public class ChatTag implements IChatTag {

    private TextComponent component;
    @Override
    public TextComponent getComponent() {
        return component;
    }


//    public ChatTag(Player player, String text, ClickEvent clickEvent, HoverEvent hoverEvent) {
//        this.player = player;
//        text = PlaceholderAPI.setPlaceholders(player,text);
//        this.clickEvent = clickEvent;
//        this.hoverEvent = hoverEvent;
//    }
    public ChatTag(Player player, String text, IChatExtra... extras){
        text = PlaceholderAPI.setPlaceholders(player,text);
        this.component = ChatUtils.translateComponentHex(text);
        for (IChatExtra extra : extras) {
            extra.applyToTextComponent(component);
        }
    }
    public ChatTag(Player player, String text, List<IChatExtra> extras){
        text = PlaceholderAPI.setPlaceholders(player,text);
        this.component = ChatUtils.translateComponentHex(text);
        for (IChatExtra extra : extras) {
            extra.applyToTextComponent(component);
        }
    }

//    public ChatTag(Player player, String text, ArrayList<String> hoverMessage) {
//        this.player = player;
//        this.text = PlaceholderAPI.setPlaceholders(player,text);
//        hoverMessage.replaceAll(string -> PlaceholderAPI.setPlaceholders(player, string));
//        hoverMessage.replaceAll(string -> string.replaceAll("\\{clan}", "ggg"));
//        ArrayList<Content> texts = ChatUtils.stringListToContentList(hoverMessage);
//        this.hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, texts);
//    }


    @Override
    public void send() {
    }
}
