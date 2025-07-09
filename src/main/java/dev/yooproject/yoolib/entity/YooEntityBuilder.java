package dev.yooproject.yoolib.entity;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class YooEntityBuilder {
    protected YooEntityType type = YooEntityType.CUSTOM_BOSS;
    protected Location location;
    protected World world;
    protected String customName;
    protected boolean visible = true;
    protected boolean glowing = false;
    protected boolean invulnerable = false;
    protected boolean silent = false;
    protected Map<String, Object> tags = new HashMap<>();
    protected Set<Player> players = new HashSet<>();
    protected List<Consumer<YooEntity>> spawnListeners = new ArrayList<>();
    protected List<Consumer<YooEntity>> removeListeners = new ArrayList<>();
    protected List<BiConsumer<Player, YooEntity>> interactListeners = new ArrayList<>();
    protected List<Consumer<YooEntity>> tickListeners = new ArrayList<>();
    
    protected String skin;
    protected boolean aiEnabled = true;
    protected String dialog;
    protected Player owner;
    protected String petType;
    protected double speed;
    protected double damage;
    protected boolean small = false;
    protected boolean marker = false;
    protected String itemType;
    protected int amount = 1;

    public YooEntityBuilder type(YooEntityType type) { this.type = type; return this; }
    public YooEntityBuilder location(Location location) { this.location = location; return this; }
    public YooEntityBuilder world(World world) { this.world = world; return this; }
    public YooEntityBuilder customName(String name) { this.customName = name; return this; }
    public YooEntityBuilder visible(boolean visible) { this.visible = visible; return this; }
    public YooEntityBuilder glowing(boolean glowing) { this.glowing = glowing; return this; }
    public YooEntityBuilder invulnerable(boolean invulnerable) { this.invulnerable = invulnerable; return this; }
    public YooEntityBuilder silent(boolean silent) { this.silent = silent; return this; }
    public YooEntityBuilder tag(String key, Object value) { this.tags.put(key, value); return this; }
    public YooEntityBuilder players(Collection<? extends Player> players) { this.players.addAll(players); return this; }
    public YooEntityBuilder onSpawn(Consumer<YooEntity> listener) { this.spawnListeners.add(listener); return this; }
    public YooEntityBuilder onRemove(Consumer<YooEntity> listener) { this.removeListeners.add(listener); return this; }
    public YooEntityBuilder onInteract(BiConsumer<Player, YooEntity> listener) { this.interactListeners.add(listener); return this; }
    public YooEntityBuilder onTick(Consumer<YooEntity> listener) { this.tickListeners.add(listener); return this; }
    
    public YooEntityBuilder skin(String skin) { this.skin = skin; return this; }
    public YooEntityBuilder aiEnabled(boolean aiEnabled) { this.aiEnabled = aiEnabled; return this; }
    public YooEntityBuilder dialog(String dialog) { this.dialog = dialog; return this; }
    public YooEntityBuilder owner(Player owner) { this.owner = owner; return this; }
    public YooEntityBuilder petType(String petType) { this.petType = petType; return this; }
    public YooEntityBuilder speed(double speed) { this.speed = speed; return this; }
    public YooEntityBuilder damage(double damage) { this.damage = damage; return this; }
    public YooEntityBuilder small(boolean small) { this.small = small; return this; }
    public YooEntityBuilder marker(boolean marker) { this.marker = marker; return this; }
    public YooEntityBuilder itemType(String itemType) { this.itemType = itemType; return this; }
    public YooEntityBuilder amount(int amount) { this.amount = amount; return this; }

    public YooEntity build() {
        YooEntity entity;
        switch (type) {
            case NPC:
                YooNPC npc = new YooNPC();
                npc.setSkin(skin).setAIEnabled(aiEnabled).setDialog(dialog);
                entity = npc;
                break;
            case PET:
                YooPet pet = new YooPet();
                pet.setOwner(owner).setType(petType);
                entity = pet;
                break;
            case PROJECTILE:
                YooProjectile proj = new YooProjectile();
                proj.setSpeed(speed).setDamage(damage);
                entity = proj;
                break;
            case ARMOR_STAND:
                YooArmorStand as = new YooArmorStand();
                as.setSmall(small).setMarker(marker);
                entity = as;
                break;
            case ITEM:
                YooItemEntity item = new YooItemEntity();
                item.setItemType(itemType).setAmount(amount);
                entity = item;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        entity.type = this.type;
        entity.location = this.location;
        entity.world = this.world;
        entity.customName = this.customName;
        entity.visible = this.visible;
        entity.glowing = this.glowing;
        entity.invulnerable = this.invulnerable;
        entity.silent = this.silent;
        entity.tags = this.tags;
        entity.players = this.players;
        return entity;
    }
}
