package org.skyhype.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.skyhype.core.SkyhypePlugin;
import org.skyhype.core.gui.HearthstoneGUI;
import xyz.oribuin.orilibrary.OriPlugin;
import xyz.oribuin.orilibrary.command.OriCommand;
import static org.skyhype.core.util.HexUtils.colorify;

import java.util.List;

public class CmdHearthstone extends OriCommand {
    public CmdHearthstone(OriPlugin plugin) {
        super(plugin, "hearthstone");
    }

    @Override
    public void executeCommand(@NotNull CommandSender sender, @NotNull String[] args, @NotNull String label) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(colorify("&cOnly a player can execute this command."));
            return;
        }


        Player player = (Player) sender;
        new HearthstoneGUI((SkyhypePlugin) plugin, player).openMenu();
    }

    @Override
    public List<String> tabComplete(@NotNull CommandSender commandSender, String[] strings) {
        return null;
    }
}
