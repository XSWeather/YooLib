package dev.yooproject.yoolib.bossbar;

import org.bukkit.entity.Player;

public interface YooBossBarListener {
    void onShow(Player player, YooBossBar bar);
    void onHide(Player player, YooBossBar bar);
    void onProgressChange(Player player, YooBossBar bar, double progress);
    void onTitleChange(Player player, YooBossBar bar, String title);
}
