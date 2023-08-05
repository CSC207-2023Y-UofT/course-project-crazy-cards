package use_cases;

import entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GameState.
 */
class GameStateTest {

    private Game game;
    private Player p1;
    private Player p2;
    private ArrayList<Player> players;
    private GameState gameState;

    /**
     * Initialize all objects needed to test GameState.
     */
    @BeforeEach
    public void setUp(){
        Deck deck = new StandardDeck();
        Hand h1 = new Hand(new ArrayList<>());
        Hand h2 = new Hand(new ArrayList<>());
        Hand[] hands = {h1, h2};
        for (Hand h: hands) {
            for(int i = 0; i < 4 ; i++) {
                h.addCard(deck.removeCardFromDeck());
            }
        }
        p1 = new HumanPlayer("sol", h1, 0 , 0);
        p2 = new HumanPlayer("sab" , h2, 0 , 0);
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        game = new Game(deck, players);
        gameState = new GameState(game);
    }

    /**
     * Set all objects created in setUp() to null to free memory.
     */
    @AfterEach
    public void tearDown() {
        gameState = null;
        p1 = null;
        p2 = null;
        game = null;
        players = null;
    }

    /**
     * Test whether GameState is properly updated when updateGameObserver is called.
     * This test method will also test the getter methods in GameState as well.
     */
    @Test
    public void testUpdateGameObserver() {
        // It is p1's turn, once the turn has been changed it will be p2's turn
        // the turn change and card placement will be separate, p1 should still have
        // the same amount of Cards
        game.changeCurrentTurn();
        Card toPutDown = new Card("Test", "17");
        game.putCardDown(toPutDown);
        // update GameState
        gameState.updateGameObserver(game);
        // Assert it is p2's turn
        assertEquals(p2, gameState.getCurrentPlayer());
        // Assert the current Card is toPutDown.
        assertEquals(toPutDown, gameState.getCurrentCard());
        // Assert the game has no winner yet.
        assertFalse(gameState.getHasWinner());
        // Assert p2's Cards are the same as when the Game was created.
        ArrayList<Card> expected = p2.getCards();
        ArrayList<Card> actual = gameState.getCurrentPlayerCards();
        assertEquals(expected, actual);
        // Assert p2 is not in the HashMap and that it contains p1's number of cards as a value.
        HashMap<Player, Integer> playerNumCardHashMap = gameState.getPlayersAndCards();
        assertFalse(playerNumCardHashMap.containsKey(p2));
        // Should only have one key in HashMap (p1), who should have 4 cards.
        assertEquals(1,playerNumCardHashMap.size());
        assertEquals(4, playerNumCardHashMap.get(p1));
    }

}