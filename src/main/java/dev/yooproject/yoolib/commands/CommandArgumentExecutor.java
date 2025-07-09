package dev.yooproject.yoolib.commands;

import org.bukkit.command.CommandSender;

public interface CommandArgumentExecutor {
    void execute(CommandSender sender, String[] args) throws CommandArgumentException;
}