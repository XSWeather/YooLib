package dev.yooproject.yoolib.entity;

import org.bukkit.entity.Player;

import java.util.*;

public class YooEntityManager {
    protected Map<UUID, YooEntity> entities = new HashMap<>();

    public YooEntity createEntity(YooEntityType type) {
        YooEntity entity = YooEntity.create(type);
        entities.put(entity.getUniqueId(), entity);
        return entity;
    }

    public YooEntity getEntity(UUID uuid) { return entities.get(uuid); }
    public void removeEntity(UUID uuid) { entities.remove(uuid); }
    public Collection<YooEntity> getAllEntities() { return entities.values(); }
    public void showAll(Player player) {}
    public void hideAll(Player player) {}

    public List<YooEntityType> getAllEntityTypes() {
        return YooEntityType.getAllTypes();
    }
}
