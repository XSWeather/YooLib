package dev.yooproject.yoolib.entity;

public class YooArmorStand extends YooEntity {
    protected boolean small = false;
    protected boolean marker = false;

    public YooArmorStand() { this.type = YooEntityType.ARMOR_STAND; }

    public YooArmorStand setSmall(boolean small) { this.small = small; return this; }
    public YooArmorStand setMarker(boolean marker) { this.marker = marker; return this; }
}
