package pl.shop.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.shop.plugin.menu.impl.ShopMenu;

public final class ShopCommand implements CommandExecutor {

    private final Inventory inventory;

    public ShopCommand(ShopMenu shopMenu) {
        this.inventory = shopMenu.getInventory();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        ((Player)commandSender).openInventory(inventory);

        return false;
    }
}
