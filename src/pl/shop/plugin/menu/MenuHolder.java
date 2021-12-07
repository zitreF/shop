package pl.shop.plugin.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public final class MenuHolder implements InventoryHolder {

    private final CustomMenu menu;

    public MenuHolder(CustomMenu menu) {
        this.menu = menu;
    }

    public CustomMenu getCustomMenu() {
        return menu;
    }

    @Override
    public Inventory getInventory() {
        return menu.getInventory();
    }
}
