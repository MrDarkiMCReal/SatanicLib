package org.mrdarkimc.Scripts;


import org.bukkit.entity.Player;
import org.mrdarkimc.messages.Message;
import org.mrdarkimc.messages.MessageInterface;

import java.util.List;
import java.util.Map;

public class Requirement {
    MessageInterface message;
    boolean result;

    //Делает проверку и отправляет сообщение если оно не Null
    public Requirement(Player player,Expression expression, String denyMessage) {
        this.message = new Message(List.of(player, denyMessage, Map.of("{current}", expression.getCurrentValue(),"{required}",expression.getRequiredValue())));
        this.result = Scripts.handleRequirement(expression);
    }


    public boolean resultAndMessage(){
        boolean result = this.result;
        if (!result){
           this.message.send();
        }
        return result;
    }

}
