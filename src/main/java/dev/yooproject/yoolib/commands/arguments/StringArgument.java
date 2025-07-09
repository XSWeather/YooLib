package dev.yooproject.yoolib.commands.arguments;

import dev.yooproject.yoolib.commands.Argument;

public class StringArgument implements Argument<String> {
    private final String name;

    public StringArgument(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String parse(String input) {
        return input;
    }
}
