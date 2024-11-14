package org.mrdarkimc.SatanicLib.Scripts;

import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.prefixHandler.prefixes.SimpleRequirement;

import java.util.List;
import java.util.Map;

public class Scripts {
    public static boolean handleRequirement(Expression expr){
        return calculations(expr.getRequiredValue(), expr.getSign(), expr.getCurrentValue());
    }
    //todo использовать метод //setComments(String path, List<String> comments)
    //"%vault_eco_balance% >= 2000"
    public static boolean calculations (String placeholder, String expression, String value) {
        switch(expression) {
            case ">":
                int placeholderInt = Integer.parseInt(placeholder);
                int valueInt = Integer.parseInt(value);
                return placeholderInt > valueInt;
            case ">=":
                placeholderInt = Integer.parseInt(placeholder);
                valueInt = Integer.parseInt(value);
                return placeholderInt >= valueInt;
            case "<":
                placeholderInt = Integer.parseInt(placeholder);
                valueInt = Integer.parseInt(value);
                return placeholderInt < valueInt;
            case "<=":
                placeholderInt = Integer.parseInt(placeholder);
                valueInt = Integer.parseInt(value);
                return placeholderInt <= valueInt;
            case "==":
                boolean placeholderBoolean = Boolean.parseBoolean(placeholder);
                boolean valueBoolean = Boolean.parseBoolean(value);
                return placeholderBoolean == valueBoolean;
            case "===":
                //placeholder == Private, value == Major
                return (placeholder.equalsIgnoreCase(value));
            default:
                throw new IllegalArgumentException("[SatanicLib] Error in REQUIREMENTS. Expression cannot be calculated. Allowed only: \n" +
                        " >= - more or equals amount(numbers) \n" +
                        " >  - more then amount(numbers) \n" +
                        " <= - less or equals amount(numbers) \n" +
                        " < - less than amount(numbers) \n" +
                        " == - equals(booleans / true or false)  \n" +
                        " === - equals (String for e.g. rank: 'Private === private' will pass requirement or 'Private === Major' will not pass requirement); case is ignored.");
        }
    }
    //input List<Map<?, ?>> requirementList = config.get().getMapList("teleport-requirements");
    public boolean havePassRequirementsDetailed(Player player, List<Map<?, ?>> requirementList){
        //"%vault_eco_balance% >= 2000"
        boolean passRequirements = false;
        for (Map<?, ?> map : requirementList) {
            String expression;
            String denyMessage;
            switch (map.size()) {
                case 1:
                    expression = (String) map.get("expression");
                    if (SimpleRequirement.result(new Expression(player, expression))){
                        passRequirements = true;
                    }
                    break;
                case 2:
                    expression = (String) map.get("expression");
                    denyMessage = (String) map.get("denyMessage");
                    if (new Requirement(player, new Expression(player,expression), denyMessage).resultAndMessage()){
                        passRequirements = true;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("[SatanicLib] requirements set up incorrectly. Check plugin's wiki or parse with online yaml parser");
            }
        }
        return passRequirements;
    }

}
