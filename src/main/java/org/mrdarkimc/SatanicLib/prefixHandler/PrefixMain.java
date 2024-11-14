package org.mrdarkimc.SatanicLib.prefixHandler;



import org.mrdarkimc.SatanicLib.messages.KeyedMessage;
import org.mrdarkimc.SatanicLib.messages.Message;
import org.mrdarkimc.SatanicLib.prefixHandler.interfaces.PrefixInterface;
import org.mrdarkimc.SatanicLib.prefixHandler.prefixes.ConsoleCommand;
import org.mrdarkimc.SatanicLib.prefixHandler.prefixes.Item;
import org.mrdarkimc.SatanicLib.prefixHandler.prefixes.PlayerCommand;
import org.mrdarkimc.SatanicLib.prefixHandler.prefixes.SavedItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrefixMain {
    public static final Map<String, Function<List<Object>, Object>> prefixMap = new HashMap<>();

    //префикс - Функция (лист аргументов, обьект)
    static {
        prefixMap.put("[command]", PlayerCommand::new);
        prefixMap.put("[console]", ConsoleCommand::new);
        prefixMap.put("[text]", Message::new);
        //prefixMap.put("[requirement]", SimpleRequirement::new); //expect: List.of(Player, javaScript, Message) or List.of(Player, javaScript) //todo
        prefixMap.put("[keyedText]", KeyedMessage::new);
        prefixMap.put("[item]", Item::new);
        prefixMap.put("[saved]", SavedItem::new);
        //prefixMap.put("[hasItem]", )
    }
    public static PrefixInterface get(String args){
        String prefix = args.split(" ")[0];
        args = (args.substring(prefix.length()+1)); //substring prefix + whitespace
        List<Object> list = Arrays.stream(args.split(" ")).collect(Collectors.toList());
       return (PrefixInterface) prefixMap.get(prefix).apply(list);
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
