package entities;

import entities.card_logic.Card;
import entities.deck_logic.Deck;
import entities.deck_logic.StandardDeck;
import entities.game_logic.GameManager;
import entities.player_logic.ComputerPlayer;
import entities.player_logic.Hand;
import entities.player_logic.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Rank;
import enums.Suit;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    private ComputerPlayer cp1;
    private GameManager manager;
    private Deck deck;

    /**
     * Construct a ComputerPlayer with a valid Hand of Cards.
     */
    @BeforeEach
    public void setUp() {
        deck = new StandardDeck();
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
        manager = new GameManager();
        manager.buildGame(players, deck);
        Card firstCard = new Card(Suit.SPADE, Rank.SEVEN);
        manager.playCard(cp1, firstCard);
    }

    /**
     * Free memory by setting constructed objects in setUp() to null.
     */
    @AfterEach
    public void tearDown() {
        cp1 = null;
    }



    /**
     * Give the given player an invalid Hand
     */
    private void giveInvalidHand(Player player) {
        Hand newHand = new Hand(new ArrayList<>());
        while (newHand.getCards().size() < 2) {
            Card newCard = deck.removeCardFromDeck();
            if(!(manager.isValidCard(newCard))) {
                newHand.addCard(newCard);
            }
        }
        player.setHand(newHand);
    }

    /**
     * Test selectRandomCard() in ComputerPlayer selects a valid Card given a Game instance or than a NoValidCardException
     * is thrown if it does not.
     */
    @Test
    public void testSelectCardValidCard() {
        Card toAddToCp1 = new Card(Suit.SPADE, Rank.NINE);
        cp1.getHand().addCard(toAddToCp1);
        Card selected = manager.selectRandomValidCard(cp1);
        boolean validity = manager.isValidCard(selected);
        assertTrue(validity);

    }

    /**
     * Test selectRandomCard() throws a NoValidCardException when there is no valid Card for the given Game.
     */
    @Test()
    public void testSelectCardInvalid() {
        giveInvalidHand(cp1);
        Card selected = manager.selectRandomValidCard(cp1);
        assertNull(selected);
    }
}