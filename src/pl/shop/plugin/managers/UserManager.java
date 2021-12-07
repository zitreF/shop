package pl.shop.plugin.managers;

import pl.shop.plugin.database.MySQL;
import pl.shop.plugin.user.User;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public final class UserManager {

    private final Set<User> users;

    public UserManager() {
        this.users = new HashSet<>();
    }

    public User getUserByName(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void load() {
        MySQL.getInstance().executeQuery("SELECT * FROM `users`", rs -> {
            try {
                while (rs.next()) {
                    User u = new User(rs);
                    users.add(u);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public Set<User> getUsers() {
        return users;
    }
}
