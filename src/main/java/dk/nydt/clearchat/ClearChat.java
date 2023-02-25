package dk.nydt.clearchat;

import dk.nydt.clearchat.utils.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ClearChat extends JavaPlugin {
    public static ClearChat instance;
    private static PluginManager pluginManager;
    public static Config config;
    public static FileConfiguration configYML;
    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();
        instance = this;


        //CONFIG.YML
        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();

        //REGISTER COMMANDS
        getCommand("ClearChat").setExecutor(new dk.nydt.clearchat.commands.ClearChat());

    }

    @Override
    public void onDisable() {
        config.saveConfig();
    }
}
