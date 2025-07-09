package dev.yooproject.yoolib.commands.arguments;

import dev.yooproject.yoolib.commands.Argument;
import dev.yooproject.yoolib.commands.CommandArgumentException;

public class BooleanArgument implements Argument<Boolean> {
    private final String name;

    public BooleanArgument(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean parse(String input) throws CommandArgumentException {
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("yes")) return true;
        if (input.equalsIgnoreCase("false") || input.equalsIgnoreCase("no")) return false;
        throw new CommandArgumentException("Argument '" + name + "' must be true/false or yes/no!");
    }
}
