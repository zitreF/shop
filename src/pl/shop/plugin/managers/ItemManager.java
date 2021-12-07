package pl.shop.plugin.managers;

import org.bukkit.configuration.ConfigurationSection;
import pl.shop.plugin.data.Item;

import java.util.HashMap;
import java.util.Map;

public final class ItemManager {

    private final Map<Integer, Item> item;

    public ItemManager(ConfigurationSection cs) {
        this.item = new HashMap<>();
        for (String s : cs.getKeys(false)) {
            ConfigurationSection section = cs.getConfigurationSection(s);
            item.put(section.getInt("slot"), new Item(section));
        }
    }

    public Map<Integer, Item> getItem() {
        return item;
    }
}
