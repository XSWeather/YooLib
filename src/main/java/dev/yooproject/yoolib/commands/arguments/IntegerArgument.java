package dev.yooproject.yoolib.commands.arguments;

import dev.yooproject.yoolib.commands.Argument;
import dev.yooproject.yoolib.commands.CommandArgumentException;

public class IntegerArgument implements Argument<Integer> {
    private final String name;

    public IntegerArgument(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer parse(String input) throws CommandArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CommandArgumentException("Argument '" + name + "' must be a number!");
        }
    }
}
