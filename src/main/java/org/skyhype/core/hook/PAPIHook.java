package org.skyhype.core.hook;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public final class PAPIHook {

    private static boolean isEnabled() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public static String apply(@Nullable Player player, String text) {
        if (isEnabled()) {
            return PlaceholderAPI.setPlaceholders(player, text);
        } else {
            return text;
        }
    }
}
