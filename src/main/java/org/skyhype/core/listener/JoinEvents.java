package org.skyhype.core.listener;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.skyhype.core.SkyhypePlugin;
import org.skyhype.core.hook.PAPIHook;
import org.skyhype.core.item.Hearthstone;
import org.skyhype.core.manager.DataManager;

import java.util.Arrays;
import java.util.List;

import static org.skyhype.core.util.HexUtils.colorify;

public class JoinEvents implements Listener {

    private final SkyhypePlugin plugin;

    public JoinEvents(SkyhypePlugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        List<String> motd = Arrays.asList(
                "&aWelcome, %vault_prefix% " + player.getDisplayName() + " to &bSkyHype v2&a!",
                "&aType &b/is&a and &b/sf&a to get started.",
                "&aUse &b/help&a to find necessary info."
        );

        if (player.hasPermission("skyhype.fly")) {
            player.setAllowFlight(true);
            player.sendMessage(colorify("&aYou can now fly."));
        }

        motd.forEach(s -> player.sendMessage(colorify(PAPIHook.apply(player, s))));

        // I hate this code, fucking elif ladders
        if (player.hasPermission("skyhype.group.admin"))
            shootFirework(Color.RED, player.getLocation());
        else if (player.hasPermission("skyhype.group.jradmin"))
            shootFirework(Color.LIME, player.getLocation());
        else if (player.hasPermission("skyhype.group.mod"))
            shootFirework(Color.ORANGE, player.getLocation());
        else if (player.hasPermission("skyhype.group.reaper"))
            shootFirework(Color.PURPLE, player.getLocation());
        else if (player.hasPermission("skyhype.group.god"))
            shootFirework(Color.AQUA, player.getLocation());
        else if (player.hasPermission("skyhype.group.hero"))
            shootFirework(Color.FUCHSIA, player.getLocation());


//        if (!player.hasPlayedBefore()) {
        player.getInventory().setHeldItemSlot(4);
        player.getInventory().setItemInMainHand(new Hearthstone(plugin).getShItem());

//        plugin.getManager(DataManager.class).setBalance(player, 500);
        player.sendTitle(colorify("&a&lWelcome " + player.getName()), colorify("&a&lto &b&lSkyHype&a&l!"), 1, 3 * 20, 1);

        plugin.getServer().broadcastMessage(colorify("&aWelcome &b" + player.getName() + " &ato th server!"));
//        }


    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Firework)) {
            return;
        }

        if (event.getDamager().hasMetadata("LoginFirework")) {
            event.setCancelled(true);
        }
    }

    private void shootFirework(Color color, Location location) {
        if (location.getWorld() == null) {
            return;
        }

        Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.setPower(1);
        fireworkMeta.addEffect(FireworkEffect.builder()
                .trail(false)
                .withColor(color)
                .flicker(true)
                .build());

        firework.setFireworkMeta(fireworkMeta);
        firework.setSilent(true);
        firework.setShotAtAngle(false);
        firework.setMetadata("LoginFirework", new FixedMetadataValue(plugin, true));
        firework.detonate();
    }
}
