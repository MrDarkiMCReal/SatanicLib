package org.mrdarkimc.SatanicLib.messages;

import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

import java.util.List;
import java.util.Map;

public class KeyedMessage implements MessageInterface {

    /**
     * This one takes string list from Configs.Defaults.mainConfig
     * <p>
     * Use Configs.Defaults.setupConfig() so it will work
     * <p>
     *     Player can be null (only for broadcast, ops, and getText method)
     * <p>
     * Usage example:
     * <p>
     * new KeyedMessage(player,"messages.reach-maximum-homes,Map.of("{homesCount}",homes.getHomes())")
     * <p>
     *     Format of config message you can see in example.yml in resources folder.
     *     <p>
     *     <a href="https://github.com/MrDarkiMCReal/SatanicLib/wiki/KeyedMessage">or see it on github page</a>
     */

     Message message;
    boolean canSend;
    public KeyedMessage(Player player, String key, Map<String, String> placeholders) {
        Object value = Configs.Defaults.mainConfig.get().get(key + ".text");
        canSend = Configs.Defaults.mainConfig.get().getBoolean(key + ".enable",true);
        if (value instanceof String text){
            this.message = new Message(player, text, placeholders);
            return;
        }
        if(value instanceof List){
            List<String> list = (List<String>) value;
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));

                if (i < list.size() - 1) {
                    builder.append("\n");
                }
            }
            this.message = new Message(player, builder.toString(), placeholders);
            return;
        }
        throw new IllegalArgumentException("[SatanicLib] Key: " + key + " does not exist or wrong configurated. \n" +
                "Please use instructions: https://github.com/MrDarkiMCReal/SatanicLib/wiki/KeyedMessage");


    }


    public KeyedMessage(List<Object> objects) {
    }

    @Override
    public void send() {
        if (canSend) {
            message.send();
        }
    }

    @Override
    public void sendToOps() {
        if (canSend) {
            message.sendToOps();
        }
    }

    @Override
    public String getText(boolean parsePlaceholderAPI) {
        return message.getText(parsePlaceholderAPI);
    }

    @Override
    public void broadcast() {
        if (canSend){
            message.broadcast();
        }
    }

    @Override
    public void sendToPlayersWithPermission(String permission) {
        message.sendToPlayersWithPermission(permission);
    }
}
