package dev.yooproject.yoolib.entity;

public class YooProjectile extends YooEntity {
    protected double speed;
    protected double damage;

    public YooProjectile() { this.type = YooEntityType.PROJECTILE; }

    public YooProjectile setSpeed(double speed) { this.speed = speed; return this; }
    public YooProjectile setDamage(double damage) { this.damage = damage; return this; }
}
