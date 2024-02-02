package com.justxale.civilizations.util;

import com.justxale.civilizations.scoreboard.TimerScoreboard;

public class SBTimerTask implements Runnable {
    private final TimerScoreboard scoreboard;

    public SBTimerTask(TimerScoreboard sb) {
        scoreboard = sb;
        System.out.println(scoreboard.getTime());
    }

    @Override
    public void run() {
        scoreboard.setTime(scoreboard.getTime() - 1);
    }
}
