package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardDeckTest {

    Card toAdd;
    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new StandardDeck();
        toAdd = new Card("Tests", "17");
    }

    /**
     * Test that the deck increases in size when a Card is added to it.
     */
    @Test
    void testAddCardToDeck() {
        int deckSize = deck.getCards().size();
        deck.addCardToDeck(toAdd);
        assertEquals(deckSize + 1, deck.getCards().size());
    }

    /**
     * Test the deck decreases in size when a Card is added to it.
     */
    @Test
    void testRemoveCardFromDeck() {
        int expected = deck.getCards().size() -  1;
        deck.removeCardFromDeck();
        assertEquals(expected, deck.getCards().size());
    }

    /**
     * Test getCards returns an ArrayList that is the expected size of Deck.
     */
    @Test
    void testGetCards() {
        int expected = 52;
        int actual = deck.getCards().size();
        assertEquals(expected, actual);
    }
}