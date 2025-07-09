package dev.yooproject.yoolib.entity;

import java.util.*;

public enum YooEntityType {
    PLAYER,
    ZOMBIE,
    SKELETON,
    CREEPER,
    SPIDER,
    ENDERMAN,
    SLIME,
    VILLAGER,
    IRON_GOLEM,
    SNOW_GOLEM,
    WITCH,
    BLAZE,
    GHAST,
    MAGMA_CUBE,
    PIG,
    COW,
    SHEEP,
    CHICKEN,
    HORSE,
    WOLF,
    OCELOT,
    CAT,
    DOG,
    FOX,
    PANDA,
    PARROT,
    RABBIT,
    TURTLE,
    DOLPHIN,
    PHANTOM,
    SHULKER,
    ARMOR_STAND,
    ITEM,
    PROJECTILE,
    NPC,
    PET,
    CUSTOM_BOSS,
    CUSTOM_NPC,
    CUSTOM_PET,
    CUSTOM_PROJECTILE;

    public static List<YooEntityType> getAllTypes() {
        return Arrays.asList(values());
    }
}
