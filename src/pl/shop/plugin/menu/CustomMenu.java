package pl.shop.plugin.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.shop.plugin.utils.ChatUtil;

public abstract class CustomMenu {

    private final ItemStack is;
    private final Inventory inv;

    protected CustomMenu(int rows, String title) {
        this.inv = Bukkit.createInventory(new MenuHolder(this), rows, ChatUtil.fixColor(title));
        this.is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        fillInventory();
        designGui();
    }

    public abstract void onInventoryClick(InventoryClickEvent e, Player p);

    protected void setItem(ItemStack item, int slot) {
        inv.setItem(slot, item);
    }

    protected void setItems(ItemStack item, int... slots) {
        for (int slot : slots) {
            setItem(item, slot);
        }
    }

    private void designGui() {
        ItemStack yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
        ItemStack orange = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        setItems(yellow, 3, 5, 18, 26, 27, 35);

        setItems(orange, 4, 10, 11, 15, 16, 36, 44, 45, 46, 52, 53);
    }

    private void fillInventory() {
        for (int i = 0; i < inv.getSize(); i++) {
            setItem(is, i);
        }
    }

    protected void addItems(ItemStack... contents) {
        inv.addItem(contents);
    }

    public Inventory getInventory() {
        return inv;
    }
}
