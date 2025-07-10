package dev.yooproject.yoolib.commands;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface CommandHandler {
    boolean onCommand(CommandSender sender, String[] args);
}