package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Rank;
import enums.Suit;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card regCard;
    private Card specCard;

    /**
     * Set up Card objects for testing.
     */
    @BeforeEach
    public void setUp() {
        regCard = new Card(Suit.SPADE, Rank.TEN);
        specCard = new Card(Suit.CLUB, Rank.EIGHT, "Crazy8");
    }

    /**
     * Free memory by setting the objects made in setUp() to null.
     */
    @AfterEach
    public void tearDown() {
        regCard = null;
        specCard = null;
    }

    /**
     * Test getSuit() returns the suit of the Card.
     */
    @Test
    public void testGetSuit() {
        Suit expected = Suit.SPADE;
        Suit actual = regCard.getSuit();
        assertEquals(expected, actual);
    }

    /**
     * Test getValue() returns the value of the Card.
     */
    @Test
    public void testGetValue() {
        Rank expected = Rank.TEN;
        Rank actual = regCard.getRank();
        assertEquals(expected, actual);
    }

    /**
     * Test getSpecialEffect returns the special effect of this Card if it has any.
     */
    @Test
    public void testGetSpecialEffect() {
        String expected = "Crazy8";
        String actual = specCard.getSpecialEffect();
        assertEquals(expected, actual);
    }
}