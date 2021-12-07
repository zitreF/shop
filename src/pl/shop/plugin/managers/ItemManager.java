package pl.shop.plugin.managers;

import org.bukkit.configuration.ConfigurationSection;
import pl.shop.plugin.data.Item;

import java.util.Map;
import java.util.stream.Collectors;

public final class ItemManager {

    private final Map<Integer, Item> items;

    public ItemManager(ConfigurationSection cs) {
        this.items = cs.getKeys(false).stream().map(cs::getConfigurationSection).collect(Collectors.toMap(config -> config.getInt("slot"), Item::new));
    }

    public Map<Integer, Item> getItems() {
        return items;
    }
}
