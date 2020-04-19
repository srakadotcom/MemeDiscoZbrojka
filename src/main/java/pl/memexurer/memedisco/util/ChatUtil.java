package pl.memexurer.memedisco.util;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class ChatUtil {
    public static String fixColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static List<String> fixColor(List<String> list) {
        return list.stream().map(ChatUtil::fixColor).collect(Collectors.toList());
    }
}
