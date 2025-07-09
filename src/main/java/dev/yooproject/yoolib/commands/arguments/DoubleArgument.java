package dev.yooproject.yoolib.commands.arguments;

import dev.yooproject.yoolib.commands.Argument;
import dev.yooproject.yoolib.commands.CommandArgumentException;

public class DoubleArgument implements Argument<Double> {
    private final String name;

    public DoubleArgument(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double parse(String input) throws CommandArgumentException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new CommandArgumentException("Argument '" + name + "' must be a decimal number!");
        }
    }
}
