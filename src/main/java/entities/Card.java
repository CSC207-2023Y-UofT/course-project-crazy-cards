package entities;

public class Card {
    private final String suit;
    private final String value;
    private String specialEffect = null;

    /**
     * Construct a Card, giving it the provided suit and value. This constructor is used for regular cards.
     * @param suit  The suit of the card (i.e. Spade, Club, Heart, Diamond)
     * @param value The value of the card (i.e. 2 through 10, or Jack, Queen, King or Ace)
     */
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Construct a Card, giving it the provided suit, value, and specialEffect.
     * @param suit          The suit of the card (i.e. Spade, Club, Heart, Diamond)
     * @param value         The value of the card (i.e. 2 through 10, or Jack, Queen, King or Ace)
     * @param specialEffect The special effect given to the card, if any is provided (e.g. changing the current suit)
     */
    public Card(String suit, String value, String specialEffect) {
        this.suit = suit;
        this.value = value;
        this.specialEffect = specialEffect;
    }

    /**
     * Getter method for the suit of the card, since the suit is a private attribute.
     * @return The suit of the card.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Getter method for the value of the card, since the value is a private attribute.
     * @return The value of the card.
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter method for the special effect of the card, since the special effect is a private attribute.
     * @return The special effect of the card.
     */
    public String getSpecialEffect() {
        return specialEffect;
    }
}
