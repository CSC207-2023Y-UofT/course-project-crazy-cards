package use_cases;

import entities.Deck;
import entities.Hand;
import entities.HumanPlayer;
import entities.StandardDeck;
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
    }

    /**
     * Clear the file after each test.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @AfterEach
    public void tearDown() throws IOException {
        FileWriter file = new FileWriter("src/main/java/use_cases/players.csv");
        file.write("");
        file.close();
    }

    /**
     * Saves and loads one valid player (player exists in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveLoadOneValidPlayer() throws IOException {
        SavePlayer.savePlayerToFile(p1);
        String[] p4 = LoadPlayer.loadPlayerFromFile("Lily1");

        assert p4 != null;
        assertEquals(p1.getName(), p4[0]);
        assertEquals(Integer.toString(p1.getWins()), p4[1]);
        assertEquals(Integer.toString(p1.getLosses()), p4[2]);
    }

    /**
     * Saves and loads multiple valid players (players exist in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveLoadMultipleValidPlayer() throws IOException {
        SavePlayer.savePlayerToFile(p1);
        SavePlayer.savePlayerToFile(p2);
        String[] p4 = LoadPlayer.loadPlayerFromFile("Lily1");
        String[] p5 = LoadPlayer.loadPlayerFromFile("Lily2");

        assert p4 != null;
        assertEquals(p1.getName(), p4[0]);
        assertEquals(Integer.toString(p1.getWins()), p4[1]);
        assertEquals(Integer.toString(p1.getLosses()), p4[2]);

        assert p5 != null;
        assertEquals(p2.getName(), p5[0]);
        assertEquals(Integer.toString(p2.getWins()), p5[1]);
        assertEquals(Integer.toString(p2.getLosses()), p5[2]);
    }

    /**
     * Loads one invalid player (player does not exist in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void loadOneInvalidPlayer() throws IOException {
        String[] p4 = LoadPlayer.loadPlayerFromFile("Lily3");

        assertNull(p4);
    }

    /**
     * Loads multiple invalid players (players do not exist in the file).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void loadMultipleInvalidPlayer() throws IOException {
        String[] p4 = LoadPlayer.loadPlayerFromFile("Lily3");
        String[] p5 = LoadPlayer.loadPlayerFromFile("Lily4");

        assertNull(p4);
        assertNull(p5);
    }

    /**
     * Saves and loads one valid player, loads one invalid player.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveLoadValidInvalidPlayer() throws IOException {
        SavePlayer.savePlayerToFile(p1);
        String[] p4 = LoadPlayer.loadPlayerFromFile("Lily1");
        String[] p5 = LoadPlayer.loadPlayerFromFile("Lily3");

        assert p4 != null;
        assertEquals(p1.getName(), p4[0]);
        assertEquals(Integer.toString(p1.getWins()), p4[1]);
        assertEquals(Integer.toString(p1.getLosses()), p4[2]);

        assertNull(p5);
    }

    /**
     * Saves an existing player (should not save).
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Test
    public void saveExistingPlayer() throws IOException {
        boolean saveP1 = SavePlayer.savePlayerToFile(p1);
        boolean saveP3 = SavePlayer.savePlayerToFile(p3);

        assertTrue(saveP1);
        assertFalse(saveP3);
    }
}
