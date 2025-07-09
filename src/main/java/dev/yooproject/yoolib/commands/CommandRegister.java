package dev.yooproject.yoolib.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CommandRegister {
    private static CommandMap commandMap;
    private static final Map<String, Command> commands = new HashMap<>();

    public static void registerCommand(JavaPlugin plugin, Command command) {
        try {
            if (commandMap == null) {
                Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                commandMap = (CommandMap) f.get(Bukkit.getServer());
            }

            BukkitCommand bukkitCommand = new BukkitCommand(command.getName()) {
                @Override
                public boolean execute(CommandSender sender, String label, String[] args) {
                    try {
                        command.execute(sender, args);
                    } catch (CommandArgumentException e) {
                        sender.sendMessage(e.getMessage());
                    }
                    return true;
                }
            };
            bukkitCommand.setDescription(command.getDescription());
            bukkitCommand.setUsage(command.getUsage());
            bukkitCommand.setPermission(command.getPermission());

            commandMap.register(plugin.getDescription().getName(), bukkitCommand);
            commands.put(command.getName(), command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Command getCommand(String name) {
        return commands.get(name);
    }
}