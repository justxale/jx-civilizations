package com.justxale.civilizations.scoreboard;

import com.justxale.civilizations.Civilizations;
import com.justxale.civilizations.util.SBTimer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class TimerScoreboard {
    private final Civilizations plugin;
    private final Team team;
    private final Objective objective;
    private SBTimer timer = null;
    private final String eventKey;

    public static String getNextPart(String eventName) {
        return switch (eventName) {
            case "exploring" -> "election";
            case "election" -> "building";
            case "building" -> "meeting";
            case "meeting" -> "war";
            default -> "";
        };
    }

    public static String getEventPartName(String key) {
        return switch (key) {
            case "exploring" -> "Изучение острова";
            case "election" -> "Выбор лидера";
            case "building" -> "Стройка базы";
            case "meeting" -> "Встреча лидеров";
            case "war" -> "Война";
            default -> "";
        };
    }

    public TimerScoreboard(Civilizations pluginInstance, String eventKey) {
        plugin = pluginInstance;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        team = board.registerNewTeam(eventKey);
        this.eventKey = eventKey;

        for(Player online : Bukkit.getOnlinePlayers()){
            team.addPlayer(online);
            online.setScoreboard(board);
            plugin.getLogger().info("Set Scoreboard for " + online);
        }

        objective = board.registerNewObjective(
                "Осталось времени:", Criteria.DUMMY, Component.text(
                        getEventPartName(eventKey),
                        NamedTextColor.GREEN
                )
        );
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore("Осталось времени:").setScore(1);

        this.timer = new SBTimer();
        start(this, eventKey);
    }

    public void cancel() {
        Bukkit.getServer().getScheduler().cancelTasks(this.plugin);
    }

    public void onPartEnd(String eventKey) {
        String nextPart = getNextPart(eventKey);
        if (Objects.equals(nextPart, "war")) {
            for(Player online : Bukkit.getOnlinePlayers()){
                Component component = Component.text("Пора воевать!", NamedTextColor.GOLD);
                Component subComponent = Component.empty();
                Title titleComponent = Title.title(component, subComponent);
                online.showTitle(titleComponent);

            }
        }
        plugin.curBoard = new TimerScoreboard(plugin, nextPart);
    }

    private void start(TimerScoreboard board, String eventKey) {
        this.timer.startTimer(plugin, board, eventKey);
    }

    public void onPlayerJoin(PlayerJoinEvent event) {
        team.addPlayer(event.getPlayer());
    }

    public void setTime(int value) {
        Score score = objective.getScore("Осталось времени:");
        score.setScore(value);
        if (score.getScore() <= 0) {
            this.cancel();
            onPartEnd(this.eventKey);
        }
    }

    public int getTime() {
        return objective.getScore("Осталось времени:").getScore();
    }
}
