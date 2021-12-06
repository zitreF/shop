package pl.shop.plugin.managers;

import org.bukkit.configuration.ConfigurationSection;
import pl.shop.plugin.data.Item;

import java.util.Set;
import java.util.stream.Collectors;

public final class ItemManager {

    private final Set<Item> items;

    public ItemManager(ConfigurationSection cs) {
        this.items = cs.getKeys(false).stream().map(cs::getConfigurationSection).map(Item::new).collect(Collectors.toSet());
    }

    public Set<Item> getItems() {
        return items;
    }
}
