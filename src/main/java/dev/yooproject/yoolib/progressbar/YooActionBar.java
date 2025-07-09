package dev.yooproject.yoolib.progressbar;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

public interface YooActionBar {

    YooActionBar setMessage(String message);
    String getMessage();

    YooActionBar setColor(String color);
    String getColor();

    YooActionBar setVisible(boolean visible);
    boolean isVisible();

    YooActionBar addPlayer(Player player);
    YooActionBar removePlayer(Player player);
    YooActionBar addPlayers(Collection<? extends Player> players);
    YooActionBar removePlayers(Collection<? extends Player> players);
    YooActionBar clearPlayers();
    Set<Player> getPlayers();

    YooActionBar showFor(Player player, long duration, TimeUnit unit);
    YooActionBar showForAll(long duration, TimeUnit unit);
    YooActionBar hideAfter(long duration, TimeUnit unit);

    YooActionBar onShow(Consumer<Player> listener);
    YooActionBar onHide(Consumer<Player> listener);
    YooActionBar onMessageChange(BiConsumer<Player, String> listener);

    YooActionBar addGroup(String groupName, Collection<? extends Player> players);
    YooActionBar removeGroup(String groupName);
    YooActionBar showToGroup(String groupName);
    YooActionBar hideFromGroup(String groupName);
    YooActionBar filterPlayers(Predicate<Player> filter);

    YooActionBar async();
    YooActionBar sync();

    YooActionBar setTag(String key, Object value);
    Object getTag(String key);

    void remove();

    static YooActionBar create(String message) { throw new UnsupportedOperationException(); }
}
