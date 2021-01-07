package org.skyhype.core.obj

import net.md_5.bungee.api.ChatColor
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.skyhype.core.SkyhypePlugin
import org.skyhype.core.util.ItemBuilder

open class SHItem(val plugin: SkyhypePlugin) : Listener {

    annotation class Info(
        val name: String,
        val material: Material,
        val amount: Int,
        val lore: Array<String>,
        val setNBT: Boolean,
        val glowing: Boolean,
        val itemFlags: Array<ItemFlag>
    )

    open fun register() {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    val info: Info
        get() = this.javaClass.getAnnotation(Info::class.java)

    var shItem: ItemStack = ItemStack(Material.AIR)
        get() {
            field = ItemBuilder()
                .setName(this.info.name)
                .setGlowing(this.info.glowing)
                .setLore(this.info.lore.toList())
                .addFlags(*this.info.itemFlags)
                .setMaterial(this.info.material)
                .setAmount(this.info.amount)
                .build()

            if (this.info.setNBT) {
                val craftItem = CraftItemStack.asNMSCopy(field)
                craftItem.orCreateTag.setString("ItemType", ChatColor.stripColor(this.info.name.toLowerCase()))
                field = CraftItemStack.asBukkitCopy(craftItem)
            }

            return field
        }
}