
package use_cases;

import entities.card_logic.Card;
import entities.deck_logic.Deck;
import entities.deck_logic.StandardDeck;
import entities.game_logic.Game;
import entities.game_logic.GameManager;
import entities.game_logic.IObserverNotifier;
import entities.game_logic.ObserverNotifier;
import entities.player_logic.ComputerPlayer;
import entities.player_logic.Hand;
import entities.player_logic.HumanPlayer;
import entities.player_logic.Player;
import enums.Rank;
import enums.Suit;
import enums.TurnAction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class testing getter methods for PlayerGameResponseModel given a particular request.
 */
class PlayerGameResponseModelTest {

    private Game game;
    private Player p1;
    private PlayerGameResponseModel response;

    @BeforeEach
    public void setUp() {
        Deck deck = new StandardDeck();
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
        Player p2 = new ComputerPlayer("cp1", h2);
        Player p3 = new HumanPlayer("sab" , h2, 0 , 0);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        game = new Game(deck, players);
        // Make the first Card of the game something p1 can play on top of.
        Card firstCard = new Card(h1.getCards().get(0).getSuit(), Rank.KING);
        game.putCardDown(firstCard);
        GameManager manager = new GameManager();
        manager.buildGame(players, deck);
        GameState gameState = new GameState(manager);
        manager.addObserver(gameState);
        IObserverNotifier notifier = new ObserverNotifier(manager);
        PlayerGameInteractor interactor = new PlayerGameInteractor(manager, notifier, gameState);
        PlayerGameRequestModel request = new PlayerGameRequestModel("sol", null, null, TurnAction.DRAW);
        response = interactor.createResponse(request);
    }

    /**
     * Set all created objects in setUp() to null to free memory.
     */
    @AfterEach
    public void tearDown() {
        game = null;
        p1 = null;
        response = null;
    }

    /**
     * Test that after a Game is updated and has no winner, this response model returns false when getHasWinner() is called.
     */
    @Test
    public void testGetHasWinner() {
        assertFalse(response.getHasWinner());
    }


    /**
     * Test that after a Game is updated, response contains the representations of the Players cards.
     */
    @Test
    public void testGetPlayerCards() {
        for(int i = 0; i < p1.getNumCards(); i++) {
            Card p1s = p1.getCards().get(i);
            Suit suit = response.getPlayerCards().get(i).getSuit();
            Rank value = response.getPlayerCards().get(i).getRank();
            assertEquals(p1s.getSuit(), suit);
            assertEquals(p1s.getRank(), value);
        }
    }

    /**
     * Test that getPlayersAndNumCards() returns a valid HashMap containing player names and their number of cards.
     */
    @Test
    public void testGetPlayersAndNumCards() {
        ArrayList<Player> playersNoP1 = new ArrayList<>(game.getPlayers());
        playersNoP1.remove(p1);
        for(Player p: playersNoP1) {
            assertEquals(p.getNumCards(), response.getPlayersAndNumCards().get(p.getName()));
        }
    }

    /**
     * Test getCurrentPlayerName() returns the String containing the current Player's name.
     */
    @Test
    public void testGetCurrentPlayerName() {
        assertEquals(p1.getName(), response.getCurrentPlayerName());
    }
}
