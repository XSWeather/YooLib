package dev.yooproject.yoolib.commands;

import org.bukkit.command.CommandSender;

public abstract class AbstractCommand {
    public abstract boolean execute(CommandSender sender, String[] args);
}