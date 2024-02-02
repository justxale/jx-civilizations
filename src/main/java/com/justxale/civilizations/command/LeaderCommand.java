package com.justxale.civilizations.command;

import com.justxale.civilizations.Civilizations;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LeaderCommand implements CommandExecutor {
    Civilizations plugin;


    public LeaderCommand(Civilizations pluginInstance) {
        plugin = pluginInstance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            Player player = Bukkit.getServer().getPlayer(args[0]);
            plugin.leaders.add(player);

            assert player != null;
            player.setGlowing(true);
            sender.sendMessage(player.getName() + " зарегестрирован как Лидер команды");
            return true;
        }
        return false;
    }
}
