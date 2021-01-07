package org.skyhype.core.item

import net.md_5.bungee.api.ChatColor
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.skyhype.core.SkyhypePlugin
import org.skyhype.core.gui.HearthstoneGUI
import org.skyhype.core.obj.SHItem

@SHItem.Info(
    name = "&6Hearthstone",
    material = Material.ENDER_EYE,
    amount = 1,
    lore = ["&aClick while holding this item", "&ato access any feature from any", "&aplace on SkyHype.", "", "&aYou can also access this menu", "&aby typing &b/hearthstone&a or&b/hs&a."],
    setNBT = true,
    glowing = false,
    itemFlags = []
)
@Suppress("UNUSED") // It is used dumb IDE, Reflections registers this.
class Hearthstone(plugin: SkyhypePlugin) : SHItem(plugin) {

    @EventHandler(ignoreCancelled = true)
    fun onInteract(event: PlayerInteractEvent) {

        if (!event.hasItem() || event.hand != EquipmentSlot.HAND) {
            return
        }

        val handItem = event.item ?: return
        val nmsHand = CraftItemStack.asNMSCopy(handItem)
        if (!handItem.isSimilar(this.shItem) || nmsHand.orCreateTag.getString("ItemType") != ChatColor.stripColor(this.info.name.toLowerCase())) {
            return
        }

        HearthstoneGUI(plugin, event.player).openMenu()
    }
}