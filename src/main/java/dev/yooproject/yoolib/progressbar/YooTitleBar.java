package dev.yooproject.yoolib.progressbar;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

public interface YooTitleBar {

    YooTitleBar setTitle(String title);
    YooTitleBar setSubtitle(String subtitle);
    YooTitleBar setFadeIn(int ticks);
    YooTitleBar setStay(int ticks);
    YooTitleBar setFadeOut(int ticks);

    String getTitle();
    String getSubtitle();
    int getFadeIn();
    int getStay();
    int getFadeOut();

    YooTitleBar setVisible(boolean visible);
    boolean isVisible();

    YooTitleBar addPlayer(Player player);
    YooTitleBar removePlayer(Player player);
    YooTitleBar addPlayers(Collection<? extends Player> players);
    YooTitleBar removePlayers(Collection<? extends Player> players);
    YooTitleBar clearPlayers();
    Set<Player> getPlayers();

    YooTitleBar showFor(Player player, long duration, TimeUnit unit);
    YooTitleBar showForAll(long duration, TimeUnit unit);
    YooTitleBar hideAfter(long duration, TimeUnit unit);

    YooTitleBar onShow(Consumer<Player> listener);
    YooTitleBar onHide(Consumer<Player> listener);
    YooTitleBar onTitleChange(BiConsumer<Player, String> listener);
    YooTitleBar onSubtitleChange(BiConsumer<Player, String> listener);

    YooTitleBar addGroup(String groupName, Collection<? extends Player> players);
    YooTitleBar removeGroup(String groupName);
    YooTitleBar showToGroup(String groupName);
    YooTitleBar hideFromGroup(String groupName);
    YooTitleBar filterPlayers(Predicate<Player> filter);

    YooTitleBar async();
    YooTitleBar sync();

    YooTitleBar setTag(String key, Object value);
    Object getTag(String key);

    void remove();

    static YooTitleBar create(String title, String subtitle) { throw new UnsupportedOperationException(); }
}
