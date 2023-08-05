package ui.windows;

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
     * @param display The display to be used.
     */
    public void setDisplay(StatsDisplay display) {
        this.display = display;
    }

    /**
     * Requests statistics for a given user and updates display.
     * @param username The name of the user.
     * @return false if no display is supplied, true otherwise.
     */
    public boolean tryRequestUser(String username) {
        if (display == null) {
            return false;
        }

        StatsDisplayData data = retrieveData(username);
        display.updateView(data);
        return true;
    }

    /**
     * Retrieves statistics for a given user.
     * @param username The name of the user.
     * @return The retrieved data as a StatsDisplayData.
     */
    private StatsDisplayData retrieveData(String username) {
        // TODO: send usecase 

        StatsDisplayData data = new StatsDisplayData();
        data.setName(username);
        data.setGamesPlayed(0); // TODO: replace with retrieved data
        data.setGamesWon(0); // ditto
        data.setLongestWinStreak(0); // ditto ditto
        return data;
    }
}
