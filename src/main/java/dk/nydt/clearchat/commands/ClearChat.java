package dk.nydt.clearchat.commands;

import dk.nydt.clearchat.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static dk.nydt.clearchat.ClearChat.config;
import static dk.nydt.clearchat.ClearChat.configYML;

public class ClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission(configYML.getString("permissions.reload")) || p.hasPermission(configYML.getString("permissions.admin"))) {
                    config.reloadConfig();
                    configYML = config.getConfig();
                    List<String> beskeder = configYML.getStringList("messages.reload-success");
                    for (String besked : beskeder) {
                        p.sendMessage(Chat.getColored(besked));
                    }
                } else {
                    List<String> beskeder = configYML.getStringList("messages.reload-failure");
                    for (String besked : beskeder) {
                        p.sendMessage(Chat.getColored(besked));
                    }
                }
            }
            return false;
        }
        if (p.hasPermission(configYML.getString("permissions.use")) || p.hasPermission(configYML.getString("permissions.admin"))) {
            for (int i = 0; i < 500; i++) {
                Bukkit.broadcastMessage("\n");
            }
            List<String> beskeder = configYML.getStringList("messages.clear-success");
            for (String besked : beskeder) {
                besked = besked.replace("%player%", p.getName());
                Bukkit.broadcastMessage(Chat.getColored(besked));
            }
            return true;
        } else {
            List<String> beskeder = configYML.getStringList("messages.clear-failure");
            for (String besked : beskeder) {;
                p.sendMessage(Chat.getColored(besked));
            }
        }
        return false;
    }
}
