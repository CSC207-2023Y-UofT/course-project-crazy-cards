package use_cases;

import entities.HumanPlayer;

import java.io.*;

public class CSVDatabase implements DataAccess {

    String file = "src/main/java/database/players.csv";

    @Override
    public String[] loadPlayer(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values[0].equals(name)) {
                return values;
            }
        }
        return null;
    }

    @Override
    public boolean savePlayer(HumanPlayer player) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        String wins = Integer.toString(player.getWins());
        String losses = Integer.toString(player.getLosses());

        String[] loadedPlayer = loadPlayer(player.getName());
        if (loadedPlayer != null && loadedPlayer[0].equals(player.getName())) {
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
