package org.mrdarkimc.SatanicLib;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHandler {
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

public void setupVault() {
    if (!setupEconomy() ) {
        SatanicLib.getPlugin().getServer().getLogger().severe("[SatanicLib] You ran plugin that requires Vault.");
        SatanicLib.getPlugin().getServer().getLogger().severe("[SatanicLib] Make sure you have Vault installed and has Vault as an dependency in SatanicLib's plugin");
        SatanicLib.getPlugin().getServer().getPluginManager().disablePlugin(SatanicLib.getPlugin());
        return;
    }
    setupPermissions();
    setupChat();
}

    private boolean setupEconomy() {
        if (SatanicLib.getPlugin().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = SatanicLib.getPlugin().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = SatanicLib.getPlugin().getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = SatanicLib.getPlugin().getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
}
