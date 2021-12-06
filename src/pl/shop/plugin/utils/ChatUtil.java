package pl.shop.plugin.utils;

import org.bukkit.ChatColor;

public final class ChatUtil {

    private ChatUtil() {
    }

    public static String fixColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg).replace(">>", "»").replace("<<", "«");
    }
}
