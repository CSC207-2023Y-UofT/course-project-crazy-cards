package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HumanPlayerTest {

    private HumanPlayer p1;

    /**
     * Set up Player instances with a given Hand.
     */
    @BeforeEach
    public void setUp() {
        Deck deck = new StandardDeck();
        Hand h1 = new Hand(new ArrayList<>());
       for(int i = 0; i < 4 ; i++) {
           h1.addCard(deck.removeCardFromDeck());
            }
        p1 = new HumanPlayer("sol", h1, 0 , 0);
    }

    /**
     * Free memory by setting the created HumanPlayer object to null.
     */
    @AfterEach
    public void tearDown() {
        p1 = null;
    }

    /**
     * Test that getStats returns the number of wins and losses of a Player.
     */
    @Test
    public void testGetStatsNoWins() {
        int[] toTest = {p1.getWins(), p1.getLosses()};
        int[] expected = {0, 0};
        assertArrayEquals(expected, toTest);
    }

    /**
     * Test incrementWins() increases the number of wins of a Player and incrementLosses() increases the number of
     * losses of a HumanPlayer.
     */
    @Test
    public void testIncrementWinsAndLosses() {
        p1.incrementWins();
        p1.incrementWins();
        p1.incrementLosses();
        int[] toTest = {p1.getWins(), p1.getLosses()};
        int[] expected = {2, 1};
        assertArrayEquals(expected, toTest);
    }

}