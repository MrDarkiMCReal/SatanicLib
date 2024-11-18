package org.mrdarkimc.SatanicLib.configsetups;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MerchantRecipe;
import org.mrdarkimc.SatanicLib.ItemStackUtils.StackUtils;
import org.mrdarkimc.SatanicLib.SatanicLib;
import org.mrdarkimc.SatanicLib.chat.ChatTag;
import org.mrdarkimc.SatanicLib.chat.TagList;
import org.mrdarkimc.SatanicLib.chat.extras.ClickEvents;
import org.mrdarkimc.SatanicLib.chat.extras.ShowText;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatExtra;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatTag;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Configs {
    private String configName;
    private File configFile;
    private FileConfiguration config;


    public Configs(String configName) {
        this.configName = configName;
        loadConfiguration();
    }
    private void loadConfiguration(){
        this.configFile = new File(SatanicLib.getPlugin().getDataFolder(), configName + ".yml");
        if (!this.configFile.exists()) {
            SatanicLib.getPlugin().saveResource(configName + ".yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void loadConfig(){
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }
    public List<MerchantRecipe> getRecipeList(String path) {
        List<MerchantRecipe> recipes = new ArrayList<>();
        List<Map<?, ?>> trades = this.config.getMapList(path);


        for (Map<?, ?> trade : trades) {
            String result = (String) trade.get("item");
            List<String> ingridients = (List<String>) trade.get("recipe");
            String[] arrayofRecipeItems = ingridients.toArray(new String[2]);

            MerchantRecipe recipeTemp = new MerchantRecipe(StackUtils.stringToItemStack(result,Map.of()), 1);
            for (String recipe : arrayofRecipeItems) {
                recipeTemp.addIngredient(StackUtils.stringToItemStack(recipe,Map.of()));
            }
            recipes.add(recipeTemp);
        }
        return recipes;
    }

    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public FileConfiguration get() {
        if (config == null) {
            loadConfig();
        }
        return this.config;
    }

    public List<IChatTag> getChatTagList(Player player, String path, Map<String, String> localPlaceholders) {

        Set<String> keys = get().getConfigurationSection(path).getKeys(false);

        List<IChatTag> chatTagList = new ArrayList<>();
        for (String key : keys) {
        chatTagList.add(getChatTag(player,path + "." + key,localPlaceholders));
        }
        return chatTagList;
    }
    public TagList getTagList(Player player, String path, Map<String, String> localPlaceholders){
    return new TagList(player, path, localPlaceholders, this); //todo memory leak?
    }

    public IChatTag getChatTag(Player player, String path, Map<String, String> localPlaceholders) {


        String text = null;


        Set<String> properties = get().getConfigurationSection(path).getKeys(false);
        List<IChatExtra> list = new ArrayList<>();
        for (String property : properties) {
            for (String placeholder : localPlaceholders.keySet()) {
                property = property.replace(placeholder, localPlaceholders.get(placeholder));
            }
            switch (property) {
                case "text":
                    String textFromConfig = get().getString(path + ".text");
                    text = PlaceholderAPI.setPlaceholders(player, textFromConfig);
                    break;
                case "hover-event":
                    List<String> hoverMessage = get().getStringList(path + ".hover-event.text");
                    hoverMessage.replaceAll((line) -> PlaceholderAPI.setPlaceholders(player, line));
                    list.add(new ShowText(hoverMessage));
                    break;
                case "click-event":
                    ConfigurationSection section = get().getConfigurationSection(path + ".click-event");
                    String action = section.getString("type");
                    String output = section.getString("output");
                    list.add(new ClickEvents.TypedEvent(ClickEvent.Action.valueOf(action),output));
                    break;
                default: //skip
                    break;
            }

        }

        return new ChatTag(player, text, list);
    }

    public String getKeyedText(String path) {
        return this.get().getString(path);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static class Defaults{
        public static Configs mainConfig;
        private static Configs itemSaverConfig;
        public static Configs tagsConfig;
        public static Configs itemSaverConfig(){
            if (itemSaverConfig != null){
                return itemSaverConfig;
            }
            throw new NoSuchElementException("[SatanicLib] itemSaverConfig does not set-up. Use Configs.Defaults.setupItemStackSaver() first.");
        }
        public static Configs setupConfig(){
            mainConfig = new Configs("config");
            return mainConfig;
        }
        public static Configs setupItemStackSaver(){
            itemSaverConfig = new Configs("savedItems");
            return itemSaverConfig;
        }
        public static Configs setupTags(){
            tagsConfig = new Configs("tags");
            return tagsConfig;
        }
    }
}
