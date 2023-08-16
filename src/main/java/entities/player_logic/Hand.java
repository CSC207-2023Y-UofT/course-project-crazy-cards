package entities.player_logic;

import entities.card_logic.Card;

import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> cards;

    /**
     * Construct a Hand instance given a list of Cards.
     * @param cards An ArrayList of Card objects set to be the Cards in this Hand.
     */
    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Add the given Card to this Hand.
     * @param card The Card to be added to this Hand.
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

    /**
     * Remove the given Card from this Hand. This method is only called when a Player puts down a Card from their
     * Hand, which will always be non-empty. It should never throw an exception, hence why we don't check if
     * Card is in this.cards.
     * @param card The Card object to be removed.
     */
    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    /**
     * Get the Cards in this Hand.
     * @return A new ArrayList of Cards instance representing the Cards in this Hand.
     */
    public ArrayList<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
