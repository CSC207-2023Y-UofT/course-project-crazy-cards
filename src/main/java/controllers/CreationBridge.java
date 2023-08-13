package controllers;

import java.util.ArrayList;

/**
 * Defines a controller that handles the creation of a game.
 */
public interface CreationBridge {
    boolean createGameResponse(ArrayList<PlayerCreationInformation> playersInfo);
}
