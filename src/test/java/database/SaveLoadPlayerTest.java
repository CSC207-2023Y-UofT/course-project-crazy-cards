package database;

import entities.deck_logic.Deck;
import entities.deck_logic.StandardDeck;
import entities.game_logic.GameManager;
import entities.game_logic.IObserverNotifier;
import entities.game_logic.ObserverNotifier;
import entities.player_logic.Hand;
import entities.player_logic.HumanPlayer;
import entities.player_logic.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.PlayerInformation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SaveLoadPlayerTest {

    private HumanPlayer p1;
    private HumanPlayer p2;
    private HumanPlayer p3;
    private HumanPlayer p4;
    private GameManager manager;
    private Deck deck;
    
    private CSVDatabase database;
    private IObserverNotifier observerNotifier;

    /**
     * Construct 3 HumanPlayer objects to be used in the following tests.
     */
    @BeforeEach
    public void setUp() {
        deck = new StandardDeck();
        Hand h1 = new Hand(new ArrayList<>());
        Hand h2 = new Hand(new ArrayList<>());
        Hand h3 = new Hand(new ArrayList<>());
        Hand[] hands = {h1, h2, h3};
        for (Hand h: hands) {
            for(int i = 0; i < 5 ; i++) {
                h.addCard(deck.removeCardFromDeck());
            }
        }
        p1 = new HumanPlayer("Lily1", h1, 0 , 0);
        p2 = new HumanPlayer("Lily2", h2, 1, 2);
        p3 = new HumanPlayer("Lily1", h3, 3, 0);
        p4 = new HumanPlayer("Lily4", h3, 3, 0);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p4);
        manager = new GameManager();
        manager.buildGame(players, deck);
        database = new CSVDatabase();
        manager.addObserver(database);
        observerNotifier = new ObserverNotifier(manager);


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
        p1 = null;
        p2 = null;
        p3 = null;
        p4 = null;
        manager = null;
        deck = null;
        database = null;
        observerNotifier = null;
    }

    @Test
    public void testUpdateGameObserver() {
        manager.setWinner(p1);
        p1.incrementWins();
        p2.incrementLosses();
        p4.incrementLosses();
        observerNotifier.update();
        try {
            PlayerInformation loadedP1 = database.loadPlayer("Lily1");
            assertEquals(1, loadedP1.getWins());
            assertEquals(0, loadedP1.getLosses());
            PlayerInformation loadedP2 = database.loadPlayer("Lily2");
            assertEquals(1, loadedP2.getWins());
            assertEquals(3, loadedP2.getLosses());
            PlayerInformation loadedP3 = database.loadPlayer("Lily4");
            assertEquals(3, loadedP3.getWins());
            assertEquals(1, loadedP3.getLosses());
        } catch(IOException e) {
            fail();
        }

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
