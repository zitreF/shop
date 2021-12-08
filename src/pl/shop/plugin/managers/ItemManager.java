package pl.shop.plugin.managers;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import pl.shop.plugin.data.Item;

import java.util.Map;
import java.util.stream.Collectors;

public final class ItemManager {

    private final Map<Integer, Item> emeralds;
    private final Map<Integer, Item> sells;
    private final Map<Integer, Item> coins;

    public ItemManager(FileConfiguration config) {
        ConfigurationSection emeraldSection = config.getConfigurationSection("shop.emerald");
        ConfigurationSection sellSection = config.getConfigurationSection("shop.sell");
        ConfigurationSection coinsSection = config.getConfigurationSection("shop.coins");
        this.emeralds = emeraldSection.getKeys(false).stream().map(emeraldSection::getConfigurationSection).collect(Collectors.toMap(cfg -> cfg.getInt("slot"), Item::new));
        this.sells = sellSection.getKeys(false).stream().map(sellSection::getConfigurationSection).collect(Collectors.toMap(cfg -> cfg.getInt("slot"), Item::new));
        this.coins = coinsSection.getKeys(false).stream().map(coinsSection::getConfigurationSection).collect(Collectors.toMap(cfg -> cfg.getInt("slot"), Item::new));
    }

    public Map<Integer, Item> getEmeralds() {
        return emeralds;
    }

    public Map<Integer, Item> getSells() {
        return sells;
    }

    public Map<Integer, Item> getCoins() {
        return coins;
    }
}
