package database;

import entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SaveLoadPlayerTest {

    private HumanPlayer p1;
    private HumanPlayer p2;
    private HumanPlayer p3;
    
    CSVDatabase database;

    /**
     * Construct 3 HumanPlayer objects to be used in the following tests.
     */
    @BeforeEach
    public void setUp() {
        Deck deck = new StandardDeck();
        Hand hand = new Hand(new ArrayList<>());
        for(int i = 0; i < 4 ; i++) {
            hand.addCard(deck.removeCardFromDeck());
        }
        p1 = new HumanPlayer("Lily1", hand, 0 , 0);
        p2 = new HumanPlayer("Lily2", hand, 1, 2);
        p3 = new HumanPlayer("Lily1", hand, 3, 0);

        database = new CSVDatabase();
    }

    /**
     * Clear the file after each test.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @AfterEach
    public void tearDown() throws IOException {
        FileWriter file = new FileWriter("src/main/java/database/players.csv");
        file.write("");
        file.close();
    }

    /**
     * Saves and loads one valid player (player exists in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveLoadOneValidPlayer() throws IOException {
        database.savePlayer(p1);
        PlayerInformation p4 = database.loadPlayer("Lily1");

        assert p4 != null;
        assertEquals(p1.getName(), p4.getName());
        assertEquals(p1.getWins(), p4.getWins());
        assertEquals(p1.getLosses(), p4.getLosses());
    }

    /**
     * Saves and loads multiple valid players (players exist in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveLoadMultipleValidPlayer() throws IOException {
        database.savePlayer(p1);
        database.savePlayer(p2);
        PlayerInformation p4 = database.loadPlayer("Lily1");
        PlayerInformation p5 = database.loadPlayer("Lily2");

        assert p4 != null;
        assertEquals(p1.getName(), p4.getName());
        assertEquals(p1.getWins(), p4.getWins());
        assertEquals(p1.getLosses(), p4.getLosses());

        assert p5 != null;
        assertEquals(p2.getName(), p5.getName());
        assertEquals(p2.getWins(), p5.getWins());
        assertEquals(p2.getLosses(), p5.getLosses());
    }

    /**
     * Loads one invalid player (player does not exist in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void loadOneInvalidPlayer() throws IOException {
        PlayerInformation p4 = database.loadPlayer("Lily3");

        assertNull(p4);
    }

    /**
     * Loads multiple invalid players (players do not exist in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void loadMultipleInvalidPlayer() throws IOException {
        PlayerInformation p4 = database.loadPlayer("Lily3");
        PlayerInformation p5 = database.loadPlayer("Lily4");

        assertNull(p4);
        assertNull(p5);
    }

    /**
     * Saves and loads one valid player, loads one invalid player.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveLoadValidInvalidPlayer() throws IOException {
        database.savePlayer(p1);
        PlayerInformation p4 = database.loadPlayer("Lily1");
        PlayerInformation p5 = database.loadPlayer("Lily3");

        assert p4 != null;
        assertEquals(p1.getName(), p4.getName());
        assertEquals(p1.getWins(), p4.getWins());
        assertEquals(p1.getLosses(), p4.getLosses());

        assertNull(p5);
    }

    /**
     * Saves an existing player (should not save).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveExistingPlayer() throws IOException {
        boolean saveP1 = database.savePlayer(p1);
        boolean saveP3 = database.savePlayer(p3);

        assertTrue(saveP1);
        assertFalse(saveP3);
    }
}
