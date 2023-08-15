package entities.deck_logic;

import entities.card_logic.Card;

import java.util.ArrayList;

// An interface with the method signatures of a Deck. An interface is used as future users of the program
// may want to implement their own non-standard deck of Cards.
public interface Deck {

    /**
     * Add a given card to the ArrayList that holds the current Deck.
     * @param card The instance of the Card class that is being added to the current Deck.
     */
    void addCardToDeck(Card card);

    /**
     * Remove a card from the current Deck, from the bottom of the Deck.
     * @return A Card instance that has been removed from the Deck.
     */
    Card removeCardFromDeck();

    /**
     * Get all the cards in this Deck.
     * @return An ArrayList of the Cards in this Deck.
     */
    ArrayList<Card> getCards();

    /**
     * Remove a Card from the top of the Deck, as a Player would when picking up a Card in-game.
     * @return a Card from the top of the Deck.
     */
    Card playerPickUp();
}
