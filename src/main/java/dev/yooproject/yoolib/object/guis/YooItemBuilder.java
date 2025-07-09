package dev.yooproject.yoolib.object.guis;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class YooItemBuilder {
    private final ItemStack item;

    public static YooItemBuilder copyOf(ItemStack item) {
        return new YooItemBuilder(item.clone());
    }

    public YooItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    public YooItemBuilder(ItemStack item) {
        this.item = Objects.requireNonNull(item, "item");
    }

    public YooItemBuilder edit(Consumer<ItemStack> function) {
        function.accept(this.item);
        return this;
    }

    public YooItemBuilder meta(Consumer<ItemMeta> metaConsumer) {
        return edit(item -> {
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                metaConsumer.accept(meta);
                item.setItemMeta(meta);
            }
        });
    }

    public <T extends ItemMeta> YooItemBuilder meta(Class<T> metaClass, Consumer<T> metaConsumer) {
        return meta(meta -> {
            if (metaClass.isInstance(meta)) {
                metaConsumer.accept(metaClass.cast(meta));
            }
        });
    }

    public YooItemBuilder type(Material material) {
        return edit(item -> item.setType(material));
    }

    public YooItemBuilder amount(int amount) {
        return edit(item -> item.setAmount(amount));
    }

    public YooItemBuilder enchant(Enchantment enchantment, int level) {
        return meta(meta -> meta.addEnchant(enchantment, level, true));
    }

    public YooItemBuilder name(String name) {
        return meta(meta -> meta.setDisplayName(name));
    }

    public YooItemBuilder lore(String... lore) {
        return meta(meta -> meta.setLore(Arrays.asList(lore)));
    }

    public YooItemBuilder flags(ItemFlag... flags) {
        return meta(meta -> meta.addItemFlags(flags));
    }

    public YooItemBuilder armorColor(Color color) {
        return meta(LeatherArmorMeta.class, meta -> meta.setColor(color));
    }

    public ItemStack build() {
        return this.item;
    }
}