package dev.yooproject.yoolib.commands;

public abstract class CommandArgument implements Argument {
    public abstract String getName();
    public abstract String getDescription();
    public abstract boolean isRequired();
}