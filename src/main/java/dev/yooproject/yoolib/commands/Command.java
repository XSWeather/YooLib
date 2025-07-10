package dev.yooproject.yoolib.commands;

import java.util.*;

public class Command extends AbstractCommand {
    private final String name;
    private final List<String> aliases;
    private final String permission;
    private final String description;
    private final Map<List<String>, CommandHandler> subCommands = new HashMap<>();

    public Command(String name, List<String> aliases, String permission, String description) {
        this.name = name;
        this.aliases = aliases;
        this.permission = permission;
        this.description = description;
    }

    public void addSubCommand(List<String> args, CommandHandler handler) {
        subCommands.put(args, handler);
    }

    @Override
    public boolean execute(org.bukkit.command.CommandSender sender, String[] args) {
        for (Map.Entry<List<String>, CommandHandler> entry : subCommands.entrySet()) {
            List<String> key = entry.getKey();
            if (args.length >= key.size()) {
                boolean match = true;
                for (int i = 0; i < key.size(); i++) {
                    if (!args[i].equalsIgnoreCase(key.get(i))) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return entry.getValue().onCommand(sender, Arrays.copyOfRange(args, key.size(), args.length));
                }
            }
        }
        return false;
    }

    public String getName() { return name; }
    public List<String> getAliases() { return aliases; }
    public String getPermission() { return permission; }
    public String getDescription() { return description; }
}