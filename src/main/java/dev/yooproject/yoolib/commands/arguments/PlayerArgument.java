package dev.yooproject.yoolib.commands.arguments;

import dev.yooproject.yoolib.commands.Argument;
import dev.yooproject.yoolib.commands.CommandArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerArgument implements Argument<Player> {
    private final String name;

    public PlayerArgument(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Player parse(String input) throws CommandArgumentException {
        Player player = Bukkit.getPlayer(input);
        if (player == null) {
            throw new CommandArgumentException("Player '" + input + "' not found!");
        }
        return player;
    }
}
