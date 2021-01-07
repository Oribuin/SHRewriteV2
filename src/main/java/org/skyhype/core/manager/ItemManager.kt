package org.skyhype.core.manager

import org.reflections.Reflections
import org.skyhype.core.obj.SHItem
import xyz.oribuin.orilibrary.OriPlugin
import xyz.oribuin.orilibrary.manager.Manager

// this is kinda useless but does it matter? no
class ItemManager(plugin: OriPlugin) : Manager(plugin) {
    private val items = mutableListOf<SHItem>()

    override fun enable() {
        this.addItems()

        items.forEach { it.register() }
    }

    override fun disable() {
        items.clear()
    }

    private fun addItems() {
        val reflection = Reflections("org.skyhype.core.item")

        reflection.getSubTypesOf(SHItem::class.java)
            .forEach {
                val constructor = it.getConstructor(this.javaClass)
                val item = constructor.newInstance(this)
                items.add(item)
            }
    }
}