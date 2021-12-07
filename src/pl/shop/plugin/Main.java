package pl.shop.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.shop.plugin.commands.ShopCommand;
import pl.shop.plugin.listeners.InventoryClickListener;
import pl.shop.plugin.managers.ItemManager;
import pl.shop.plugin.menu.impl.ShopMenu;

public final class Main extends JavaPlugin {

    private final ItemManager manager = new ItemManager(getConfig().getConfigurationSection("items"));
    private final ShopMenu shopMenu = new ShopMenu();

    @Override
    public void onEnable() {
        getCommand("sklep").setExecutor(new ShopCommand(shopMenu));
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }
}
