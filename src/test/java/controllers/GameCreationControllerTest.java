package controllers;

import database.CSVDatabase;
import entities.game_logic.CreationAccess;
import entities.game_logic.GameManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.DataAccess;
import use_cases.GameCreationInputBoundary;
import use_cases.GameCreationInteractor;

import java.util.ArrayList;

/**
 * Test class for GameCreationController
 */
class GameCreationControllerTest {

    private ArrayList<PlayerCreationInformation> validInfo = new ArrayList<>();
    private ArrayList<PlayerCreationInformation> invalidInfo = new ArrayList<>();
    private GameCreationController controller;

    /**
     * Set up objects needed to test GameCreationController
     */
    @BeforeEach
    public void setUp() {
        PlayerCreationInformation pci1 = new PlayerCreationInformation("Sol", true);
        PlayerCreationInformation pci2 = new PlayerCreationInformation("Sol", false);
        PlayerCreationInformation pci3 = new PlayerCreationInformation("Sab", false);
        PlayerCreationInformation pci4 = new PlayerCreationInformation("Hee", true);
        validInfo.add(pci2);
        validInfo.add(pci4);
        validInfo.add(pci3);
        invalidInfo.add(pci1);
        invalidInfo.add(pci2);
        invalidInfo.add(pci3);
        DataAccess dataAccess = new CSVDatabase();
        CreationAccess creationAccess = new GameManager();
        GameCreationInputBoundary inputBoundary = new GameCreationInteractor(dataAccess, creationAccess);
        controller = new GameCreationController(inputBoundary);
    }

    /**
     * Set objects made in setUp tp null
     */
    @AfterEach
    public void tearDown() {
        validInfo = null;
        controller = null;
        invalidInfo = null;
    }

    /**
     * Test false is returned when the controller is given invalid player information.
     */
    @Test
    public void testCreateGameInvalid() {
        Assertions.assertFalse(controller.createGameResponse(invalidInfo));
    }

    /**
     * Test true is returned when the controller is given valid player information.
     */
    @Test
    public void testCreateGameValid() {
        Assertions.assertTrue(controller.createGameResponse((validInfo)));
    }
}