package use_cases;

import entities.*;
import enums.Rank;
import enums.Suit;
import enums.TurnAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PlayerGameInteractor.
 * This test class would also serve to test PlayerGameController as its only behaviour is creating a request model
 * and using the interactor to create a response model.
 */
class PlayerGameInteractorTest {

    private Player p1;
    private Player p2;
    private Player p3;
    private ArrayList<Player> players;
    private GameState gameState;
    private PlayerGameInteractor interactor;
    private PlayerGameRequestModel playCardReq;
    private PlayerGameRequestModel skipReq;
    private PlayerGameRequestModel pickUpReq;
    private Card p1sCard;
    private Card firstCard;
    private GameManager manager;
    private Deck deck;
    private Card valid;

    /**
     * Initialize all objects required to test PlayerGameInteractor.
     */
    @BeforeEach
    public void setUp() {
        deck = new StandardDeck();
        Hand h1 = new Hand(new ArrayList<>());
        Hand h2 = new Hand(new ArrayList<>());
        Hand h3 = new Hand(new ArrayList<>());
        Hand h4 = new Hand(new ArrayList<>());
        Hand[] hands = {h1, h2, h3, h4};
        for (Hand h: hands) {
            for(int i = 0; i < 5 ; i++) {
                h.addCard(deck.removeCardFromDeck());
            }
        }
        p1 = new HumanPlayer("sol", h1, 0 , 0);
        p2 = new ComputerPlayer("cp1", h2);
        p3 = new HumanPlayer("sab" , h2, 0 , 0);
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        manager = new GameManager();
        manager.buildGame(players, deck);
        // Make the first Card of the game something p1 can play on top of.
        if (!hasAnyValid(p1)) {
            valid = giveValidCard(p1);
        }
        gameState = new GameState(manager);
        manager.addObserver(gameState);
        valid = findValidCard(p1);
        IObserverNotifier observerNotifier = new ObserverNotifier(manager);
        interactor = new PlayerGameInteractor(manager, observerNotifier, gameState);
        playCardReq = new PlayerGameRequestModel("sol", valid.getSuit(), valid.getRank(), TurnAction.PLAY);
        pickUpReq = new PlayerGameRequestModel("sol", null, null, TurnAction.DRAW);
        skipReq = new PlayerGameRequestModel("sol", null, null, TurnAction.SKIP);

    }
    private Card findValidCard(Player player) {
        for(Card card: player.getCards()) {
            if(manager.isValidCard(card)) {
                return card;
            }
        }
        return null;
    }
    private Card giveValidCard(Player player) {
        boolean hasValid = false;
        while(!hasValid) {
            Card toAdd = deck.removeCardFromDeck();
            if(manager.isValidCard(toAdd)) {
                player.getHand().addCard(toAdd);
                return toAdd;
            }
        }
        return null;
    }
    private boolean hasAnyValid(Player player) {
        for(Card card: player.getCards()) {
            if (manager.isValidCard(card)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set all made objects in setUp() to null to free memory.
     */
    @AfterEach
    public void tearDown() {
        gameState = null;
        p1 = null;
        p2 = null;
        deck = null;
        players = null;
        interactor = null;
        playCardReq = null;
        skipReq = null;
        pickUpReq = null;
        p1sCard = null;
        firstCard = null;
        manager = null;
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
     * Test createResponse() when given the input to play a Card and the Card is valid.
     */
    @Test
    public void testCreateResponsePlayValidCard() {
        PlayerGameResponseModel response = interactor.createResponse(playCardReq);
        // Assert turn has been changed to p3 (p2 is Computer, so logic should be done)
        assertEquals(p3.getName(), response.getCurrentPlayerName());
        // Assert that p1 has lost a Card and that Card is in the deck.
        assertEquals(4, p1.getNumCards());
        assertTrue(deck.getCards().contains(p1sCard));
        // Assert the Game has no winner yet.
        assertFalse(response.getHasWinner());
    }

    /**
     * Test createResponse() when given the input to play a Card and the Card is invalid.
     */
    @Test
    public void testCreateResponsePlayInvalidCard() {
        // Give a new bogus Hand to p1 so to validate p1 has no valid Cards.
        giveInvalidHand(p1);
        Card bogus = p1.getCards().get(0);
        Suit bogusSuit = bogus.getSuit();
        Rank bogusRank = bogus.getRank();
        // Have p1 try to play bogus card.
        PlayerGameRequestModel pgrm = new PlayerGameRequestModel("sol", bogusSuit, bogusRank, TurnAction.PLAY);
        interactor.createResponse(pgrm);
        // Assert the turn has not changed, no winner, current Card is the same, none of the player's cards have changed.
        assertEquals(p1, gameState.getCurrentPlayer());
        assertFalse(gameState.getHasWinner());
        assertEquals(firstCard, gameState.getCurrentCard());
        for (Player p : manager.getPlayers()) {
            if (p.equals(p1)) {
                assertEquals(2, p.getNumCards());
            } else {
                assertEquals(5, p.getNumCards());
            }
        }
    }

    /**
     * Test createResponse when given a Player plays a valid Card, and they become the winner.
     */
    @Test
    public void testCreateResponsePlayCardWinner () {
        // Give p1 a new Hand containing just one valid Card
        Hand newHand = new Hand(new ArrayList<>());
        newHand.addCard(p1sCard);
        p1.setHand(newHand);
        // Play the only Card in this Hand.
        PlayerGameResponseModel response = interactor.createResponse(playCardReq);
        // Assert the Game has a winner, p1 has no cards, p1 has 1 win 0 losses, while p3 has 1 loss and 0 win, current
        // Card was the played Card.
        assertTrue(response.getHasWinner());
        assertEquals(0, p1.getNumCards());
        HumanPlayer humanP1 = (HumanPlayer) p1;
        assertEquals(1, humanP1.getWins());
        assertEquals(0, humanP1.getLosses());
        HumanPlayer humanP3 = (HumanPlayer) p3;
        assertEquals(1, humanP3.getLosses());
        assertEquals(0, humanP3.getWins());
        assertEquals(p1sCard, gameState.getCurrentCard());
    }

    /**
     * Test createResponse when a User/Player requests to skip a turn which they can.
     */
    @Test
    public void testCreateResponseSkipTurnValid () {
        // Make it so that p1 has to skip (has picked up and no valid cards)
        //.setCurrentTurnHasPickedUpTrue();
        giveInvalidHand(p1);
        // p1 has no valid cards, and cannot pick up, now request to skip
        interactor.createResponse(skipReq);
        // Assert that p1 has had no change in Card number, and that it is now p3s turn
        assertEquals(2, p1.getNumCards());
        assertEquals(p3, gameState.getCurrentPlayer());
    }

    /**
     * Test createResponse when a User requests to skip a turn, but they cannot.
     */
    @Test
    public void testCreateResponseSkipTurnInvalid () {
        interactor.createResponse(skipReq);
        // Nothing should have changed. Therefore, assert game was how it was before the response.
        assertEquals(p1, gameState.getCurrentPlayer());
        assertFalse(gameState.getHasWinner());
        assertEquals(firstCard, gameState.getCurrentCard());
        for (Player p : manager.getPlayers()) {
            assertEquals(5, p.getNumCards());
        }
    }

    /**
     * Test createResponse when a User requests to pick up a Card and they can do so.
     */
    @Test
    public void testCreateResponsePickUpCardValid () {
        // Give a new bogus Hand to p1 so to validate p1 has no valid Cards.
        giveInvalidHand(p1);
        // Have p1 pick up a Card. Only thing that should've changed is p1's number of Cards.
        interactor.createResponse(pickUpReq);
        assertEquals(p1, gameState.getCurrentPlayer());
        assertFalse(gameState.getHasWinner());
        assertEquals(firstCard, gameState.getCurrentCard());
        for (Player p : manager.getPlayers()) {
            if(!(p.equals(p1))) {
                assertEquals(5, p.getNumCards());
            } else {
                assertEquals(3, p1.getNumCards());
            }
        }
    }

    /**
     * Test createResponse when a User requests to pick up a Card, but they cannot.
     */
    @Test
    public void testCreateResponsePickUpCardInValid () {
        PlayerGameResponseModel response = interactor.createResponse(pickUpReq);
        // Nothing should have changed. Therefore, assert game was how it was before the response.
        assertEquals(p1.getName(), response.getCurrentPlayerName());
        assertFalse(gameState.getHasWinner());
        assertEquals(firstCard, gameState.getCurrentCard());
        for (Player p : manager.getPlayers()) {
            assertEquals(5, p.getNumCards());
        }
    }
}