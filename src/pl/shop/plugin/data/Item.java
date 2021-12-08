package pl.shop.plugin.data;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.shop.plugin.utils.ItemBuilder;

public final class Item {

    private final String name;
    private final int cost;
    private final ItemStack item, raw;

    public Item(ConfigurationSection cs) {
        this.name = cs.getString("name");
        this.cost = cs.getInt("cost");
        ItemBuilder builder = new ItemBuilder(Material.getMaterial(cs.getString("material")), cs.getInt("amount"), (short) cs.getInt("data"));
        for (String s : cs.getStringList("enchantments")) {
            String[] splitted = s.split(":");
            builder.addEnchantment(Enchantment.getByName(splitted[0]), Integer.parseInt(splitted[1]));
        }
        this.raw = builder.build();
        this.item = builder.setTitle(cs.getString("title")).addLores(cs.getStringList("lore")).build();
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public ItemStack getRaw() {
        return raw;
    }

    public ItemStack getItem() {
        return item;
    }
}
