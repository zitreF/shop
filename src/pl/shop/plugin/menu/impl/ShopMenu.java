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
import pl.shop.plugin.user.User;
import pl.shop.plugin.utils.ItemBuilder;

public final class ShopMenu extends CustomMenu {

    private final Inventory emeraldShopMenu;
    private final Inventory sellShopMenu;
    private final Inventory coinsShopMenu;

    public ShopMenu(ItemManager itemManager, UserManager userManager) {
        super(9*6, "&8>> &7Sklep!");
        this.coinsShopMenu = new CoinsShopMenu(itemManager, userManager).getInventory();
        this.emeraldShopMenu = new EmeraldShopMenu(itemManager).getInventory();
        this.sellShopMenu = new SellShopMenu(itemManager, userManager).getInventory();
        setItem(new ItemBuilder(Material.EMERALD).setTitle("&8>> &aSKLEP ZA EMERALDY &8<<").addEnchantment(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 22);
        setItem(new ItemBuilder(Material.INK_SACK, 1, (short)10).setTitle("&8>> &aSKLEP ZA COINSY &8<<").addEnchantment(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 29);
        setItem(new ItemBuilder(Material.REDSTONE_BLOCK).setTitle("&8>> &cSPRZEDAZ &8<<").addEnchantment(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 33);
    }

    public Inventory getInventory(User u) {
        setItem(new ItemBuilder(Material.YELLOW_FLOWER).setTitle("&8>> &7Posiadasz &e" + u.getCoins() + " &7coinsow! &8<<").addEnchantment(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).build(), 40);
        return super.getInventory();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e, Player p) {
        e.setCancelled(true);
        if (e.getSlot() == 22) {
            p.openInventory(emeraldShopMenu);
        } else if (e.getSlot() == 33) {
            p.openInventory(sellShopMenu);
        } else if (e.getSlot() == 29) {
            p.openInventory(coinsShopMenu);
        }
    }
}
