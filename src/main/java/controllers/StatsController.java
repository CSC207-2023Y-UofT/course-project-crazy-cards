package controllers;

import controllers.data_objects.StatsDisplayData;
import controllers.interfaces.StatsBridge;
import controllers.interfaces.StatsUI;
import use_cases.DataAccess;
import use_cases.PlayerInformation;

import java.io.IOException;

/**
 * Handles stats requests and responses.
 */
public class StatsController implements StatsBridge {
    private static final StatsDisplayData ERROR = new StatsDisplayData("ERROR", -1, -1);

    private StatsUI display;
    private final DataAccess database;

    /**
     * Construct a StatsController with no endpoint. 
     */
    public StatsController(DataAccess database) {
        this.display = null;
        this.database = database;
    }

    /**
     * Requests statistics for a given user and updates display.
     * @param username the name of the user
     */
    @Override
    public void tryRequestUser(String username) {
        PlayerInformation info;
        try {
            info = database.loadPlayer(username);
        } catch (IOException e) {
            e.printStackTrace();
            display.updateView(ERROR);
            return;
        }
        StatsDisplayData data = retrieveData(info, username);
        display.updateView(data);
    }

    /**
     * Sets a display endpoint for the controller.
     * @param display the display to be used
     */
    @Override
    public void setUI(StatsUI display) {
        this.display = display;
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
