package org.mrdarkimc.SatanicLib.playerformatting;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class FormatPlayer extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "FormatPlayer";
    }

    @Override
    public @NotNull String getAuthor() {
        return "MrDarkiMC";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0-SNAPSHOT";
    }

    public FormatPlayer() {
    }
    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String identifier) {
        //usage: %dFormat_<decimalFormat>_<dataType>_<originalPlaceholderWithoutBrackets>%
        //usage: %dFormat_#,###_int_statistic_deaths%
        //supported deciminal = any that java.text.DecimalFormat supports
        //supported dataType = int long
        //identifier will be "#,###_int_statistic_deaths" for ex


        //%FormatPlayer_PlayerName%
        String[] args = identifier.split("_");
        if (args.length==1){

        }
        if (args.length!=3)
            return "Expected3ArgsNot" + args.length;
        String playerName = identifier.substring(0,identifier.indexOf("_"));
        Player player = Bukkit.getPlayer(playerName);
        if (player !=null){
            //do stuff
        }else{
            playerName = "%" + playerName + "%"; //output %statistic_death%
            player = Bukkit.getPlayer(PlaceholderAPI.setPlaceholders(null,playerName));
        }
        String type = identifier.substring(identifier.indexOf("_")+1,identifier.indexOf("_",identifier.indexOf("_")+1));
        String originPH = "%" + identifier.substring(playerName.length() + type.length() + 2) + "%"; //output %statistic_death%
        DecimalFormat df = new DecimalFormat(playerName);

        String parsedValue = PlaceholderAPI.setPlaceholders(offlinePlayer, originPH);
        try {
            Long lng = Long.parseLong(parsedValue);
            return ((type.equalsIgnoreCase("int") | type.equalsIgnoreCase("long")) && type.equals("int")) ? df.format(Integer.parseInt(parsedValue)) : df.format(lng);
        } catch (NumberFormatException e) {
            return "NumFormatExc";
        }
    }
}
