package pl.shop.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.shop.plugin.managers.UserManager;
import pl.shop.plugin.menu.impl.ShopMenu;

public final class ShopCommand implements CommandExecutor {

    private final ShopMenu menu;
    private final UserManager userManager;

    public ShopCommand(ShopMenu shopMenu, UserManager userManager) {
        this.menu = shopMenu;
        this.userManager = userManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;

        p.openInventory(menu.getInventory(userManager.getUserByName(p.getName())));

        return false;
    }
}
