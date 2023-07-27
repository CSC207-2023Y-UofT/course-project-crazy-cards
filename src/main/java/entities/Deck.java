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

    Card removeCardFromDeck();

    ArrayList<Card> getCards();
}
