package use_cases;

import entities.HumanPlayer;

import java.io.IOException;

public interface DataAccess {
    String[] loadPlayer(String name) throws IOException;

    boolean savePlayer(HumanPlayer player) throws IOException;
}
