package controllers.data_objects;

/**
 * Stores data to be displayed by a StatsDisplay.
 */
public class StatsDisplayData {
    private String name;
    private int gamesWon;
    private int gamesLost;

    /**
     * Create a StatsDisplayData with default values.
     */
    public StatsDisplayData() {
        this.name = "";
        this.gamesWon = 0;
        this.gamesLost = 0;
    }

    /**
     * Create a StatsDisplayData with specified values.
     * @param name      the name of the user
     * @param gamesWon  the number of games won by the user
     * @param gamesLost the number of games lost by the user
     */
    public StatsDisplayData(String name, int gamesWon, int gamesLost) {
        this.name = name;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
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
     * Returns the number of games won by the stored user.
     * @return the number of games won as an integer
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Returns the number of games lost by the stored user.
     * @return the number of games lost as an integer
     */
    public int getGamesLost() {
        return gamesLost;
    }
}