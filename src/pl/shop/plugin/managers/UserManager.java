package pl.shop.plugin.managers;

import pl.shop.plugin.Main;
import pl.shop.plugin.database.MySQL;
import pl.shop.plugin.user.User;

import java.sql.SQLException;
import java.util.*;

public final class UserManager {

    private final Map<UUID, User> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public User getUserByUUID(UUID uuid) {
        return users.get(uuid);
    }

    public void addUser(UUID uuid, User user) {
        users.put(uuid, user);
    }

    public void load() {
        Main.getInstance().getMySQL().executeQuery("SELECT * FROM `users`", rs -> {
            try {
                while (rs.next()) {
                    User u = new User(rs);
                    users.put(u.getUUID(), u);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public Map<UUID, User> getUsers() {
        return users;
    }
}
