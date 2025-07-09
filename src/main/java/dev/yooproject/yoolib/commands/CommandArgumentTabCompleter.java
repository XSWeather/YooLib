package dev.yooproject.yoolib.commands;

import org.bukkit.command.CommandSender;
import java.util.List;

public interface CommandArgumentTabCompleter {
    List<String> onTabComplete(CommandSender sender, String[] args);
}