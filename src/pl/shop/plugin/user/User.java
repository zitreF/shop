package pl.shop.plugin.user;

import pl.shop.plugin.database.MySQL;

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
        this.insert();
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

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void update() {
        MySQL.getInstance().executeUpdate("UPDATE users SET name = '"+this.name+"', coins = '"+this.coins+"' WHERE `uuid` = '" + this.uuid + "';");
    }

    private void insert() {
        MySQL.getInstance().executeUpdate("INSERT INTO users(uuid, name, coins) VALUES ('"+this.uuid+"', '"+this.name+"', '"+this.coins+"');");
    }
}