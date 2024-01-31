package com.justxale.civilizations.commands;

import com.justxale.civilizations.Civilizations;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EventCommand implements CommandExecutor {
    Civilizations plugin;

    public EventCommand(Civilizations pluginInstance) {
        plugin = pluginInstance;
    }
    public String getEventPartName(String key) {
        return switch (key) {
            case "exploring" -> "Island Exploring";
            case "election" -> "Leader Election";
            case "building" -> "Base Building";
            case "meeting" -> "Leader Meeting";
            case "war" -> "War";
            default -> "";
        };
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        Component text = Component.text("Event ", NamedTextColor.WHITE)
                        .append(Component.text(getEventPartName(args[0]), NamedTextColor.GREEN)
                                .append(Component.text("", NamedTextColor.GREEN)

                                )
                        );
        sender.getServer().broadcast(text);
        return true;
    }
}
