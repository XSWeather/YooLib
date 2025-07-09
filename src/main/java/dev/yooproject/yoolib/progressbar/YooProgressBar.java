package dev.yooproject.yoolib.progressbar;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

public interface YooProgressBar {

    YooProgressBar setTitle(String title);
    YooProgressBar setProgress(double progress); // 0.0 - 1.0
    YooProgressBar setColor(String color);
    YooProgressBar setVisible(boolean visible);

    String getTitle();
    double getProgress();
    String getColor();
    boolean isVisible();

    YooProgressBar addPlayer(Player player);
    YooProgressBar removePlayer(Player player);
    YooProgressBar addPlayers(Collection<? extends Player> players);
    YooProgressBar removePlayers(Collection<? extends Player> players);
    YooProgressBar clearPlayers();
    Set<Player> getPlayers();

    YooProgressBar showFor(Player player, long duration, TimeUnit unit);
    YooProgressBar showForAll(long duration, TimeUnit unit);
    YooProgressBar hideAfter(long duration, TimeUnit unit);

    YooProgressBar onShow(Consumer<Player> listener);
    YooProgressBar onHide(Consumer<Player> listener);
    YooProgressBar onProgressChange(BiConsumer<Player, Double> listener);
    YooProgressBar onTitleChange(BiConsumer<Player, String> listener);

    YooProgressBar addGroup(String groupName, Collection<? extends Player> players);
    YooProgressBar removeGroup(String groupName);
    YooProgressBar showToGroup(String groupName);
    YooProgressBar hideFromGroup(String groupName);
    YooProgressBar filterPlayers(Predicate<Player> filter);

    YooProgressBar async();
    YooProgressBar sync();

    YooProgressBar setTag(String key, Object value);
    Object getTag(String key);

    void remove();

    static YooProgressBar create(String title) { throw new UnsupportedOperationException(); }
}
