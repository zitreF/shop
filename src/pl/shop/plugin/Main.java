package pl.shop.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.shop.plugin.commands.ShopCommand;
import pl.shop.plugin.database.MySQL;
import pl.shop.plugin.listeners.InventoryClickListener;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.managers.UserManager;
import pl.shop.plugin.menu.impl.ShopMenu;
import pl.shop.plugin.user.User;

public final class Main extends JavaPlugin {

    private final ItemManager manager = new ItemManager(getConfig());
    private final UserManager userManager = new UserManager();
    private final ShopMenu shopMenu = new ShopMenu(manager, userManager);

    @Override
    public void onEnable() {

        new MySQL();

        userManager.load();

        getCommand("sklep").setExecutor(new ShopCommand(shopMenu));

        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    @Override
    public void onDisable() {
        for (User u : userManager.getUsers()) {
            u.update();
        }
    }
}
