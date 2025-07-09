package dev.yooproject.yoolib.object.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class YooPaginatedGui extends YooGui {
    private final List<ItemStack> contentItems = new ArrayList<>();
    private final List<Consumer<InventoryClickEvent>> contentHandlers = new ArrayList<>();
    private final List<IntConsumer> pageChangeHandlers = new ArrayList<>();

    private List<Integer> contentSlots;
    private int page = 1;

    private IntFunction<ItemStack> previousPageItem;
    private IntFunction<ItemStack> nextPageItem;
    private int previousPageSlot = -1;
    private int nextPageSlot = -1;

    public YooPaginatedGui(int size) {
        this(owner -> Bukkit.createInventory(owner, size));
    }

    public YooPaginatedGui(int size, String title) {
        this(owner -> Bukkit.createInventory(owner, size, title));
    }

    public YooPaginatedGui(InventoryType type) {
        this(owner -> Bukkit.createInventory(owner, type));
    }

    public YooPaginatedGui(InventoryType type, String title) {
        this(owner -> Bukkit.createInventory(owner, type, title));
    }

    public YooPaginatedGui(Function<YooPaginatedGui, Inventory> inventoryFunction) {
        super(inv -> inventoryFunction.apply((YooPaginatedGui) inv));
        this.contentSlots = IntStream.range(0, Math.max(9, getInventory().getSize() - 9))
                .boxed().collect(Collectors.toList());
    }

    public void addContent(ItemStack item) {
        addContent(item, null);
    }

    public void addContent(ItemStack item, Consumer<InventoryClickEvent> handler) {
        this.contentItems.add(item);
        this.contentHandlers.add(handler);
    }

    public void addContent(Collection<ItemStack> content) {
        addContent(content, Collections.nCopies(content.size(), null));
    }

    public void addContent(Collection<ItemStack> content, Collection<Consumer<InventoryClickEvent>> handlers) {
        Objects.requireNonNull(content, "content");
        Objects.requireNonNull(handlers, "handlers");
        if (content.size() != handlers.size()) {
            throw new IllegalArgumentException("The content and handlers lists must have the same size");
        }
        this.contentItems.addAll(content);
        this.contentHandlers.addAll(handlers);
    }

    public void setContent(int index, ItemStack item) {
        setContent(index, item, null);
    }

    public void setContent(int index, ItemStack item, Consumer<InventoryClickEvent> handler) {
        this.contentItems.set(index, item);
        this.contentHandlers.set(index, handler);
    }

    public void setContent(List<ItemStack> content) {
        clearContent();
        addContent(content);
    }

    public void setContent(Collection<ItemStack> content, Collection<Consumer<InventoryClickEvent>> handlers) {
        clearContent();
        addContent(content, handlers);
    }

    public void clearContent() {
        this.contentItems.clear();
        this.contentHandlers.clear();
    }

    public void openPrevious() {
        openPage(this.page - 1);
    }

    public void openNext() {
        openPage(this.page + 1);
    }

    public void refreshCurrentPage() {
        openPage(this.page);
    }

    public void openPage(int page) {
        int lastPage = lastPage();
        this.page = Math.max(1, Math.min(page, lastPage));
        int index = this.contentSlots.size() * (this.page - 1);

        for (int slot : this.contentSlots) {
            if (index >= this.contentItems.size()) {
                removeItem(slot);
                continue;
            }
            setItem(slot, contentItems.get(index), contentHandlers.get(index++));
        }

        if (this.page > 1 && this.previousPageItem != null) {
            setItem(this.previousPageSlot, this.previousPageItem.apply(this.page - 1), e -> openPrevious());
        } else if (this.previousPageSlot >= 0) {
            removeItem(this.previousPageSlot);
        }

        if (this.page < lastPage && this.nextPageItem != null) {
            setItem(this.nextPageSlot, this.nextPageItem.apply(this.page + 1), e -> openNext());
        } else if (this.nextPageSlot >= 0) {
            removeItem(this.nextPageSlot);
        }

        onPageChange(page);
        this.pageChangeHandlers.forEach(c -> c.accept(this.page));
    }

    public void setContentSlots(List<Integer> contentSlots) {
        this.contentSlots = Objects.requireNonNull(contentSlots, "contentSlots");
    }

    public void previousPageItem(int slot, IntFunction<ItemStack> item) {
        this.previousPageSlot = slot;
        this.previousPageItem = item;
    }

    public void previousPageItem(int slot, ItemStack item) {
        previousPageItem(slot, page -> item);
    }

    public void nextPageItem(int slot, IntFunction<ItemStack> item) {
        this.nextPageSlot = slot;
        this.nextPageItem = item;
    }

    public void nextPageItem(int slot, ItemStack item) {
        nextPageItem(slot, page -> item);
    }

    @Override
    public void open(Player player) {
        openPage(this.page);
        super.open(player);
    }

    protected void onPageChange(int page) {}

    public int currentPage() {
        return this.page;
    }

    public int lastPage() {
        int last = this.contentItems.size() / this.contentSlots.size();
        int remaining = this.contentItems.size() % this.contentSlots.size();
        return remaining == 0 ? last : last + 1;
    }

    public boolean isFirstPage() {
        return this.page == 1;
    }

    public boolean isLastPage() {
        return this.page == lastPage();
    }

    public void addPageChangeHandler(IntConsumer handler) {
        this.pageChangeHandlers.add(handler);
    }
}
