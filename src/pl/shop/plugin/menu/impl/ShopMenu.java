package pl.shop.plugin.menu.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.managers.UserManager;
import pl.shop.plugin.menu.CustomMenu;
import pl.shop.plugin.utils.ItemBuilder;

public final class ShopMenu extends CustomMenu {

    private final Inventory emeraldShopMenu;
    private final Inventory sellShopMenu;

    public ShopMenu(ItemManager itemManager, UserManager userManager) {
        super(9*6, "&8>> &7Sklep!");
        this.emeraldShopMenu = new EmeraldShopMenu(itemManager).getInventory();
        this.sellShopMenu = new SellShopMenu(itemManager, userManager).getInventory();
        setItem(new ItemBuilder(Material.EMERALD).setTitle("&8>> &aSKLEP ZA EMERALDY &8<<").addEnchantment(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 12);
        setItem(new ItemBuilder(Material.REDSTONE_BLOCK).setTitle("&8>> &cSPRZEDAZ &8<<").addEnchantment(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 14);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, Player p) {
        e.setCancelled(true);
        if (e.getSlot() == 12) {
            p.openInventory(emeraldShopMenu);
        } else if (e.getSlot() == 14) {
            p.openInventory(sellShopMenu);
        }
    }
}
