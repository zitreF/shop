package pl.shop.plugin.menu.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.shop.plugin.data.Item;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.managers.UserManager;
import pl.shop.plugin.menu.CustomMenu;
import pl.shop.plugin.user.User;
import pl.shop.plugin.utils.ChatUtil;

import java.util.Map;

public final class CoinsShopMenu extends CustomMenu {

    private final UserManager userManager;
    private final ItemManager itemManager;

    public CoinsShopMenu(ItemManager itemManager, UserManager userManager) {
        super(9*6, "&8>> &aSklep za coinsy!");

        this.userManager = userManager;

        this.itemManager = itemManager;

        for (Map.Entry<Integer, Item> item : itemManager.getCoins().entrySet()) {
            setItem(item.getValue().getItem(), item.getKey());
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, Player p) {

        e.setCancelled(true);

        Item item = itemManager.getCoins().get(e.getSlot());


        if (item == null) return;

        User u = userManager.getUserByUUID(p.getUniqueId());

        if (u.getCoins() >= item.getCost()) {

            p.getInventory().addItem(item.getRaw());
            u.addCoins(-item.getCost());

            p.sendTitle(ChatUtil.fixColor("&8>> &9&lSKLEP &8<<"), ChatUtil.fixColor(String.format("&8>> &7Kupiles &e%d &7%s", item.getRaw().getAmount(), item.getName())));

            return;
        }

        p.sendTitle(ChatUtil.fixColor("&8>> &9&lSKLEP &8<<"), ChatUtil.fixColor("&8>> &7Nie posiadasz &e" + item.getCost() + " &7coinsow! &8<<"));
    }
}
