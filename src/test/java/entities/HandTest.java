package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    Hand h1;
    Card toAdd;

    ArrayList<Card> cards;

    /**
     * Set up Hand and Card objects for testing.
     */
    @BeforeEach
    void setUp() {
        Deck deck = new StandardDeck();
        cards = new ArrayList<>();
        for(int i = 0; i < 4 ; i++) {
            cards.add(deck.removeCardFromDeck());
        }
        h1 = new Hand(cards);
        toAdd = new Card("Tests", "17");
    }

    /**
     * Set each created object in setUp to null to free memory.
     */
    @AfterEach
    void tearDown() {
        h1 = null;
        toAdd = null;
        cards = null;
    }


    /**
     * Test addCard() adds the given Card to the Hand. The given Card will be a nonsense Card, with made up
     * suit and value such that it does not match any Card in the Deck.
     */
    @Test
    void testAddCard() {
        // First assert the Hand is not empty.
        ArrayList<Card> empty = new ArrayList<>();
        assertNotEquals(empty, h1.getCards());

        h1.addCard(toAdd);
        boolean containOrNot = h1.getCards().contains(toAdd);
        assertTrue(containOrNot);

    }

    /**
     * Test removeCard() removes a given Card from the Hand.
     */
    @Test
    void testRemoveCard() {
        Card toAdd = new Card("Tests", "17");
        h1.addCard(toAdd);
        h1.removeCard(toAdd);
        assertFalse(h1.getCards().contains(toAdd));

    }

    /**
     * Test getCards() contains all the Cards in this Hand.
     */
    @Test
    void testGetCards() {
        assertEquals(cards, h1.getCards());
    }
}