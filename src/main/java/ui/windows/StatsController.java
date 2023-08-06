package ui.windows;

import usecases.PlayerInformation;

/**
 * Handles in- and outgoing data for a StatsDisplay.
 */
public class StatsController {
    private StatsDisplay display;

    /**
     * Construct a StatsController with no endpoint. 
     */
    public StatsController() { 
        this.display = null;
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
     * @param player the PlayerInformation of the user
     * @param username the name of the user
     * @return false if no display is supplied, true otherwise
     */
    public boolean tryRequestUser(PlayerInformation player, String username) {
        if (display == null) {
            return false;
        }

        StatsDisplayData data = retrieveData(player, username);
        display.updateView(data);
        return true;
    }

    /**
     * Retrieves statistics for a given user.
     * @param player the PlayerInformation of the user
     * @param username the name of the user
     * @return the retrieved data as a StatsDisplayData
     */
    private StatsDisplayData retrieveData(PlayerInformation player, String username) {
        StatsDisplayData data = new StatsDisplayData();

        if (player != null) {
            data.setName(player.getName());
            data.setGamesWon(player.getWins());
            data.setGamesLost(player.getLosses());
        } else {
            data.setName(username);
            data.setGamesWon(0);
            data.setGamesLost(0);
        }
        return data;
    }
}
