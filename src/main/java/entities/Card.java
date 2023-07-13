package entities;

public class Card {
    private final String suit;
    private final int value;
    private String specialEffect = null;

    public Card(String suit, int value, String specialEffect) {
        this.suit = suit;
        this.value = value;
        this.specialEffect = specialEffect;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getSpecialEffect() {
        return specialEffect;
    }
}
