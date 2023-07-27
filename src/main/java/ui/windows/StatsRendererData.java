package ui.windows;

import javax.swing.*;

public class StatsRendererData {
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int longestWinStreak;

    public StatsRendererData(String name, int gamesPlayed, int gamesWon, int longestWinStreak) {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.longestWinStreak = longestWinStreak;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getLongestWinStreak() {
        return longestWinStreak;
    }
}
