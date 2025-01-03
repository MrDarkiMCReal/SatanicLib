package org.mrdarkimc.SatanicLib;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.chat.ChatTag;
import org.mrdarkimc.SatanicLib.chat.ChatUtils;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatTag;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagBuilderGetter {


    public static TextComponent get(Player player, String path, Map<String, String> placeholders){
        Configs config = Configs.Defaults.chattags;
        List<String> list = config.get().getStringList("builders." + path);
        //List<TextComponent> components = new ArrayList<>();
        //components.add(new TextComponent());
        TextComponent main = new TextComponent();
        for (int i = 0; i < list.size(); i++) {
            String[] tags = list.get(i).split(",");
            //List<IChatTag> list2 = new ArrayList<>();
            TextComponent mainTag = new TextComponent();
            for (String tagID : tags) {
                TextComponent tag = config.getTextComponent(player,"tags." + tagID,placeholders);
                    mainTag.addExtra(tag);
            }
            //TextComponent component = ChatUtils.stackChatTags(list2);
            if (i < list.size()-1) {
                mainTag.addExtra("\n");
            }
            main.addExtra(mainTag);
        }
//        for (String s : list) {
//            String[] tags = s.split(",");
//            List<IChatTag> list2 = new ArrayList<>();
//            list2.add(new ChatTag(player,""));
//            for (String tagID : tags) {
//                IChatTag tag = config.getChatTag(player,"tags." + tagID,placeholders);
//                list2.add(tag);
//            }
//            TextComponent component = ChatUtils.stackChatTags(list2);
//            if (!s.equals(list.get(list.size()-1))) {
//                component.addExtra("\n");
//            }
//            components.add(component);
//        }
        return main;
    }
}
