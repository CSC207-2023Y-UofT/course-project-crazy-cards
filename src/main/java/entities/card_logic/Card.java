package entities.card_logic;

import enums.Rank;
import enums.Suit;

public class Card {
    private Suit suit;
    private Rank rank;
    private SpecialEffect effect;

    /**
     * Construct a Card, giving it the provided suit and value. This constructor is used for regular cards.
     * @param suit  The suit of the card (i.e. Spades, Clubs, Hearts, Diamonds)
     * @param rank The value of the card (i.e. 2 through 10, or J, Q, K or A)
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.effect = null;
    }

    /**
     * Construct a Card, giving it the provided suit, value, and specialEffect.
     * @param suit          The suit of the card (i.e. Spades, Clubs, Hearts, Diamonds)
     * @param rank         The value of the card (i.e. 2 through 10, or J, Q, K or A)
     * @param specialEffect The special effect given to the card, if any is provided (e.g. changing the current suit)
     */
    public Card(Suit suit, Rank rank, SpecialEffect specialEffect) {
        this.suit = suit;
        this.rank = rank;
        this.effect = specialEffect;
    }

    /**
     * Getter method for the suit of the card, since the suit is a private attribute.
     * @return The suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Sets the suit of the card.
     * @param suit The suit to set the card to.
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Getter method for the value of the card, since the value is a private attribute.
     * @return The value of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets the rank of the card.
     * @param rank The rank to set the card to.
     */
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    /**
     * Getter method for the special effect of the card, since the special effect is a private attribute.
     * @return The special effect of the card.
     */
    public SpecialEffect getSpecialEffect() {
        return this.effect;
    }
}
