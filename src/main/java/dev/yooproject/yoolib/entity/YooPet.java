package dev.yooproject.yoolib.entity;

import org.bukkit.entity.Player;

public class YooPet extends YooEntity {
    protected Player owner;
    protected String petType;

    public YooPet() { this.type = YooEntityType.PET; }

    public YooPet setOwner(Player player) { this.owner = player; return this; }
    public YooPet setType(String petType) { this.petType = petType; return this; }
}
