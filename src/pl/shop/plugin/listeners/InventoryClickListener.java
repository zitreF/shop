package pl.shop.plugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.shop.plugin.menu.MenuHolder;

public final class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getClickedInventory() == null || !(e.getClickedInventory().getHolder() instanceof MenuHolder)) return;

        MenuHolder menuHolder = (MenuHolder) e.getClickedInventory().getHolder();

        menuHolder.getCustomMenu().onInventoryClick(e, (Player) e.getWhoClicked());

    }
}
