package database;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import entities.GameAccess;
import entities.HumanPlayer;
import entities.Player;
import use_cases.DataAccess;
import use_cases.PlayerInformation;
import java.io.*;
import java.util.List;


public class CSVDatabase implements DataAccess {
    String file = "src/main/java/database/players.csv";

    /**
     * Loads a HumanPlayer's wins and losses from "src/main/java/use_cases/players.csv" using the given name.
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
     * If the HumanPlayer does not exist in "src/main/java/use_cases/players.csv", save its name, wins, and losses.
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
            PlayerInformation newInfo = new PlayerInformation(loadedPlayer.getName(), player.getWins(), player.getLosses());
            updatePlayerStats(newInfo);
            bw.close();
            return false;
        }

        String[] data = {player.getName(), wins, losses};
        String line = String.join(",", data);
        bw.write(line);
        bw.write("\n");
        bw.close();
        return true;
    }

    /**
     * Update the stats of a particular Player given their stats information.
     * @param playerInfo The object containing the stats and name of the Player to update.
     */
    private void updatePlayerStats(PlayerInformation playerInfo) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> allLines = reader.readAll();
            for(String[] line: allLines) {
                if(line[0].equals(playerInfo.getName())) {
                    line[1] = ((Integer) playerInfo.getWins()).toString();
                    line[2] = ((Integer) playerInfo.getLosses()).toString();
                }
            }
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(file),
                    CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(allLines);
            writer.flush();
            writer.close();
        } catch (IOException | CsvException ignored) {
        }

    }

    /**
     * Update this GameObserver observing the given ObservableGame.
     * This database will continually receive updates when a Game is updated, and if there is a winner
     * then update the database to now have all the HumanPlayer's in the Game wins and losses.
     * @param game An ObservableGame that has notified this GameObserver of an update.
     */
    @Override
    public void updateGameObserver(GameAccess game) {
        boolean hasWinner = game.hasWinner();
        if(hasWinner) {
            for(Player p: game.getPlayers()) {
                if(p instanceof HumanPlayer) {
                    try {
                        savePlayer((HumanPlayer) p);
                    } catch (IOException ignored) {

                    }
                }
            }
        }
    }
}
