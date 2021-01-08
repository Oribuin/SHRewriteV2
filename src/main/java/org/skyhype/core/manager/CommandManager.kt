package org.skyhype.core.manager

import org.reflections.Reflections
import org.skyhype.core.SkyhypePlugin
import xyz.oribuin.orilibrary.OriPlugin
import xyz.oribuin.orilibrary.command.OriCommand
import xyz.oribuin.orilibrary.manager.Manager
import java.util.*
import java.util.function.Consumer

class CommandManager(plugin: OriPlugin) : Manager(plugin) {
    private val commands = mutableListOf<OriCommand>()

    override fun enable() {
        this.registerCommands()
        commands.forEach{ it.register() }
    }

    override fun disable() {
        commands.clear()
    }

    private fun registerCommands() {
        val main = plugin as SkyhypePlugin
        val reflections = Reflections("org.skyhype.core.command")
        reflections.getSubTypesOf(OriCommand::class.java).forEach {
            val constructor = it.getConstructor(main.javaClass)
            val command = constructor.newInstance(main)
            commands.add(command)
        }
    }
}