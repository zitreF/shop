package pl.shop.plugin.menu.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.shop.plugin.data.Item;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.managers.UserManager;
import pl.shop.plugin.menu.CustomMenu;
import pl.shop.plugin.user.User;
import pl.shop.plugin.utils.ChatUtil;

import java.util.Map;

public final class SellShopMenu extends CustomMenu {

    private final UserManager userManager;
    private final ItemManager itemManager;

    public SellShopMenu(ItemManager itemManager, UserManager userManager) {
        super(9*6, "&8>> &cSprzedawaj!");

        this.userManager = userManager;

        this.itemManager = itemManager;

        for (Map.Entry<Integer, Item> item : itemManager.getSells().entrySet()) {
            setItem(item.getValue().getItem(), item.getKey());
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, Player p) {

        e.setCancelled(true);

        Item item = itemManager.getSells().get(e.getSlot());

        if (item == null) return;

        p.getInventory().removeItem(item.getRaw());

        userManager.getUserByName(p.getName()).addCoins(item.getCost());

        p.sendTitle(ChatUtil.fixColor("&8>> &9&lSKLEP &8<<"), ChatUtil.fixColor(String.format("&8>> &7Sprzedales %s za &e%d", item.getName(), item.getCost())));
    }
}
