package entities;

import enums.Rank;
import enums.Suit;

public class Card {
    private final Suit suit;
    private final Rank rank;
    private String specialEffect = null;

    /**
     * Construct a Card, giving it the provided suit and value. This constructor is used for regular cards.
     * @param suit  The suit of the card (i.e. Spades, Clubs, Hearts, Diamonds)
     * @param value The value of the card (i.e. 2 through 10, or J, Q, K or A)
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Construct a Card, giving it the provided suit, value, and specialEffect.
     * @param suit          The suit of the card (i.e. Spades, Clubs, Hearts, Diamonds)
     * @param value         The value of the card (i.e. 2 through 10, or J, Q, K or A)
     * @param specialEffect The special effect given to the card, if any is provided (e.g. changing the current suit)
     */
    public Card(Suit suit, Rank rank, String specialEffect) {
        this.suit = suit;
        this.rank = rank;
        this.specialEffect = specialEffect;
    }

    /**
     * Getter method for the suit of the card, since the suit is a private attribute.
     * @return The suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Getter method for the value of the card, since the value is a private attribute.
     * @return The value of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Getter method for the special effect of the card, since the special effect is a private attribute.
     * @return The special effect of the card.
     */
    public String getSpecialEffect() {
        return specialEffect;
    }
}
