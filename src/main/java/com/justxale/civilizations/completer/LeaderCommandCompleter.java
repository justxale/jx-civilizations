package com.justxale.civilizations.completer;

import com.justxale.civilizations.Civilizations;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderCommandCompleter implements TabCompleter {
    private final Civilizations plugin;

    public LeaderCommandCompleter(Civilizations pluginInstance) {
        plugin = pluginInstance;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        plugin.getLogger().info(Arrays.toString(args));
        plugin.getLogger().info(String.valueOf(args.length));

        ArrayList<String> argsList1 = new ArrayList<>();
        for(Player online : Bukkit.getOnlinePlayers()){
           argsList1.add(online.getName());
        }

        if (args.length == 1) {
            return argsList1;
        }
        return new ArrayList<>();
    }
}