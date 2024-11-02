package org.mrdarkimc.Scripts;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class Expression {
    String requiredValue;
    String equalitySign;
    String currentValue;

    public Expression(Player player, String expression) {
        //input "[expression] %vault_eco_balance% >= 2000"
        String[] keys = expression.split(" ");
        this.requiredValue = PlaceholderAPI.setPlaceholders(player,keys[1]);
        this.equalitySign = keys[2];
        this.currentValue = PlaceholderAPI.setPlaceholders(player,keys[3]);
    }

    public String getRequiredValue() {
        return requiredValue;
    }

    public String getSign() {
        return equalitySign;
    }

    public String getCurrentValue() {
        return currentValue;
    }
}
