package use_cases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadPlayer {
    /**
     * Loads a HumanPlayer's wins and losses from "src/main/java/use_cases/players.csv" given their name.
     * @param name The name of the HumanPlayer.
     * @return A string array [name, wins, losses] of the HumanPlayer, or null if the name is not found.
     * @throws IOException If an input/output error occurs when reading from the file.
     */
    public static String[] loadPlayerFromFile(String name) throws IOException {
        String file = "src/main/java/use_cases/players.csv";
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
}
