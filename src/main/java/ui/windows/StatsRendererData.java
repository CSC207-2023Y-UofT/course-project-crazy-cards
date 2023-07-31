package ui.windows;

/**
 * Stores data to be displayed by a StatsRenderer.
 */
public class StatsRendererData {
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int longestWinStreak;

    /**
     * Create a StatsRendererData with default values.
     */
    public StatsRendererData() {
        this.name = "";
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.longestWinStreak = 0;
    }

    /**
     * Create a StatsRendererData with specified values.
     * @param name the name of the user
     * @param gamesPlayed the number of games played by the user
     * @param gamesWon the number of games won by the user
     * @param longestWinStreak the longest win streak of the user
     */
    public StatsRendererData(String name, int gamesPlayed, int gamesWon, int longestWinStreak) {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.longestWinStreak = longestWinStreak;
    }

    /**
     * Returns the name of the stored user.
     * @return the name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the stored user.
     * @param name the name as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of games played by the stored user.
     * @return the number of games played as an integer
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the number of games played by the stored user.
     * @param gamesPlayed the number of games played as an integer
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Returns the number of games won by the stored user.
     * @return the number of games won as an integer
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets the number of games won by the stored user.
     * @param gamesWon the number of games won as an integer
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Returns the longest win streak of the stored user.
     * @return the longest win streak as an integer
     */
    public int getLongestWinStreak() {
        return longestWinStreak;
    }

    /**
     * Sets the longest win streak of the stored user.
     * @param longestWinStreak the longest win streak as an integer
     */
    public void setLongestWinStreak(int longestWinStreak) {
        this.longestWinStreak = longestWinStreak;
    }
}
