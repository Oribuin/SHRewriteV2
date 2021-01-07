package org.skyhype.core.gui

import dev.rosewood.guiframework.GuiFactory
import dev.rosewood.guiframework.GuiFramework
import dev.rosewood.guiframework.gui.ClickAction
import dev.rosewood.guiframework.gui.GuiSize
import dev.rosewood.guiframework.gui.screen.GuiScreen
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.skyhype.core.SkyhypePlugin

class HearthstoneGUI(private val plugin: SkyhypePlugin, private val player: Player) : Listener {

    private val container = GuiFactory.createContainer()
        .setTickRate(1)

    private val framework = GuiFramework.instantiate(plugin)

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    private fun buildGui() {
        this.container.addScreen(menu())
        this.framework.guiManager.registerGui(container)

    }

    private fun menu(): GuiScreen {

        val screen = GuiFactory.createScreen(container, GuiSize.DYNAMIC)
        screen.title = "Hearthstone"

        screen.addButtonAt(
            2, GuiFactory.createButton()
                .setIcon(Material.EMERALD)
                .setName("&6Voting Information")
                .setLore("&aClick here to open the", "&avoting information menu.")
                .setClickAction({ this.executeCommand("vote"); ClickAction.NOTHING })
        )

        screen.addButtonAt(
            4, GuiFactory.createButton()
                .setIcon(Material.GRASS_BLOCK)
                .setName("&6Skyblock Menu")
                .setLore("&aClick here to open the", "&askyblock menu.")
                .setClickAction({ this.executeCommand("island"); ClickAction.NOTHING })
        )

        screen.addButtonAt(
            6, GuiFactory.createButton()
                .setIcon(Material.NETHER_STAR)
                .setName("&6Donation Information")
                .setLore("&aClick here to open the", "&adonation information menu.")
                .setClickAction({ this.executeCommand("donate"); ClickAction.NOTHING })
        )

        screen.addButtonAt(
            10, GuiFactory.createButton()
                .setIcon(Material.WHEAT)
                .setName("&6Sell Farmable goods")
                .setLore("&aClick here to open the", "&asell farmable goods menu.")
                .setClickAction({ this.executeCommand("sell"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            12, GuiFactory.createButton()
                .setIcon(Material.DIAMOND)
                .setName("&6Auction House")
                .setLore("&aClick here to open the", "&aauction house menu.")
                .setClickAction({ this.executeCommand("ah"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            14, GuiFactory.createButton()
                .setIcon(Material.REDSTONE)
                .setName("&6Hype Shop")
                .setLore("&aClick here to open the", "&ahype shop menu.")
                .setClickAction({ this.executeCommand("hype shop"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            16, GuiFactory.createButton()
                .setIcon(Material.CHEST)
                .setName("&6Daily Spoils")
                .setLore("&aClick here to open the", "&adaily spoils menu.")
                .setClickAction({ this.executeCommand("spoils"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            18, GuiFactory.createButton()
                .setIcon(Material.BONE)
                .setName("&6Pets")
                .setLore("&aClick here to open the", "&apets menu.")
                .setClickAction({ this.executeCommand("pets"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            20, GuiFactory.createButton()
                .setIcon(Material.DIAMOND_HORSE_ARMOR)
                .setName("&6Mounts")
                .setLore("&aClick here to open the", "&amounts menu.")
                .setClickAction({ this.executeCommand("mounts"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            22, GuiFactory.createButton()
                .setIcon(Material.ZOMBIE_HEAD)
                .setName("&6Disguises")
                .setLore("&aClick here to open the", "&adisguises menu.")
                .setClickAction({ this.executeCommand("disguises"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            24, GuiFactory.createButton()
                .setIcon(Material.BLAZE_POWDER)
                .setName("&6Effects")
                .setLore("&aClick here to open the", "&aeffects menu.")
                .setClickAction({ this.executeCommand("effects"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            26, GuiFactory.createButton()
                .setIcon(Material.PURPLE_STAINED_GLASS)
                .setLore("&aClick here to open the", "&awarps menu.")
                .setClickAction({ this.executeCommand("warps"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            28, GuiFactory.createButton()
                .setIcon(Material.CLOCK)
                .setLore("&aClick here to open the", "&awarps menu.")
                .setClickAction({ this.executeCommand("ptime"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            30, GuiFactory.createButton()
                .setIcon(Material.END_PORTAL_FRAME)
                .setName("&6Teleport to Spawn")
                .setLore("&aClick here to teleport to", "&aspawn.")
                .setClickAction({ this.executeCommand("spawn"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            32, GuiFactory.createButton()
                .setIcon(Material.QUARTZ)
                .setName("&6Flight Trainer")
                .setLore("&aClick here to open the", "&aflight trainer menu.")
                .setClickAction({ this.executeCommand("trainer"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            34, GuiFactory.createButton()
                .setIcon(Material.PAPER)
                .setName("&6Statistics")
                .setLore("&aClick here to open the", "&astatistics menu.")
                .setClickAction({ this.executeCommand("stats"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            36, GuiFactory.createButton()
                .setIcon(Material.BOOK)
                .setName("&6Help & Tutorials")
                .setLore("&aClick here to open the", "&ahelp & tutorials menu.")
                .setClickAction({ this.executeCommand("help"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            38, GuiFactory.createButton()
                .setIcon(Material.LEATHER)
                .setName("&6Achievements")
                .setLore("&aClick here to open the", "&aachievements menu.")
                .setClickAction({ this.executeCommand("achivements"); ClickAction.NOTHING })
        )
        screen.addButtonAt(
            40, GuiFactory.createButton()
                .setIcon(Material.WRITABLE_BOOK)
                .setName("&6Sever Rules")
                .setLore("&aClick here to open the", "&aserver rules menu.")
                .setClickAction({ this.executeCommand("rules"); ClickAction.NOTHING })
        )


        return screen
    }

    fun openMenu() {
        if (this.isInvalid)
            this.buildGui()

        container.openFor(player)
    }

    private fun executeCommand(cmd: String) {
        Bukkit.dispatchCommand(player, cmd)
    }

    private val isInvalid: Boolean
        get() = !this.framework.guiManager.activeGuis.contains(container)
}

