package ui.windows.game;

import enums.Rank;
import enums.Suit;

public class CardDisplayData {
    private Suit suit;
    private Rank rank;
    private boolean justDrawn;

    /**
     * Constructor for the CardDisplayData that will be used to generate GUI components to display.
     * @param suit The suit of the card.
     * @param rank The rank/value of the card.
     */
    public CardDisplayData(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        justDrawn = false;
    }

    /**
     * Getter method for the suit of the card.
     * @return The suit of the card, as an enum.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Getter method for the rank/value of the card.
     * @return The rank/value of the card, as an enum.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns whether the card to be displayed was just drawn or not.
     * @return If the card is just drawn, return true, otherwise return false.
     */
    public boolean isJustDrawn() {
        return justDrawn;
    }
}
