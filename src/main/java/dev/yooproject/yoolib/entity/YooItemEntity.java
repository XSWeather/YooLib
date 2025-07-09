package dev.yooproject.yoolib.entity;

public class YooItemEntity extends YooEntity {
    protected String itemType;
    protected int amount = 1;

    public YooItemEntity() { this.type = YooEntityType.ITEM; }

    public YooItemEntity setItemType(String itemType) { this.itemType = itemType; return this; }
    public YooItemEntity setAmount(int amount) { this.amount = amount; return this; }
}
