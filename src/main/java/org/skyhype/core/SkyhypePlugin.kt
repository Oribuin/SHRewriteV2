package org.skyhype.core

import xyz.oribuin.orilibrary.OriPlugin
import org.skyhype.core.SkyhypePlugin
import java.lang.Runnable
import org.skyhype.core.manager.DataManager
import org.skyhype.core.manager.ItemManager
import org.skyhype.core.listener.JoinEvents
import org.skyhype.core.manager.CommandManager

class SkyhypePlugin : OriPlugin() {
    override fun enablePlugin() {

        server.scheduler.runTaskAsynchronously(this, Runnable {
            getManager(CommandManager::class.java)
            getManager(DataManager::class.java)
            getManager(ItemManager::class.java)
        })

        // Register Listeners
        JoinEvents(this)
    }

    override fun disablePlugin() {}
}