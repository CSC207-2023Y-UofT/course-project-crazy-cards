package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardDeckTest {

    private Card toAdd;
    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new StandardDeck();
        toAdd = new Card("Tests", "17");
    }

    /**
     * Test that the deck increases in size when a Card is added to it.
     */
    @Test
    public void testAddCardToDeck() {
        int deckSize = deck.getCards().size();
        deck.addCardToDeck(toAdd);
        assertEquals(deckSize + 1, deck.getCards().size());
    }

    /**
     * Test the deck decreases in size when a Card is added to it.
     */
    @Test
    public void testRemoveCardFromDeck() {
        int expected = deck.getCards().size() -  1;
        deck.removeCardFromDeck();
        assertEquals(expected, deck.getCards().size());
    }

    /**
     * Test getCards returns an ArrayList that is the expected size of Deck.
     */
    @Test
    public void testGetCards() {
        int expected = 52;
        int actual = deck.getCards().size();
        assertEquals(expected, actual);
    }
}