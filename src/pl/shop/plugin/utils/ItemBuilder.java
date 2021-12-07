package pl.shop.plugin.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public final class ItemBuilder {

    private final Material mat;
    private final int amount;
    private final short data;
    private String title;
    private final List<String> lore;
    private final Map<Enchantment, Integer> enchants;
    private ItemFlag itemFlag;

    public ItemBuilder(final Material mat) {
        this(mat, 1);
    }

    public ItemBuilder(final Material mat, final int amount) {
        this(mat, amount, (short)0);
    }

    public ItemBuilder(final Material mat, final int amount, final short data) {
        this.title = "";
        this.lore = new ArrayList<>();
        this.enchants = new HashMap<>();
        this.mat = mat;
        this.amount = amount;
        this.data = data;
        this.itemFlag = null;
    }

    public ItemBuilder setTitle(final String title) {
        this.title = ChatUtil.fixColor(title);
        return this;
    }

    public ItemBuilder addLore(String... lores) {
        for (String loreLine : lores) {
            this.lore.add(ChatUtil.fixColor(loreLine));
        }
        return this;
    }
    public ItemBuilder addLores(List<String> lores) {
        for (String loreLine : lores) {
            this.lore.add(ChatUtil.fixColor(loreLine));
        }
        return this;
    }

    public ItemBuilder addEnchantment(final Enchantment enchant, final int level) {
        this.enchants.remove(enchant);
        this.enchants.put(enchant, level);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.itemFlag = itemFlag;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(this.mat, this.amount, this.data);
        final ItemMeta meta = item.getItemMeta();
        if (!this.title.isEmpty()) {
            meta.setDisplayName(this.title);
        }
        if (!this.lore.isEmpty()) {
            meta.setLore(this.lore);
        }
        if (itemFlag != null) {
            meta.addItemFlags(itemFlag);
        }
        item.setItemMeta(meta);
        item.addUnsafeEnchantments(this.enchants);
        return item;
    }
}
