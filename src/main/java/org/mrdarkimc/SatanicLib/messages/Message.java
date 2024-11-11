package org.mrdarkimc.SatanicLib.messages;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.Utils;

import java.util.List;
import java.util.Map;

public class Message implements MessageInterface{
    String message;
    Player player;
    Map<String,String> placeholders;
    public Message(Player player, String text, Map<String, String> placeholders) {
        if (placeholders != null){
            for (Map.Entry<String, String> s : placeholders.entrySet()) {
                text = text.replaceAll(s.getKey(),s.getValue());
            }
        }
        text = PlaceholderAPI.setPlaceholders(player, text);
        text = Utils.translateHex(text);
        this.player = player;
        this.message = text;
    }

    public Message(List<Object> objects) //expect List.of(player, text, Map<String,String> placeholders)
    {
        for (Object obj : objects) {
            //todo
//            switch (obj.getClass().getSimpleName()){
//                case "Player":
//                    this.player = (Player) obj;
//                case "String":
//                    this.javaScriptRequirement = (String) obj;
//                    break;
//                case "Message":
//                    this.denyMessage = (Message) obj;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + obj);
//            }
            if (obj instanceof Player) {
                this.player = (Player) obj;
            } else if (obj instanceof String) {
                this.message = (String) obj;
            } else if (obj instanceof Map<?,?>) {
                this.placeholders = (Map<String, String>) obj;
            }
        }
    }
    @Override
    public void send() {
        this.player.sendMessage(message);
        this.message=null;
        this.player=null;
    }
}
