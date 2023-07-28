package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class GameTest {

    Game game;
    Player p1;
    Player p2;
    ArrayList<Player> players;
    Deck deck;

    /**
     * Initialize a Game with a Deck and Players for testing of Game.java methods.
     */
    @BeforeEach
    public void setUp() {
        deck = new StandardDeck();
        Hand h1 = new Hand(new ArrayList<>());
        Hand h2 = new Hand(new ArrayList<>());
        Hand[] hands = {h1, h2};
        for (Hand h: hands) {
            for(int i = 0; i < 4 ; i++) {
                h.addCard(deck.removeCardFromDeck());
            }
        }
        p1 = new HumanPlayer("sol", h1);
        p2 = new HumanPlayer("sab" , h2);
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        game = new Game(deck, players);
    }

    /**
     * Set each variable used in setUp to null to save memory.
     */
    @AfterEach
    public void tearDown() {
        p1 = null;
        p2 = null;
        game = null;
        players = null;
        deck = null;
    }

    /**
     * Test that the turn belongs to the first Player in the players ArrayList.
     */
    @Test
   public void testGetCurrentTurnFirst() {
        Player curTurn = game.getCurrentTurn();
        assertEquals(curTurn, game.getPlayers().get(0));
    }


    /**
     * Test that after a Player has played a Card, the current Card of the Game has been changed to the played Card.
     */
    @Test
    void testPutCardDownAndGetCurrentCard() {
        // Get some Card from p1's Hand and put it down directly (we typically call putCardDown from p1.playCard,
        // but we have skipped that to test putCardDown directly).
        Card toPutDown = p1.getCards().get(0);
        game.putCardDown(toPutDown);
        assertEquals(toPutDown, game.getCurrentCard());
        // assert that the GameDeck has gotten increased by one Card!!!
    }


    /**
     * Test that getPlayers() returns a list of all the Players in this Game.
     */
    @Test
    void testGetPlayers() {
        ArrayList<Player> toTest = game.getPlayers();
        assertEquals(players, toTest);
    }


    /**
     * Test that hasWinner(), when the Game doesn't have a winner, returns False.
     */
    @Test
    void testHasWinnerNotYet() {
        boolean winnerYet = game.hasWinner();
        assertFalse(winnerYet);
    }

    /**
     * Test that when a winner has been set, that getWinner() returns that Player.
     */
    @Test
    void testGetAndSetWinner() {
        game.setWinner(p1);
        assertEquals(p1, game.getWinner());
    }

    /**
     * Test isValidCard() returns True when given a Card of same suit.
     */
    @Test
    void testIsValidCardSameSuit() {
        game.putCardDown(new Card("Spades", "9"));
        Card toTest = new Card("Spades", "7");
        boolean validity = game.isValidCard(toTest);
        assertTrue(validity);
    }

    /**
     * Test isValidCard() returns True when given a Card of same value.
     */
    @Test
    void testIsValidCardSameValue() {
        game.putCardDown(new Card("Hearts", "9"));
        Card toTest = new Card("Spades", "9");
        boolean validity = game.isValidCard(toTest);
        assertTrue(validity);
    }

    /**
     * Test isValidCard() returns false when given an invalid Card.
     */
    @Test
    void testIsValidCardInvalid() {
        game.putCardDown(new Card("Hearts", "9"));
        Card toTest = new Card("Spades", "King");
        boolean validity = game.isValidCard(toTest);
        assertFalse(validity);
    }

    /**
     * Test that with a currentCard of different suit and number, any card with number 8 is valid.
     */
    @Test
    void testIsValidCardCrazy8() {
        game.putCardDown(new Card("Hearts", "9"));
        Card toTest = new Card("Spades", "8");
        boolean validity = game.isValidCard(toTest);
        assertTrue(validity);
    }

    /**
     * Test that getGameDeck returns the Deck used in this Game.
     */
    @Test
    void testGetGameDeck() {
        Deck toTest = game.getGameDeck();
        assertEquals(deck, toTest);
    }

}