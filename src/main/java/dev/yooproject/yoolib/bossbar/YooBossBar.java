package dev.yooproject.yoolib.bossbar;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface YooBossBar {

    YooBossBar setTitle(String title);
    YooBossBar setProgress(double progress);
    YooBossBar setColor(BarColor color);
    YooBossBar setStyle(BarStyle style);
    YooBossBar setVisible(boolean visible);

    String getTitle();
    double getProgress();
    BarColor getColor();
    BarStyle getStyle();
    boolean isVisible();

    YooBossBar addPlayer(Player player);
    YooBossBar removePlayer(Player player);
    YooBossBar addPlayers(Collection<? extends Player> players);
    YooBossBar removePlayers(Collection<? extends Player> players);
    YooBossBar clearPlayers();
    Set<Player> getPlayers();

    YooBossBar showFor(Player player, long duration, TimeUnit unit);
    YooBossBar showForAll(long duration, TimeUnit unit);
    YooBossBar hideAfter(long duration, TimeUnit unit);

    YooBossBar onShow(Consumer<Player> listener);
    YooBossBar onHide(Consumer<Player> listener);
    YooBossBar onProgressChange(BiConsumer<Player, Double> listener);
    YooBossBar onTitleChange(BiConsumer<Player, String> listener);

    YooBossBar addGroup(String groupName, Collection<? extends Player> players);
    YooBossBar removeGroup(String groupName);
    YooBossBar showToGroup(String groupName);
    YooBossBar hideFromGroup(String groupName);
    YooBossBar filterPlayers(Predicate<Player> filter);

    YooBossBar async();
    YooBossBar sync();

    YooBossBar setTag(String key, Object value);
    Object getTag(String key);

    void remove();

    static YooBossBar create(String title, BarColor color, BarStyle style) { throw new UnsupportedOperationException(); }
    static YooBossBar of(String title) { throw new UnsupportedOperationException(); }
}
