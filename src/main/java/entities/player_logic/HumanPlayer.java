package entities.player_logic;

public class HumanPlayer extends Player {
    private int losses;
    private int wins;

    /**
     * Construct a new HumanPlayer with the given name and Hand.
     * @param name A String representing the name of this Player.
     * @param hand The Hand of Cards given to this player.
     */
    public HumanPlayer(String name, Hand hand, int wins, int losses) {
        super(name, hand);
        this.wins = wins;
        this.losses = losses;
    }

    /**
     * Increase the number of wins for this Player.
     */
    public void incrementWins() {
        this.wins ++;
    }

    /**
     * Increase the number of losses for this Player.
     */
    public void incrementLosses() {
        this.losses ++;
    }

    /**
     * Get the number of wins of this HumanPlayer.
     * @return An int containing the number of wins of this HumanPlayer.
     */
    public int getWins() {
        return this.wins;
    }

    /**
     * Get the number of losses of this HumanPlayer.
     * @return An int containing the number of losses of this HumanPlayer.
     */
    public int getLosses() {
        return this.losses;
    }
}
