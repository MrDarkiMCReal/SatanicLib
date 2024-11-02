package org.mrdarkimc.currency.interfaces;

import org.bukkit.entity.Player;

public interface Currency {
    boolean hasAmountOfMoney(Player player, int amount);
    int getBalance(Player player);
    void setBalance(Player player, int amount);
    void addMoney(Player player, int amount);
    void reduceMoney(Player player, int amount);
}
