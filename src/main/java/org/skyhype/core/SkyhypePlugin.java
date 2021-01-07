package org.skyhype.core;

import org.skyhype.core.manager.CommandManager;
import org.skyhype.core.manager.DataManager;
import org.skyhype.core.manager.ItemManager;
import xyz.oribuin.orilibrary.OriPlugin;

public class SkyhypePlugin extends OriPlugin {
    private static SkyhypePlugin instance;

    @Override
    public void enablePlugin() {
        instance = this;

        getServer().getScheduler().runTaskAsynchronously(this, () -> {
            this.getManager(CommandManager.class);
            this.getManager(DataManager.class);
            this.getManager(ItemManager.class);
        });

    }

    @Override
    public void disablePlugin() {

    }

    public static SkyhypePlugin getInstance() {
        return instance;
    }
}
