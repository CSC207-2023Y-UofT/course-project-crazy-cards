package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card regCard;
    Card specCard;

    /**
     * Set up Card objects for testing.
     */
    @BeforeEach
    void setUp() {
        regCard = new Card("Spades", "10");
        specCard = new Card("Clubs", "8", "Crazy8");
    }

    /**
     * Free memory by setting the objects made in setUp() to null.
     */
    @AfterEach
    void tearDown() {
        regCard = null;
        specCard = null;
    }

    /**
     * Test getSuit() returns the suit of the Card.
     */
    @Test
    void testGetSuit() {
        String expected = "Spades";
        String actual = regCard.getSuit();
        assertEquals(expected, actual);
    }

    /**
     * Test getValue() returns the value of the Card.
     */
    @Test
    void testGetValue() {
        String expected = "10";
        String actual = regCard.getValue();
        assertEquals(expected, actual);
    }

    /**
     * Test getSpecialEffect returns the special effect of this Card if it has any.
     */
    @Test
    void testGetSpecialEffect() {
        String expected = "Crazy8";
        String actual = specCard.getSpecialEffect();
        assertEquals(expected, actual);
    }
}