package ui.windows.stats;

import use_cases.DataAccess;
import use_cases.PlayerInformation;

import java.io.IOException;

/**
 * Handles in- and outgoing data for a StatsDisplay.
 */
public class StatsController {
    private StatsDisplay display;
    private DataAccess database;

    /**
     * Construct a StatsController with no endpoint. 
     */
    public StatsController() {
        this.display = null;
        this.database = database;
    }

    public void setDatabase(DataAccess database) {
        this.database = database;
    }

    /**
     * Sets a display endpoint for the controller.
     * @param display the display to be used
     */
    public void setDisplay(StatsDisplay display) {
        this.display = display;
    }

    /**
     * Requests statistics for a given user and updates display.
     * @param username the name of the user
     * @return false if no display is supplied, true otherwise
     */
    public boolean tryRequestUser(String username) throws IOException {
        if (display == null) {
            return false;
        }
        PlayerInformation player = database.loadPlayer(username);
        StatsDisplayData data = retrieveData(player, username);
        display.updateView(data);
        return true;
    }

    /**
     * Retrieves statistics for a given user.
     * @param playerInfo the PlayerInformation of the user
     * @param username the name of the user
     * @return the retrieved data as a StatsDisplayData
     */
    private StatsDisplayData retrieveData(PlayerInformation playerInfo, String username) {
        StatsDisplayData data;

        if (playerInfo != null) {
            data = new StatsDisplayData(playerInfo.getName(), playerInfo.getWins(), playerInfo.getLosses());
        } else {
            data = new StatsDisplayData();
            data.setName(username);
        }
        return data;
    }
}
