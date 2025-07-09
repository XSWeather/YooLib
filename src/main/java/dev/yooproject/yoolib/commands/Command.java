package dev.yooproject.yoolib.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class Command {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUsage();
    public abstract String getPermission();
    public abstract void execute(CommandSender sender, String[] args) throws CommandArgumentException;
}