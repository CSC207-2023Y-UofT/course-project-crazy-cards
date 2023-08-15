package controllers.interfaces;

import ui.interfaces.StatsUI;

/**
 * Defines a controller that handles stats requests and responses.
 */
public interface StatsBridge {
    /**
     * Request the stats for a given user.
     * @param username the username of the user
     */
    void tryRequestUser(String username);

    /**
     * Sets the UI.
     * @param ui the UI to be set
     */
    void setUI(StatsUI ui);
}