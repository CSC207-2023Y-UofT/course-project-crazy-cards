package controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PlayerCreationInformation.
 */
class PlayerCreationInformationTest {
    private PlayerCreationInformation pciComp;
    private PlayerCreationInformation pciHuman;

    /**
     * Create objects needed to test PlayerCreationInformation.
     */
    @BeforeEach
    public void setUp() {
        pciComp = new PlayerCreationInformation("cp1", true);
        pciHuman = new PlayerCreationInformation("Sab", false);
    }

    /**
     * Free memory by setting objects from setUp() to null.
     */
    @AfterEach
    public void tearDown() {
        pciComp = null;
        pciHuman = null;
    }

    /**
     * Test getPlayerName() given both computer and non computer requests.
     */
    @Test
    public void testGetPlayerName() {
        assertEquals("cp1", pciComp.getPlayerName());
        assertEquals("Sab", pciHuman.getPlayerName());
    }

    /**
     * Test getIsComputerPlayer() given both computer and non computer requests.
     */
    @Test
    public void testGetIsComputerPlayer() {
        assertTrue(pciComp.getIsComputerPlayer());
        assertFalse(pciHuman.getIsComputerPlayer());
    }
}