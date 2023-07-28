package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Game game;
    private Player p1;
    private Player p2;
    private Deck deck;
    private Hand h1;
    private Hand h2;

    /**
     * Initialize a Game with a Deck and Players for testing of Player.java methods.
     */
    @BeforeEach
    public void setUp() {
        deck = new StandardDeck();
        h1 = new Hand(new ArrayList<>());
        h2 = new Hand(new ArrayList<>());
        Hand[] hands = {h1, h2};
        for (Hand h: hands) {
            for(int i = 0; i < 4 ; i++) {
                h.addCard(deck.removeCardFromDeck());
            }
        }
        p1 = new ConcretePlayer("p1", h1);
        p2 = new ConcretePlayer("p2", h2);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        game = new Game(deck, players);
        Card firstGameCard = new Card("Spades", "7");
        game.putCardDown(firstGameCard);
    }

    /**
     * Set each variable used in setUp to null to save memory.
     */
    @AfterEach
    public void tearDown() {
        p1 = null;
        p2 = null;
        game = null;
        deck = null;
        h1 = null;
        h2 = null;
    }

    /**
     * Test getNumCard() returns the Player's actual number of Cards in their Hand.
     */
    @Test
    public void testGetNumCards() {
        int expected = 4;
        int actual = p1.getNumCards();
        assertEquals(expected, actual);
    }

    /**
     * Test playCard() sets the played Card as the current Card in the Game, and the played Card is removed from
     * the Player's Hand.
     * Note that the played Card may not necessarily be valid as that is handled in the Use Case classes.
     */
    @Test
    public void testPlayCard() {
        int expectedDeckSize = deck.getCards().size() + 1;
        Card toPutDown = p1.getCards().get(0);
        p1.playCard(game, toPutDown);
        // Test the Deck has increased by 1 card.
        int actualDeckSize = deck.getCards().size();
        assertEquals(expectedDeckSize, actualDeckSize);
        // Test the Card is not in the Hand anymore.
        boolean inHand = p1.getCards().contains(toPutDown);
        assertFalse(inHand);
        // Test the current Card in the Game is the put down Card.
        Card isCurrentCard = game.getCurrentCard();
        assertEquals(toPutDown, isCurrentCard);
    }

    /**
     * Test setHand() sets the Player's Hand to the given Hand.
     */
    @Test
    public void testSetHand() {
        p1.setHand(h2);
        Hand actualHand = p1.getHand();
        assertEquals(h2, actualHand);
    }

    /**
     * Test getCards() returns the cards in this Player's Hand.
     */
    @Test
    public void testGetCards() {
        ArrayList<Card> playerCards = p1.getCards();
        ArrayList<Card> expected = h1.getCards();
        boolean equality = expected.equals(playerCards);
        assertTrue(equality);
    }

    /**
     * Test that getName() returns the name of the given Player.
     */
    @Test
    public void testGetName() {
        String actual = p1.getName();
        String expected = "p1";
        assertEquals(expected, actual);
    }
}
