package controllers.interfaces;

import java.util.List;

import controllers.data_objects.PlayerCreationInformation;

/** 
 * Define a controller for creating games.
 */
public interface CreatorBridge {
    /**
     * Request the creation of a game with the given players.
     * @param players the players to be in the game
     * @return whether the game was successfully created
     */
    boolean requestCreateGame(List<PlayerCreationInformation> players);
}
