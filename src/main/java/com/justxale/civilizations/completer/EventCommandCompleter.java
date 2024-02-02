package com.justxale.civilizations.completer;

import com.justxale.civilizations.Civilizations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventCommandCompleter implements TabCompleter {
    private final Civilizations plugin;
    private final String[] argsList1 = {"exploring", "election", "building", "meeting", "war"};
    private final String[] argsList2 = {"start", "end", "add"};
    private final String[] argsList3 = {"1", "10", "20"};
    public EventCommandCompleter(Civilizations pluginInstance) {
        plugin = pluginInstance;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        plugin.getLogger().info(Arrays.toString(args));
        plugin.getLogger().info(String.valueOf(args.length));
        if (args.length == 1) {
            return new ArrayList<>(List.of(argsList1));
        }
        else if (args.length == 2) {
            return new ArrayList<>(List.of(argsList2));
        }
        if (args[1].equals("add") && args.length == 3) {
            return new ArrayList<>(List.of(argsList3));
        }
        return new ArrayList<>();
    }
}
