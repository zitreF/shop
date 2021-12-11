package pl.shop.plugin.user;

import org.bukkit.Bukkit;
import pl.shop.plugin.Main;
import pl.shop.plugin.managers.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public final class User {

    private final String name;
    private final UUID uuid;
    private int coins;

    public User(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
        this.coins = 0;
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), this::insert);
        Main.getInstance().getUserManager().addUser(uuid, this);
    }

    public User(ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.coins = rs.getInt("coins");
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int amount) {
        this.coins+=amount;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void update() {
        Main.getInstance().getMySQL().executeUpdate("UPDATE users SET name = '"+this.name+"', coins = '"+this.coins+"' WHERE `uuid` = '" + this.uuid + "';");
    }

    private void insert() {
        Main.getInstance().getMySQL().executeUpdate("INSERT INTO users(uuid, name, coins) VALUES ('"+this.uuid+"', '"+this.name+"', '"+this.coins+"');");
    }
}
