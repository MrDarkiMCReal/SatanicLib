package org.mrdarkimc.SatanicLib.currency;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.currency.interfaces.Currency;


public class Vault implements Currency {
    private final JavaPlugin plugin;
    public Vault(JavaPlugin plugin) {
        this.plugin = plugin;
        setupEconomy();
    }

    @Override
    public boolean hasAmountOfMoney(Player player, int amount) {
        OfflinePlayer player2 = Bukkit.getServer().getOfflinePlayer(player.getUniqueId());
        return econ.has(player2,amount);
    }

    @Override
    public int getBalance(Player player) {
        OfflinePlayer player2 = Bukkit.getServer().getOfflinePlayer(player.getUniqueId());
        return (int) econ.getBalance(player2);
    }

    @Override
    public void setBalance(Player player, int amount) {
        OfflinePlayer player2 = Bukkit.getServer().getOfflinePlayer(player.getUniqueId());
    econ.withdrawPlayer(player2,getBalance(player));
    }

    @Override
    public void addMoney(Player player, int amount) {
        OfflinePlayer player2 = Bukkit.getServer().getOfflinePlayer(player.getUniqueId());
        econ.depositPlayer(player2,amount);
    }

    @Override
    public void reduceMoney(Player player, int amount) {
        OfflinePlayer player2 = Bukkit.getServer().getOfflinePlayer(player.getUniqueId());
        econ.withdrawPlayer(player2,amount);
    }
    private static Economy econ = null;
    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
