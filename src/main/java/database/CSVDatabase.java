package database;

import entities.HumanPlayer;
import use_cases.DataAccess;
import usecases.PlayerInformation;

import java.io.*;

public class CSVDatabase implements DataAccess {

    String file = "src/main/java/database/players.csv";

    /**
     * Loads a HumanPlayer's wins and losses from "src/main/java/use_cases/players.csv" given their name.
     * @param name The name of the HumanPlayer.
     * @return A PlayerInformation object, or null if the name is not found.
     * @throws IOException If an input/output error occurs when reading from the file.
     */
    @Override
    public PlayerInformation loadPlayer(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[0].equals(name)) {
                return new PlayerInformation(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
            }
        }
        return null;
    }

    /**
     * If the HumanPlayer does not exist in "src/main/java/use_cases/players.csv", saves its name, wins, and losses.
     * If the HumanPlayer exists in "src/main/java/use_cases/players.csv", do not save anything.
     * @param player The HumanPlayer object.
     * @return true if information was saved, false if nothing was saved.
     * @throws IOException If an input/output error occurs when writing to the file.
     */
    @Override
    public boolean savePlayer(HumanPlayer player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        String wins = Integer.toString(player.getWins());
        String losses = Integer.toString(player.getLosses());

        PlayerInformation loadedPlayer = loadPlayer(player.getName());
        if (loadedPlayer != null && loadedPlayer.getName().equals(player.getName())) {
            bw.close();
            return false;
        }

        String[] data = {player.getName(), wins, losses};
        String line = String.join(",", data);
        bw.write(line);
        bw.newLine();
        bw.close();
        return true;
    }
}
