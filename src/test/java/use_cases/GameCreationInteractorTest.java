package use_cases;

import database.CSVDatabase;
import entities.Game;
import entities.HumanPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test public methods in GameCreationInteractor explicitly and private methods implicitly. This class would also test
 * methods in GameCreationResponseModel. It also tests the functionality of GameCreationController as this is the main
 * action done in it, so testing both would be redundant.
 */
class GameCreationInteractorTest {
    GameCreationRequestModel validRequest;
    GameCreationRequestModel invalidRequest;
    GameCreationInteractor interactor;

    /**
     * Set up all the objects needed to test GameCreationInteractor.
     */
    @BeforeEach
    public void setUp() {
        DataAccess dataAccess = new CSVDatabase();
        interactor = new GameCreationInteractor(dataAccess);
        validRequest = new GameCreationRequestModel(new HashMap<>());
        invalidRequest = new GameCreationRequestModel(new HashMap<>());
        validRequest.getPlayersInfo().put("p1", false);
        validRequest.getPlayersInfo().put("p2", false);
        validRequest.getPlayersInfo().put("cp3", true);
        invalidRequest.getPlayersInfo().put("cp1", true);
        invalidRequest.getPlayersInfo().put("cp2", true);
    }

    /**
     * Set all objects from setUp() to null.
     */
    @AfterEach
    public void tearDown() {
        interactor = null;
        validRequest = null;
        invalidRequest = null;
    }

    /**
     * Test no Game is created when given an invalid input and the response model has the correct response.
     */
    @Test
    public void testCreateGameResponseInvalid() {
        GameCreationResponseModel response = interactor.createGameResponse(invalidRequest);
        assertNull(interactor.getCreatedGame());
        assertNull(interactor.getNewGameState());
        assertFalse(response.getCreated());
    }

    /**
     * Test a Game and GameState are created and the response model has the correct response when valid request given.
     * This method also tests getNewGameState() and getCreatedGame().
     */
    @Test
    public void testCreateGameResponseValid() {
        GameCreationResponseModel response = interactor.createGameResponse(validRequest);
        assertTrue(response.getCreated());
        Game game = interactor.getCreatedGame();
        GameState gameState = interactor.getNewGameState();
        assertNotNull(game);
        assertNotNull(gameState);
        assertTrue(gameState.getCurrentPlayer() instanceof HumanPlayer);
        assertEquals(5, gameState.getCurrentPlayer().getNumCards());
        assertNotNull(gameState.getCurrentCard());
        // Assert the created Game's deck has had Cards dealt from it.
        assertEquals(36, game.getGameDeck().getCards().size());
    }
}