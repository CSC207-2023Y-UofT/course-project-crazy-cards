package use_cases;

import entities.game_logic.GameObserver;
import entities.player_logic.HumanPlayer;

import java.io.IOException;

/**
 * An interface that interacts with the database to load and save players.
 */
public interface DataAccess extends GameObserver {
    /**
     * Loads a HumanPlayer's wins and losses from "src/main/java/use_cases/players.csv" using the given name.
     * @param name The name of the HumanPlayer.
     * @return A PlayerInformation object, or null if the name is not found.
     * @throws IOException If an input/output error occurs when reading from the file.
     */
    PlayerInformation loadPlayer(String name) throws IOException;

    /**
     * If the HumanPlayer does not exist in "src/main/java/use_cases/players.csv", save its name, wins, and losses.
     * If the HumanPlayer exists in "src/main/java/use_cases/players.csv", do not save anything.
     * @param player The HumanPlayer object.
     * @return true if information was saved, false if nothing was saved.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    boolean savePlayer(HumanPlayer player) throws IOException;
}
