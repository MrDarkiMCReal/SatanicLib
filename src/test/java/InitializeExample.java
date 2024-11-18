import org.bukkit.plugin.java.JavaPlugin;
import org.mrdarkimc.SatanicLib.SatanicLib;
import org.mrdarkimc.SatanicLib.configsetups.Configs;

public class InitializeExample extends JavaPlugin {
    public static Configs mainConfig;
    public static Configs saver;
    public static Configs tags;
    public static Configs myCustomConfig;
    @Override
    public void onEnable(){
        SatanicLib.setupLib(this); //register your plugin
        mainConfig = Configs.Defaults.setupConfig();
        boolean saveditems = mainConfig.get().getBoolean("settings.enableSavedItems");
        if (saveditems) {
            saver = Configs.Defaults.setupItemStackSaver();
        }

        boolean tag = mainConfig.get().getBoolean("settings.enableTags");
        if (tag) {
            tags = Configs.Defaults.setupTags();
        }
        myCustomConfig = new Configs("custom_config");

    }
}
