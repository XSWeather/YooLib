package dev.yooproject.yoolib.commands;

public interface Argument<T> {
    String getName();
    T parse(String input) throws CommandArgumentException;
}