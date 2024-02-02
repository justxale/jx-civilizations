package com.justxale.civilizations;

import com.justxale.civilizations.command.EventCommand;
import com.justxale.civilizations.command.LeaderCommand;
import com.justxale.civilizations.completer.EventCommandCompleter;
import com.justxale.civilizations.completer.LeaderCommandCompleter;
import com.justxale.civilizations.scoreboard.TimerScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Civilizations extends JavaPlugin implements Listener {
    public TimerScoreboard curBoard;
    public List<Player> leaders = new ArrayList<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("event")).setExecutor(new EventCommand(this));
        Objects.requireNonNull(this.getCommand("event")).setTabCompleter(new EventCommandCompleter(this));
        Objects.requireNonNull(this.getCommand("leader")).setExecutor(new LeaderCommand(this));
        Objects.requireNonNull(this.getCommand("leader")).setTabCompleter(new LeaderCommandCompleter(this));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (curBoard != null) {
            curBoard.onPlayerJoin(event);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
    }

}