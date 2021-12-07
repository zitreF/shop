package pl.shop.plugin.menu.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.shop.plugin.data.Item;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.menu.CustomMenu;
import pl.shop.plugin.utils.ChatUtil;

import java.util.Map;

public final class EmeraldShopMenu extends CustomMenu {

    private final ItemManager itemManager;

    public EmeraldShopMenu(ItemManager itemManager) {
        super(9*6, "&8>> &aSklep za emeraldy!");

        this.itemManager = itemManager;

        for (Map.Entry<Integer, Item> item : itemManager.getItems().entrySet()) {
            setItem(item.getValue().getItem(), item.getKey());
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, Player p) {

        e.setCancelled(true);

        Item item = itemManager.getItems().get(e.getSlot());

        if (item == null) return;

        p.getInventory().removeItem(new ItemStack(Material.EMERALD, item.getCost()));
        p.getInventory().addItem(item.getRaw());
        p.sendTitle(ChatUtil.fixColor("&8>> &9&lSKLEP &8<<"), ChatUtil.fixColor(String.format("&8>> &7Kupiles &e%d &7%s", item.getRaw().getAmount(), item.getName())));
    }
}
