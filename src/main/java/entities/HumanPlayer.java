package entities;


import java.util.*;

public class HumanPlayer extends Player {

    String name;

    private int losses;

    private int wins;


    /**
     * Construct a new HumanPlayer with the given name and Hand.
     * @param name A String representing the name of this Player.
     * @param hand The Hand of Cards given to this player.
     */
    public HumanPlayer(String name, Hand hand) {
        super(hand);
        this.name = name;
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

    /**
     * Get the name of this Player
     * @return a String containing the name of this HumanPlayer.
     */
    public String getName() {
        return this.name;
    }

}
