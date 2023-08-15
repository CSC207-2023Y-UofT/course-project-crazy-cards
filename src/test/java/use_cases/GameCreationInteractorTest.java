
package use_cases;

import database.CSVDatabase;
import entities.game_logic.CreationAccess;
import entities.game_logic.GameAccess;
import entities.game_logic.GameManager;
import entities.player_logic.HumanPlayer;
import entities.player_logic.Player;
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
    private GameCreationRequestModel validRequest;
    private GameCreationRequestModel invalidRequest;
    private GameCreationInteractor interactor;
    private GameAccess access;


    /**
     * Set up all the objects needed to test GameCreationInteractor.
     */
    @BeforeEach
    public void setUp() {
        access = new GameManager();
        DataAccess dataAccess = new CSVDatabase();
        interactor = new GameCreationInteractor(dataAccess, (CreationAccess) access);
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
        access = null;
    }

    /**
     * Test no Game is created when given an invalid input and the response model has the correct response.
     * That is, methods in access should return null.
     */
    @Test
    public void testCreateGameResponseInvalid() {
        GameCreationResponseModel response = interactor.createGameResponse(invalidRequest);
        assertNull(access.getCurrentCard());
        assertNull(access.getPlayers());
        assertFalse(response.getGameCreated());
    }

    /**
     * Test a Game and GameState are created and the response model has the correct response when valid request given.
     */
    @Test
    public void testCreateGameResponseValid() {
        GameCreationResponseModel response = interactor.createGameResponse(validRequest);
        assertTrue(response.getGameCreated());
        assertNotNull(access.getCurrentCard());
        assertTrue(access.getCurrentTurn() instanceof HumanPlayer);
        assertEquals(5, access.getCurrentTurn().getNumCards());
        assertNotNull(access.getCurrentCard());
        // Assert the created Game's deck has had Cards dealt from it.
        int sum = 0;
        for(Player p: access.getPlayers()) {
            sum += p.getNumCards();
        }
        assertEquals(15, sum);
    }
}
