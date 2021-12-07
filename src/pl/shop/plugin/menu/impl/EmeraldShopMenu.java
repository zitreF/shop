package pl.shop.plugin.menu.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.shop.plugin.data.Item;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.menu.CustomMenu;

import java.util.Map;

public final class EmeraldShopMenu extends CustomMenu {

    private final ItemManager itemManager;

    public EmeraldShopMenu(ItemManager itemManager) {
        super(9*6, "&8>> &aSklep za emeraldy!");

        this.itemManager = itemManager;

        for (Map.Entry<Integer, Item> item : itemManager.getItem().entrySet()) {
            setItem(item.getValue().getItem(), item.getKey());
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, Player p) {

        e.setCancelled(true);

        Item item = itemManager.getItem().get(e.getSlot());

        if (item == null) return;

        item.getOnMouseClicked().accept(p);
    }
}
