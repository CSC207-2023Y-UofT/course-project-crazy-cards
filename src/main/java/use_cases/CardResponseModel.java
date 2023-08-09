package use_cases;

import enums.Rank;
import enums.Suit;

public class CardResponseModel {
    private Suit suit;
    private Rank rank;

    public CardResponseModel(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
}
