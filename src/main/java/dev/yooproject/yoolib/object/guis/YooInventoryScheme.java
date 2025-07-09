package dev.yooproject.yoolib.object.guis;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Consumer;

public class YooInventoryScheme {
    private final List<String> masks = new ArrayList<>();
    private final Map<Character, ItemStack> items = new HashMap<>();
    private final Map<Character, Consumer<InventoryClickEvent>> handlers = new HashMap<>();
    private char paginationChar;

    public YooInventoryScheme mask(String mask) {
        Objects.requireNonNull(mask);
        this.masks.add(mask);
        return this;
    }

    public YooInventoryScheme masks(String... masks) {
        for (String mask : Objects.requireNonNull(masks)) {
            mask(mask);
        }
        return this;
    }

    public YooInventoryScheme bindItem(char character, ItemStack item, Consumer<InventoryClickEvent> handler) {
        this.items.put(character, Objects.requireNonNull(item));
        if (handler != null) {
            this.handlers.put(character, handler);
        }
        return this;
    }

    public YooInventoryScheme bindItem(char character, ItemStack item) {
        return this.bindItem(character, item, null);
    }

    public YooInventoryScheme bindPagination(char character) {
        this.paginationChar = character;
        return this;
    }

    public void apply(YooGui inv) {
        List<Integer> paginationSlots = new ArrayList<>();
        for (int line = 0; line < this.masks.size(); line++) {
            String mask = this.masks.get(line);
            for (int slot = 0; slot < mask.length(); slot++) {
                char c = mask.charAt(slot);
                if (c == this.paginationChar) {
                    paginationSlots.add(9 * line + slot);
                    continue;
                }
                ItemStack item = this.items.get(c);
                Consumer<InventoryClickEvent> handler = this.handlers.get(c);
                if (item != null) {
                    inv.setItem(9 * line + slot, item, handler);
                }
            }
        }
        if (inv instanceof YooPaginatedGui && !paginationSlots.isEmpty()) {
            ((YooPaginatedGui) inv).setContentSlots(paginationSlots);
        }
    }
}
