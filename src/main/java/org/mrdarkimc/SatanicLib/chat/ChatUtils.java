package org.mrdarkimc.SatanicLib.chat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ChatUtils {
    public static TextComponent translateComponentHex(String text) {
        TextComponent component = new TextComponent();
        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})([^&]*)");
        Matcher matcher = pattern.matcher(text);
        int lastMatchEnd = 0;

        while (matcher.find()) {
            if (matcher.start() > lastMatchEnd) {
                String unchangedText = text.substring(lastMatchEnd, matcher.start());
                component.addExtra(new TextComponent(unchangedText));
            }
            String hex = "#" + matcher.group(1);
            String textWithoutHex = matcher.group(2);
            TextComponent comp = new TextComponent(textWithoutHex);
            comp.setColor(ChatColor.of(hex));
            component.setColor(ChatColor.of(hex));//add last coloring to main text component. then calls a stackText components. thats how
            component.addExtra(comp);

            lastMatchEnd = matcher.end();
        }
        if (lastMatchEnd < text.length()) {
            String remainingText = text.substring(lastMatchEnd);
            component.addExtra(new TextComponent(remainingText));
        }

        return component;
    }
    public static ArrayList<Content> stringListToContentList(List<String> hoverMessage) {
        ArrayList<Content> contentList = new ArrayList<>();

        List<String> list2 = new ArrayList<>();
        for (String s : hoverMessage) {
            if (!hoverMessage.get(hoverMessage.size()-1).equals(s)){
                list2.add(s + "\n");
            }else {
                list2.add(s);
            }
        }
        TextComponent[] comps = list2.stream().map(ChatUtils::translateComponentHex).toArray(TextComponent[]::new);
        contentList.add(new Text(comps));
        return contentList;
        //output (colored)
        //Клан игрока: SATANIC
        //KD клана: 1.6
    }
    public static TextComponent stackComponents(List<TextComponent> componentList){
        TextComponent previous = null;
        for (int i = componentList.size()-1; i >= 0; i--) {
            if (i==0) {
                return previous;
            }
            TextComponent last = previous==null ? componentList.get(i) : previous;
            TextComponent stackToMe = componentList.get(i-1);
            stackToMe.addExtra(last);
            previous = stackToMe;
        }
        return previous!= null ? previous : new TextComponent("");
    }
    public static TextComponent stackChatTags(List<IChatTag> chatTagList){
        TextComponent previous = null;
        for (int i = chatTagList.size()-1; i >= 0; i--) {
            if (i==0) {
                return previous == null ? chatTagList.get(0).getComponent() : previous;
            }
            TextComponent last = previous==null ? chatTagList.get(i).getComponent() : previous;
            TextComponent stackToMe = chatTagList.get(i-1).getComponent();
            stackToMe.addExtra(last);
            previous = stackToMe;
        }
        return previous!= null ? previous : new TextComponent("");
    }
}
