package org.mrdarkimc.SatanicLib.currency;

import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.entity.Player;
import org.mrdarkimc.SatanicLib.currency.interfaces.Currency;

public class PlayerPoints implements Currency {
    PlayerPointsAPI api;

    public PlayerPoints() {
        this.api = org.black_ixx.playerpoints.PlayerPoints.getInstance().getAPI();
    }

    @Override
    public boolean hasAmountOfMoney(Player player, int amount) {
        return (api.look(player.getUniqueId()) >= amount);
    }

    @Override
    public int getBalance(Player player) {
        return api.look(player.getUniqueId());
    }

    @Override
    public void setBalance(Player player, int amount) {
        api.set(player.getUniqueId(),amount);
    }

    @Override
    public void addMoney(Player player, int amount) {
    api.give(player.getUniqueId(),amount);
    }

    @Override
    public void reduceMoney(Player player, int amount) {
    api.take(player.getUniqueId(),amount);
    }
}
