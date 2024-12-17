import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.SatanicLib;
import org.mrdarkimc.SatanicLib.configsetups.Configs;
import org.mrdarkimc.SatanicLib.chat.ChatTag;
import org.mrdarkimc.SatanicLib.chat.ChatUtils;
import org.mrdarkimc.SatanicLib.chat.SimpleMessage;
import org.mrdarkimc.SatanicLib.chat.extras.ClickEvents;
import org.mrdarkimc.SatanicLib.chat.extras.ShowText;
import org.mrdarkimc.SatanicLib.loops.StartStopAsyncTask;
import org.mrdarkimc.SatanicLib.loops.TaskExample;
import org.mrdarkimc.SatanicLib.messages.KeyedMessage;
import org.mrdarkimc.SatanicLib.messages.Message;
import org.mrdarkimc.SatanicLib.prefixHandler.prefixes.*;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass extends JavaPlugin {
    //    public enum PrefixType{
//        command,console,text,requrement;
//    }

    public static final Map<String, Function<List<Object>, Object>> prefixMap = new HashMap<>();

    //префикс - Функция (лист аргументов, обьект)
    static {
        prefixMap.put("[command]", PlayerCommand::new);
        prefixMap.put("[console]", ConsoleCommand::new);
        prefixMap.put("[text]", Message::new);
        prefixMap.put("[requirement]", SimpleRequirement::new); //expect: List.of(Player, javaScript, Message) or List.of(Player, javaScript) //todo
        prefixMap.put("[keyedText]", KeyedMessage::new);
        prefixMap.put("[item]", Item::new);
        prefixMap.put("[saved]", SavedItem::new);
        prefixMap.put("[tag]", Tag::new);
        //prefixMap.put("[hasItem]", )
    }

    public void get(String... arguments) {

    }

    public void Test() {
        saveDefaultConfig();
        SatanicLib.setupLib(this);
        Configs.Defaults.setupItemStackSaver();
        Configs main = new Configs("config");
        new StartStopAsyncTask(new TaskExample(5,5,5));
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            new ChatTag(onlinePlayer,"trweqe",
                    new ShowText("123"),
                    new ClickEvents.RunCommand("say 123"));
            TextComponent component = ChatUtils.stackChatTags(main.getChatTagList(onlinePlayer, "tags", Collections.emptyMap()));
            onlinePlayer.spigot().sendMessage(component);
            main.getTagList(onlinePlayer,"tags",Collections.emptyMap()).send();
        }
        List<String> actionList = new ArrayList<>();

        //TestClass.handlePrefix(player, new KeyedMessage(player,));


    }
    public void testSimpleMessage(Player player){
        SimpleMessage.ToPlayer.sendListMessages(player,List.of("g"),Map.of("key","value"));
    }


    public static void handlePrefix(Player player, String text, Map<String, String> placeholders) {
        Pattern pattern = Pattern.compile("^\\[[^\\]]+\\]");
        Matcher matcher = pattern.matcher(text);
        for (Map.Entry<String, String> stringStringEntry : placeholders.entrySet()) {
            text = text.replace(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        text = PlaceholderAPI.setPlaceholders(player,text);
        if (matcher.find()){
            text = text.substring(matcher.group().length()+1); //+1 with space
            switch (matcher.group()){
                case "[console]":
                    //todo создать отдельные хандлеры для команд, звуков, реквайрментов, и т.д
                    new ConsoleCommand(text);
                    break;
                case "[command]":
                    //todo создать отдельные хандлеры для команд, звуков, реквайрментов, и т.д
                    new Command(player,text);
                    break;
            }
        }

    }
}
