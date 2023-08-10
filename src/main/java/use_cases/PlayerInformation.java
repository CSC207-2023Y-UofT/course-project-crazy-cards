package use_cases;

public class PlayerInformation {
    private String name;
    private int wins;
    private int losses;

    /**
     * Construct an instance of PlayerInformation.
     */
    public PlayerInformation(String name, int wins, int losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }

    /**
     * Get the name from this player's PlayerInformation.
     * @return A String containing this player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the number of wins from this player's PlayerInformation.
     * @return An int containing this player's number of wins.
     */
    public int getWins() {
        return this.wins;
    }

    /**
     * Get the number of losses from this player's PlayerInformation.
     * @return An int containing this player's number of losses.
     */
    public int getLosses() {
        return this.losses;
    }
}