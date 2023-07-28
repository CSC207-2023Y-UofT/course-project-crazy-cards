package entities;


public class HumanPlayer extends Player {

    private int losses;

    private int wins;


    /**
     * Construct a new HumanPlayer with the given name and Hand.
     * @param name A String representing the name of this Player.
     * @param hand The Hand of Cards given to this player.
     */
    public HumanPlayer(String name, Hand hand) {
        super(name, hand);
    }

    /**
     * Get the wins and losses of this HumanPlayer/
     * @return An ArrayList of Integers containing the wins as the first index and losses as the second index.
     */
    public int[] getStats() {
        int[] winsLosses = {this.wins, this.losses};
        return winsLosses;
    }

    /**
     * Increase the win statistic of this Player.
     */
    public void incrementWins() {
        this.wins ++;
    }

    /**
     * Increase the loss statistic of this Player.
     */
    public void incrementLosses() {
        this.losses ++;
    }

}
