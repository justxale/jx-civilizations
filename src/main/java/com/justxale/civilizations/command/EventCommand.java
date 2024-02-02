package com.justxale.civilizations.command;

import com.justxale.civilizations.Civilizations;
import com.justxale.civilizations.scoreboard.TimerScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class EventCommand implements CommandExecutor {
    Civilizations plugin;


    public EventCommand(Civilizations pluginInstance) {
        plugin = pluginInstance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        String eventKey = args[0];
        String action = args[1];

        switch (action) {
            case "start" -> plugin.curBoard = new TimerScoreboard(plugin, eventKey);
            case "end" -> {
                if (plugin.curBoard != null) {
                    plugin.curBoard.cancel();
                    String nextPart = TimerScoreboard.getNextPart(eventKey);
                    if (Objects.equals(nextPart, "war")) {
                        Bukkit.getServer().getScoreboardManager().getMainScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                    }
                    else if (!Objects.equals(nextPart, "")) {
                        plugin.curBoard = new TimerScoreboard(plugin, nextPart);
                    }
                    else {
                        Bukkit.getServer().getScoreboardManager().getMainScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                    }
                }
            }
            case "add" -> {
                System.out.println(Arrays.toString(args));
                if (plugin.curBoard != null && args.length == 3) {
                    try {
                        int timeToAdd = Integer.parseInt(args[2]);
                        plugin.curBoard.setTime(plugin.curBoard.getTime() + timeToAdd);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
