package database;

import entities.HumanPlayer;
import usecases.PlayerInformation;

import java.io.IOException;

public interface DataAccess {
    /**
     * Loads a HumanPlayer's wins and losses from "src/main/java/use_cases/players.csv" given their name.
     * @param name The name of the HumanPlayer.
     * @return A PlayerInformation object, or null if the name is not found.
     * @throws IOException If an input/output error occurs when reading from the file.
     */
    PlayerInformation loadPlayer(String name) throws IOException;

    /**
     * If the HumanPlayer does not exist in "src/main/java/use_cases/players.csv", saves its name, wins, and losses.
     * If the HumanPlayer exists in "src/main/java/use_cases/players.csv", do not save anything.
     * @param player The HumanPlayer object.
     * @return true if information was saved, false if nothing was saved.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    boolean savePlayer(HumanPlayer player) throws IOException;
}