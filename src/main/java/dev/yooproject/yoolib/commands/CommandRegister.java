package dev.yooproject.yoolib.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class CommandRegister {
    private final JavaPlugin plugin;
    private final CommandMap commandMap;

    public CommandRegister(JavaPlugin plugin) {
        this.plugin = plugin;
        this.commandMap = getCommandMap();
    }

    private CommandMap getCommandMap() {
        try {
            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            return (CommandMap) f.get(Bukkit.getServer());
        } catch (Exception e) {
            throw new RuntimeException("Cannot get CommandMap", e);
        }
    }

    public void registerCommand(Class<?> clazz) {
        try {
            if (clazz.isAnnotationPresent(CommandInfo.class)) {
                CommandInfo info = clazz.getAnnotation(CommandInfo.class);
                Command command = new Command(
                        info.name(),
                        Arrays.asList(info.aliases()),
                        info.permission(),
                        info.description()
                );
                Object instance = clazz.getDeclaredConstructor().newInstance();
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(CommandExecutor.class)) {
                        CommandExecutor exec = method.getAnnotation(CommandExecutor.class);
                        List<String> argsList = Arrays.asList(exec.args());
                        method.setAccessible(true);
                        command.addSubCommand(argsList, (sender, args) -> {
                            try {
                                return (boolean) method.invoke(instance, sender, args);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return false;
                            }
                        });
                    }
                }
                BukkitCommandWrapper wrapper = new BukkitCommandWrapper(command);
                commandMap.register(plugin.getName(), wrapper);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class BukkitCommandWrapper extends org.bukkit.command.Command {
        private final Command command;

        protected BukkitCommandWrapper(Command command) {
            super(command.getName(), command.getDescription(), "", command.getAliases());
            this.command = command;
        }

        @Override
        public boolean execute(CommandSender sender, String label, String[] args) {
            if (!command.getPermission().isEmpty() && !sender.hasPermission(command.getPermission())) {
                return true;
            }
            return true;
        }
    }
}