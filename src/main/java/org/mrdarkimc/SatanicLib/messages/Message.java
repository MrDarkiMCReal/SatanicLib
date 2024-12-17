package org.mrdarkimc.SatanicLib.messages;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.Utils;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Message implements MessageInterface{
    String message;
    Player player;
    Map<String,String> placeholders;

    /**
     *
     * @param player can be null (except for send() method)
     * @param text
     * @param placeholders can be null
     */
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
        this.placeholders = placeholders;
    }
    public Message(String text, Map<String, String> placeholders) {
        if (placeholders != null){
            for (Map.Entry<String, String> s : placeholders.entrySet()) {
                text = text.replaceAll(s.getKey(),s.getValue());
            }
        }
        text = Utils.translateHex(text);
        this.message = text;
    }
    @Override
    public String getText(boolean parsePlaceholderAPI){
        return parsePlaceholderAPI ? PlaceholderAPI.setPlaceholders(player==null ? null : player,this.message) : this.message;
    }

    @Override
    public void broadcast() {
        if (player!=null) {
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(message));
            return;
        }
        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(PlaceholderAPI.setPlaceholders(p, message)));
    }

    @Override
    public void sendToPlayersWithPermission(String permission) {
        if (player!=null) {
            Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission(permission)).forEach(p -> p.sendMessage(message));
            return;
        }
        Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission(permission)).forEach(p -> p.sendMessage(PlaceholderAPI.setPlaceholders(p,message)));
    }

    @Override
    public void sendToOps(){
        if (player!=null) {
            Bukkit.getOperators().stream().filter(OfflinePlayer::isOnline).map(OfflinePlayer::getPlayer).forEach(p -> {
                p.sendMessage(message);
            });
            return;
        }
        Bukkit.getOperators().stream().filter(OfflinePlayer::isOnline).map(OfflinePlayer::getPlayer).forEach(p -> {
            p.sendMessage(PlaceholderAPI.setPlaceholders(p,message));
        });
    }

    /**
     *
     * Do not use that if you dont know what are you doing
     */
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
        if (player==null) {
            throw new NoSuchElementException("[SatanicLib] Message.send() didnt complete. Player is null \n" +
                    "Message is: \n" + message);
        }
        this.player.sendMessage(message);
        this.message=null;
        this.player=null;
    }
}
