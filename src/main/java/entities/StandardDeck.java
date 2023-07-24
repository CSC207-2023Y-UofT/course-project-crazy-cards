package entities;

import java.util.*;

// A StandardDeck containing 52 Cards, no Jokers
public class StandardDeck implements Deck {

    private ArrayList<Card> cards = new ArrayList<>();

    private static String[] suits = {"Spades", "Hearts", "Diamond", "Clubs"};
    private static String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    /**
     * Construct an ArrayList that contains a standard 52 card deck that will be used for the game.
     */
    public StandardDeck() {
        for (String suit: suits) {
            for (String value: values) {
                this.cards.add(new Card(suit, value));
            }
        }
    }

    /**
     * Add the given card to the end of the ArrayList containing the deck of cards in play.
     * @param card The instance of the Card class that is being added to the current deck.
     */
    public void addCardToDeck(Card card) {
        this.cards.add(card);
    }

    public void removeCardFromDeck(Card card) {
        this.cards.remove(card);
    }

}
