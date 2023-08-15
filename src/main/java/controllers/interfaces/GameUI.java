package controllers.interfaces;

import controllers.data_objects.GameDisplayData;

/**
 * Represents an active game renderer.
 */
public interface GameUI {
    /** 
     * Update the view with the given data.
     * @param data The data to be displayed.
     */
    void updateView(GameDisplayData data);
}