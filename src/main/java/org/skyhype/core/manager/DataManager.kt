package org.skyhype.core.manager

import org.bukkit.OfflinePlayer
import org.skyhype.core.database.DatabaseConnector
import org.skyhype.core.database.SQLiteConnector
import xyz.oribuin.orilibrary.OriPlugin
import xyz.oribuin.orilibrary.manager.Manager
import xyz.oribuin.orilibrary.util.FileUtils

class DataManager(plugin: OriPlugin) : Manager(plugin) {
    var connector: DatabaseConnector? = null

    override fun enable() {
        try {
            FileUtils.createFile(plugin, "skyhype.db")
            connector = SQLiteConnector(plugin, "skyhype.db")
        } catch (exception: Exception) {
            plugin.logger.severe("Fatal error connecting to the database, Plugin has disabled itself.")
            exception.printStackTrace()
            plugin.server.pluginManager.disablePlugin(plugin)
        }

        this.createTables()
    }

    override fun disable() {}

    private fun createTables() {
        val tables = listOf(
            "CREATE TABLE IF NOT EXISTS hypeBalances (player VARCHAR(36), balance INTEGER, PRIMARY KEY(player))"
        )

        async { connector?.connect { connection -> tables.forEach { connection.prepareStatement(it).executeUpdate() } } }
    }

    /**
     * Gets the player's Hype Balance when called
     *
     * <p>
     *     Use this to get the current total of coins that the player as.
     * </p>
     *
     * @param player The player being checked
     * @return The hype balance of the player
     */
    fun getBalance(player: OfflinePlayer): Int {
        var amount = 0
        connector?.connect { connection ->
            connection.prepareStatement("SELECT balance FROM hypeBalances WHERE player = ?").use { statement ->
                statement.setString(1, player.uniqueId.toString())
                val result = statement.executeQuery()
                if (result.next()) {
                    amount = result.getInt(1)
                }
            }
        }

        return amount
    }

    /**
     * Sets a player's hype balance to the designed amount
     *
     * @param player The player having their balance set
     * @param balance The amount of hype they're being said
     */
    fun setBalance(player: OfflinePlayer, balance: Int) {
        async {
            connector?.connect { connection ->
                connection.prepareStatement("REPLACE INTO hypeBalances (player, balance) VALUES (?, ?)").use { statement ->
                    statement.setString(1, player.uniqueId.toString())
                    statement.setInt(2, balance)
                    statement.executeUpdate()
                }
            }
        }
    }

    /**
     * Asynchronizes the callback with it's own thread unless it is already not on the main thread
     *
     * @param asyncCallback The callback to run on a separate thread
     */
    private fun async(asyncCallback: Runnable) {
        plugin.server.scheduler.runTaskAsynchronously(plugin, asyncCallback)
    }
}