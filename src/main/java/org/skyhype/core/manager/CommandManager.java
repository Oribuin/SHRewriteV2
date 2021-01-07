package org.skyhype.core.manager;

import org.reflections.Reflections;
import org.skyhype.core.SkyhypePlugin;
import xyz.oribuin.orilibrary.OriPlugin;
import xyz.oribuin.orilibrary.command.OriCommand;
import xyz.oribuin.orilibrary.manager.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommandManager extends Manager {
    private final List<OriCommand> commands = new ArrayList<>();

    public CommandManager(OriPlugin plugin) {
        super(plugin);
    }

    @Override
    public void enable() {
        this.registerCommands();
        commands.forEach(OriCommand::register);
    }

    @Override
    public void disable() {
        this.commands.clear();
    }

    private void registerCommands() {
        SkyhypePlugin main = (SkyhypePlugin) getPlugin();
        Reflections reflections = new Reflections("org.skyhype.core.command");
        reflections.getSubTypesOf(OriCommand.class).forEach(aClass -> {
            try {
                Constructor<? extends OriCommand> constructor = aClass.getConstructor(main.getClass());
                OriCommand command = constructor.newInstance(main);
                commands.add(command);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }
}
