package dev.yooproject.yoolib.commands;

public class Argument {
    private final String name;
    private final boolean required;

    public Argument(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public String getName() { return name; }
    public boolean isRequired() { return required; }
}