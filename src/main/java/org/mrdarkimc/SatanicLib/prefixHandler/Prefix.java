package org.mrdarkimc.SatanicLib.prefixHandler;



import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.messages.KeyedMessage;
import org.mrdarkimc.SatanicLib.messages.Message;
import org.mrdarkimc.SatanicLib.prefixHandler.interfaces.PrefixInterface;
import org.mrdarkimc.SatanicLib.prefixHandler.prefixes.*;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Prefix {
    public static final Map<String, Function<List<Object>, Object>> prefixMap = new HashMap<>();

    //префикс - Функция (лист аргументов, обьект)
    static {
        prefixMap.put("[command]", PlayerCommand::new);
        prefixMap.put("[console]", ConsoleCommand::new);
        prefixMap.put("[text]", Message::new);
        //prefixMap.put("[requirement]", SimpleRequirement::new); //expect: List.of(Player, javaScript, Message) or List.of(Player, javaScript) //todo
        prefixMap.put("[keyedText]", KeyedMessage::new);
        //prefixMap.put("[item]", Item::new);
        //prefixMap.put("[saved]", SavedItem::new);
        //prefixMap.put("[hasItem]", )
    }

    /**
     * do not use that, if you dont know what are you doing
     * @param args
     * @return
     */
    public static PrefixInterface get(String args){
        String prefix = args.split(" ")[0];
        args = (args.substring(prefix.length()+1)); //substring prefix + whitespace
        List<Object> list = Arrays.stream(args.split(" ")).collect(Collectors.toList());
       return (PrefixInterface) prefixMap.get(prefix).apply(list);
    }
    public static void handle(Player player, String text, Map<String, String> placeholders) {
        Pattern pattern = Pattern.compile("^\\[[^\\]]+\\]");
        Matcher matcher = pattern.matcher(text);
        for (Map.Entry<String, String> stringStringEntry : placeholders.entrySet()) {
            text = text.replace(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        text = PlaceholderAPI.setPlaceholders(player,text);
        if (matcher.find()){
            text = text.substring(matcher.group().length()+1); //+1 with space
            switch (matcher.group()){
                case "[console]":
                    //todo создать отдельные хандлеры для команд, звуков, реквайрментов, и т.д
                    new ConsoleCommand(text);
                    break;
                case "[command]":
                    //todo создать отдельные хандлеры для команд, звуков, реквайрментов, и т.д
                    new Command(player,text);
                    break;
                case "[text]":
                    new Message(player,text,placeholders).send();
                    break;
                case "[keyedText]":
                    new KeyedMessage(player,text,placeholders).send();
                    break;
                default:
                    Bukkit.getLogger().warning("[SatanicLib] Prefix: " + text + " did not recognized. Ignoring this key complitely. Allowed: " + prefixMap.keySet().toString());
                    break;
            }
        }

    }
//    public static final Map<String, Function<String, Object>> prefixMap = new HashMap<>();
//
//    static {
//        prefixMap.put("[command]", Player::new);
//        prefixMap.put("[console]", Admin::new);
//        prefixMap.put("[text]", Admin::new);
//        prefixMap.put("[requirement]", Admin::new);
//    }
}
