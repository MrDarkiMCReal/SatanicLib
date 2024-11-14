package org.mrdarkimc.SatanicLib.chat.extras;

import net.md_5.bungee.api.chat.HoverEvent;
import org.mrdarkimc.SatanicLib.chat.ChatUtils;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatExtra;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ShowText implements IChatExtra {
    List<String> text;
    public ShowText(String text, String customSpliterator) {
        this.text = Arrays.stream(text.split(customSpliterator)).collect(Collectors.toList());
    }
    public ShowText(String text) {
        text = text.replace("\\n","\n");
        this.text = Arrays.stream(text.split("\n")).collect(Collectors.toList());
    }
    public ShowText(List<String> strings) {
        this.text = strings;
    }


    @Override
    public void applyToTextComponent(net.md_5.bungee.api.chat.TextComponent component) {
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ChatUtils.stringListToContentList(text)));
    }
}
