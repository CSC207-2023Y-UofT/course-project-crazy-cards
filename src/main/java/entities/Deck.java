package entities;

import java.util.ArrayList;

// An interface with the method signatures of a Deck. An interface is used as future users of the program
// may want to implement their own non-standard deck of Cards.
public interface Deck {

    /**
     * Add a given card to the ArrayList that holds the current deck.
     * @param card The instance of the Card class that is being added to the current deck.
     */
    void addCardToDeck(Card card);

    /**
     * Remove a card from the current deck.
     * @return A Card instance that has been removed from the deck.
     */
    Card removeCardFromDeck();

    /**
     * Get all the cards in this Deck.
     * @return An ArrayList of the Cards in this Deck.
     */
    ArrayList<Card> getCards();
}
