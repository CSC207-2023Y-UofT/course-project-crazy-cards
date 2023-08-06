package use_cases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GameCreationRequestModel
 */
class GameCreationRequestModelTest {

    private GameCreationRequestModel request;

    /**
     * Initialize objects needed to test GameCreationRequestModel.
     */
    @BeforeEach
    public void setUp() {
        request = new GameCreationRequestModel(new HashMap<>());
        request.getPlayersInfo().put("cp1", true);
        request.getPlayersInfo().put("Sab", false);
    }

    /**
     * Set objects from setUp() to null to free memory./
     */
    @AfterEach
    public void tearDown() {
        request = null;
    }

    /**
     * Test to check whether a given Player name in the request is to be a ComputerPlayer using isComputerPlayer().
     */
    @Test
    public void testIsComputerPlayer() {
        assertTrue(request.isComputerPlayer("cp1"));
        assertFalse(request.isComputerPlayer("Sab"));
    }

    /**
     * Test to check getPlayersinfo() returns with the names and options requested.
     */
    @Test
    public void TestgetPlayersInfo() {
        HashMap<String, Boolean> map = request.getPlayersInfo();
        assertEquals(2, map.keySet().size());
        assertTrue(map.get("cp1"));
        assertFalse(map.get("Sab"));
    }
}