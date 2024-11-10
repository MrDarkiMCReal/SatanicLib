import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.Configs;
import org.mrdarkimc.Scripts.Expression;
import org.mrdarkimc.Scripts.Requirement;
import org.mrdarkimc.messages.KeyedMessage;
import org.mrdarkimc.messages.Message;
import org.mrdarkimc.prefixHandler.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
        //prefixMap.put("[requirement]", SimpleRequirement::new); //expect: List.of(Player, javaScript, Message) or List.of(Player, javaScript) //todo
        prefixMap.put("[keyedText]", KeyedMessage::new);
        prefixMap.put("[item]", Item::new);
        prefixMap.put("[saved]", SavedItem::new);
        //prefixMap.put("[hasItem]", )
    }

    public void get(String... arguments) {

    }

    public void Test() {
        String lore = "penis \n penis2 \n penis 3";


    }

    public void handlePrefix(String text) {

    }
}
