package org.mrdarkimc.SatanicLib.messages;


public interface MessageInterface {
    void send();
    void sendToOps();
    String getText(boolean parsePlaceholderAPI);
    void broadcast();
    void sendToPlayersWithPermission(String permission);
}
