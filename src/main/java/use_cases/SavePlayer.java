package use_cases;

import entities.HumanPlayer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SavePlayer {
    /**
     * If the HumanPlayer does not exist in "src/main/java/use_cases/players.csv", saves its name, wins, and losses.
     * If the HumanPlayer exists in "src/main/java/use_cases/players.csv", do not save anything.
     * @param player The HumanPlayer object.
     * @return true if information was saved, false if nothing was saved.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    public static boolean savePlayerToFile(HumanPlayer player) throws IOException {
        String csvFile = "src/main/java/use_cases/players.csv";

        BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true));
        String wins = Integer.toString(player.getWins());
        String losses = Integer.toString(player.getLosses());

        String[] loadedPlayer = LoadPlayer.loadPlayerFromFile(player.getName());
        if (loadedPlayer != null && loadedPlayer[0].equals(player.getName())) {
            return false;
        }

        String[] data = {player.getName(), wins, losses};
        String line = String.join(",", data);
        bw.write(line);
        bw.newLine();
        return true;
    }
}
