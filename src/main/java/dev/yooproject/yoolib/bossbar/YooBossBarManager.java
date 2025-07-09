package dev.yooproject.yoolib.bossbar;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface YooBossBarManager {
    YooBossBar createBar(String title);
    YooBossBar getBar(String id);
    void removeBar(String id);
    Collection<YooBossBar> getAllBars();
    void showAll(Player player);
    void hideAll(Player player);
}
