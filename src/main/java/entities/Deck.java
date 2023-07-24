package entities;

// An interface with the method signatures of a Deck. An interface is used as future users of the program
// may want to implement their own non-standard deck of Cards.
public interface Deck {

    void addCardToDeck(Card card);

    void removeCardFromDeck();
}
