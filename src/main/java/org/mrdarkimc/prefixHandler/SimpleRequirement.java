package org.mrdarkimc.prefixHandler;

import org.bukkit.entity.Player;
import org.mrdarkimc.Scripts.Expression;
import org.mrdarkimc.Scripts.Scripts;
import org.mrdarkimc.messages.Message;
import org.mrdarkimc.messages.MessageInterface;

import java.util.List;
import java.util.Map;

public class SimpleRequirement {

    public static boolean result(Expression expression) {
        return Scripts.handleRequirement(expression);
    }







////todo
    public SimpleRequirement(List<Object> objects) {

    }
    }
//        //player
//        //requirement
//        //message
//
//        for (Object obj : objects) {
//            //todo
////            switch (obj.getClass().getSimpleName()){
////                case "Player":
////                    this.player = (Player) obj;
////                case "String":
////                    this.javaScriptRequirement = (String) obj;
////                    break;
////                case "Message":
////                    this.denyMessage = (Message) obj;
////                default:
////                    throw new IllegalStateException("Unexpected value: " + obj);
////            }
//            if (obj instanceof Player) {
//                this.player = (Player) obj;
//            } else if (obj instanceof String) {
//                this.expression = (String) obj;
////            } else if (obj instanceof Message) {
////                this.denyMessage = (Message) obj;
////            }
//            }
//
//        }
//    }

