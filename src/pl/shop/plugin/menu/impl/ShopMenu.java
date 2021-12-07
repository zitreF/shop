package pl.shop.plugin.menu.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import pl.shop.plugin.menu.CustomMenu;
import pl.shop.plugin.utils.ItemBuilder;

public final class ShopMenu extends CustomMenu {

    public ShopMenu() {
        super(9*6, "&8>> &7Sklep!");
        setItem(new ItemBuilder(Material.EMERALD).setTitle("&8>> &aSKLEP ZA EMERALDY &8<<").addEnchantment(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 12);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, Player p) {
        e.setCancelled(true);
        if (e.getSlot() == 12) {

        }
    }
}
