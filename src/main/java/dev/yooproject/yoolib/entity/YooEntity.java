package dev.yooproject.yoolib.entity;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class YooEntity {

    protected UUID uuid = UUID.randomUUID();
    protected YooEntityType type = YooEntityType.CUSTOM_BOSS;
    protected String customName;
    protected boolean visible = true;
    protected boolean glowing = false;
    protected boolean invulnerable = false;
    protected boolean silent = false;
    protected Location location;
    protected World world;
    protected Set<Player> players = new HashSet<>();
    protected Map<String, Object> tags = new HashMap<>();
    protected YooEntityStatus status = YooEntityStatus.ALIVE;

    public YooEntity spawn(Location location) { return this; }
    public YooEntity teleport(Location location) { return this; }
    public YooEntity remove() { return this; }
    public YooEntity setCustomName(String name) { this.customName = name; return this; }
    public String getCustomName() { return customName; }
    public YooEntity setVisible(boolean visible) { this.visible = visible; return this; }
    public boolean isVisible() { return visible; }
    public YooEntity setGlowing(boolean glowing) { this.glowing = glowing; return this; }
    public boolean isGlowing() { return glowing; }
    public YooEntity setInvulnerable(boolean invulnerable) { this.invulnerable = invulnerable; return this; }
    public boolean isInvulnerable() { return invulnerable; }
    public YooEntity setSilent(boolean silent) { this.silent = silent; return this; }
    public boolean isSilent() { return silent; }

    public YooEntity addTag(String key, Object value) { tags.put(key, value); return this; }
    public Object getTag(String key) { return tags.get(key); }

    public YooEntity addPlayer(Player player) { players.add(player); return this; }
    public YooEntity removePlayer(Player player) { players.remove(player); return this; }
    public YooEntity addPlayers(Collection<? extends Player> players) { this.players.addAll(players); return this; }
    public YooEntity removePlayers(Collection<? extends Player> players) { this.players.removeAll(players); return this; }
    public YooEntity clearPlayers() { this.players.clear(); return this; }
    public Set<Player> getPlayers() { return players; }

    public YooEntity addGroup(String groupName, Collection<? extends Player> players) { return this; }
    public YooEntity removeGroup(String groupName) { return this; }
    public YooEntity showToGroup(String groupName) { return this; }
    public YooEntity hideFromGroup(String groupName) { return this; }
    public YooEntity filterPlayers(Predicate<Player> filter) { return this; }

    public YooEntity async() { return this; }
    public YooEntity sync() { return this; }

    public YooEntity onSpawn(Consumer<YooEntity> listener) { return this; }
    public YooEntity onRemove(Consumer<YooEntity> listener) { return this; }
    public YooEntity onInteract(BiConsumer<Player, YooEntity> listener) { return this; }
    public YooEntity onTick(Consumer<YooEntity> listener) { return this; }

    public YooEntity removeAfter(long duration, TimeUnit unit) { return this; }

    public UUID getUniqueId() { return uuid; }
    public Location getLocation() { return location; }
    public World getWorld() { return world; }
    public boolean isSpawned() { return status == YooEntityStatus.ALIVE; }
    public YooEntityType getType() { return type; }
    public YooEntityStatus getStatus() { return status; }

    public static YooEntity create(YooEntityType type) {
        switch (type) {
            case NPC: return new YooNPC();
            case PET: return new YooPet();
            case PROJECTILE: return new YooProjectile();
            case ARMOR_STAND: return new YooArmorStand();
            case ITEM: return new YooItemEntity();
            default: return null;
        }
    }
}