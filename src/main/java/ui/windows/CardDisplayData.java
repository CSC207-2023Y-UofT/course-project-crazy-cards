package ui.windows;

import enums.Rank;
import enums.Suit;

public class CardDisplayData {
    private Suit suit;
    private Rank rank;
    private boolean justDrawn;

    public CardDisplayData(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        justDrawn = false;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isJustDrawn() {
        return justDrawn;
    }
}
