package entities;

public class Card {
    private final String suit;
    private final String value;
    private String specialEffect = null;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    public Card(String suit, String value, String specialEffect) {
        this.suit = suit;
        this.value = value;
        this.specialEffect = specialEffect;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public String getSpecialEffect() {
        return specialEffect;
    }
}
