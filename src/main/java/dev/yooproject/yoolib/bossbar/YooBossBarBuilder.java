package dev.yooproject.yoolib.bossbar;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface YooBossBarBuilder {
    YooBossBarBuilder title(String title);
    YooBossBarBuilder progress(double progress);
    YooBossBarBuilder color(BarColor color);
    YooBossBarBuilder style(BarStyle style);
    YooBossBarBuilder visible(boolean visible);
    YooBossBarBuilder players(Collection<? extends Player> players);
    YooBossBarBuilder group(String groupName, Collection<? extends Player> players);
    YooBossBar build();
}
