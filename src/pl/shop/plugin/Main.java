package pl.shop.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.shop.plugin.managers.ItemManager;

public final class Main extends JavaPlugin {

    private final ItemManager manager = new ItemManager(getConfig().getConfigurationSection("items"));

    @Override
    public void onEnable() {

    }
}
