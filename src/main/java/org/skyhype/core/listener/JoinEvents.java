package org.skyhype.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.skyhype.core.SkyhypePlugin;

public class JoinEvents implements Listener {

    private final SkyhypePlugin plugin;

    public JoinEvents(SkyhypePlugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {

    }
}
