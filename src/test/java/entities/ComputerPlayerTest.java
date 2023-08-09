package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Rank;
import enums.Suit;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    private ComputerPlayer cp1;
    private Game game;

    /**
     * Construct a ComputerPlayer with a valid Hand of Cards.
     */
    @BeforeEach
    public void setUp() {
        Deck deck = new StandardDeck();
        Hand h1 = new Hand(new ArrayList<>());
        Hand h2 = new Hand(new ArrayList<>());
        Hand[] hands = {h1, h2};
        for (Hand h : hands) {
            for (int i = 0; i < 4; i++) {
                h.addCard(deck.removeCardFromDeck());
            }
        }
        cp1 = new ComputerPlayer("CPU1", h1);
        ArrayList<Player> players = new ArrayList<>();
        players.add(cp1);
        game = new Game(deck, players);
        Card firstCard = new Card(Suit.SPADE, Rank.SEVEN);
        game.putCardDown(firstCard);
    }

    /**
     * Free memory by setting constructed objects in setUp() to null.
     */
    @AfterEach
    public void tearDown() {
        cp1 = null;
    }


    /**
     * Test selectRandomCard() in ComputerPlayer selects a valid Card given a Game instance or than a NoValidCardException
     * is thrown if it does not.
     */
    @Test
    public void testSelectCardValidCard() {
        Card toAddToCp1 = new Card(Suit.SPADE, Rank.NINE);
        cp1.getHand().addCard(toAddToCp1);
        Card selected = cp1.selectRandomValidCard(game);
        boolean validity = game.isValidCard(selected);
        assertTrue(validity);

    }

    /**
     * Test selectRandomCard() throws a NoValidCardException when there is no valid Card for the given Game.
     */
    @Test()
    public void testSelectCardInvalid() {
        Card bogus = new Card("Test", "18");
        ArrayList<Card> newHandList = new ArrayList<>();
        newHandList.add(bogus);
        Hand hand = new Hand(newHandList);
        cp1.setHand(hand);
        Card selected = cp1.selectRandomValidCard(game);
        assertNull(selected);
    }
}