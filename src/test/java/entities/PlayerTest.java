package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Game game;
    Player p1;
    Player p2;
    Deck deck;

    Hand h1;

    Hand h2;

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
        p1 = new ConcretePlayer(h1);
        p2 = new ConcretePlayer(h2);
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
    void testGetNumCards() {
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
    void testPlayCard() {
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
    void testSetHand() {
        p1.setHand(h2);
        Hand actualHand = p1.getHand();
        assertEquals(h2, actualHand);
    }

    /**
     * Test getCards() returns the cards in this Player's Hand.
     */
    @Test
    void testGetCards() {
        ArrayList<Card> playerCards = p1.getCards();
        ArrayList<Card> expected = h1.getCards();
        boolean equality = expected.equals(playerCards);
        assertTrue(equality);
    }
}
