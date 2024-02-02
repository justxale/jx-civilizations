package com.justxale.civilizations.util;

import com.justxale.civilizations.Civilizations;
import com.justxale.civilizations.scoreboard.TimerScoreboard;

import org.bukkit.Bukkit;


public class SBTimer {

    public int getEventTime(String key) {
        return switch (key) {
            case "exploring" -> 25 * 60;
            case "election", "meeting" -> 15 * 60;
            case "building" -> 30 * 60;
            case "war" -> -1;
            default -> 0;
        };
    }

    public void startTimer(Civilizations plugin, TimerScoreboard sb, String eventKey) {
        int eventTime = getEventTime(eventKey);
        if (eventTime > 0) {
            sb.setTime(eventTime);
            SBTimerTask task = new SBTimerTask(sb);
            Bukkit.getServer().getScheduler().runTaskTimer(plugin, task, 0L, 20L);
        }
    }
}
