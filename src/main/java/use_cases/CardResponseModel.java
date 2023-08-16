package use_cases;

import enums.Rank;
import enums.Suit;

/**
 * A class representing a playing card with a specific suit and rank.
 */
public class CardResponseModel {
    private final Suit suit;
    private final Rank rank;

    /**
     * Constructs a new card with the specified suit and rank.
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     */
    public CardResponseModel(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Gets the suit of the card.
     * @return the suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the rank of the card.
     * @return the rank of the card.
     */
    public Rank getRank() {
        return rank;
    }
}
