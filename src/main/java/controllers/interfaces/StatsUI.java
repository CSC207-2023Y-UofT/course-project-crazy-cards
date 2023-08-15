package controllers.interfaces;

import controllers.data_objects.StatsDisplayData;

/**
 * Represents a user statistics view.
 */
public interface StatsUI {
    /**
     * Updates the view with the given data.
     * @param data The data to update the view with.
     */
    void updateView(StatsDisplayData data);
}
