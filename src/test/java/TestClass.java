import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.loops.StartStopAsyncTask;
import org.mrdarkimc.SatanicLib.loops.TaskExample;
import org.mrdarkimc.SatanicLib.messages.KeyedMessage;
import org.mrdarkimc.SatanicLib.messages.Message;
import org.mrdarkimc.SatanicLib.prefixHandler.ConsoleCommand;
import org.mrdarkimc.SatanicLib.prefixHandler.Item;
import org.mrdarkimc.SatanicLib.prefixHandler.PlayerCommand;
import org.mrdarkimc.SatanicLib.prefixHandler.SavedItem;

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
        new StartStopAsyncTask(new TaskExample(5,5,5),this);


    }

    public void handlePrefix(String text) {

    }
}
