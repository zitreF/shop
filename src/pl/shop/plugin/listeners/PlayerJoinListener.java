package pl.shop.plugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.shop.plugin.managers.UserManager;
import pl.shop.plugin.user.User;

public final class PlayerJoinListener implements Listener {

    private final UserManager userManager;

    public PlayerJoinListener(UserManager userManager) {
        this.userManager = userManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        User user = (userManager.getUserByUUID(p.getUniqueId()) == null ?  new User(p.getName(), p.getUniqueId(), userManager) : userManager.getUserByUUID(p.getUniqueId()));

        if (!p.hasPlayedBefore()) user.setCoins(1000);
    }
}
