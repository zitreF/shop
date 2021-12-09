package pl.shop.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.shop.plugin.commands.ShopCommand;
import pl.shop.plugin.database.MySQL;
import pl.shop.plugin.listeners.InventoryClickListener;
import pl.shop.plugin.listeners.PlayerJoinListener;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.managers.UserManager;
import pl.shop.plugin.menu.impl.ShopMenu;
import pl.shop.plugin.user.User;

import java.util.Map;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private final ItemManager manager = new ItemManager(getConfig());
    private final UserManager userManager = new UserManager();
    private final ShopMenu shopMenu = new ShopMenu(manager, userManager);

    @Override
    public void onEnable() {

        saveDefaultConfig();

        new MySQL();

        userManager.load();

        getCommand("sklep").setExecutor(new ShopCommand(shopMenu, userManager));

        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(userManager), this);
    }

    @Override
    public void onDisable() {
        for (Map.Entry<UUID, User> u : userManager.getUsers().entrySet()) {
            u.getValue().update();
        }
    }
}
