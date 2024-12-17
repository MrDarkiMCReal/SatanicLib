package org.mrdarkimc.SatanicLib.prefixHandler.prefixes;

import org.bukkit.Bukkit;

import java.util.List;

public class ConsoleCommand {

    public ConsoleCommand(List<Object> objects) {
    }
    public ConsoleCommand(String command){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
    }
}
