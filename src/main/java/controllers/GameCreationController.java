package controllers;

import use_cases.GameCreationInputBoundary;
import use_cases.GameCreationRequestModel;
import use_cases.GameCreationResponseModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The controller responsible for handling input and output for the Game creation page.
 */
public class GameCreationController {
    private final GameCreationInputBoundary inputBoundary;

    /**
     * Create a GameCreationController.
     * @param inputBoundary The inputBoundary responsible for handling the request and response models.
     */
    public GameCreationController(GameCreationInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Create a response model detailing whether a Game was created given a particular request from the User.
     * @param playersInfo The User requested Player names and if they are to be ComputerPlayers or not.
     * @return A GameCreationResponseModel detailing if a Game was created.
     */
    public boolean createGameResponse(ArrayList<PlayerCreationInformation> playersInfo) {
        HashMap<String, Boolean> requestInfo = new HashMap<>();
        for(PlayerCreationInformation info: playersInfo) {
            requestInfo.put(info.getPlayerName(), info.getIsComputerPlayer());
        }
        GameCreationRequestModel request = new GameCreationRequestModel(requestInfo);
        GameCreationResponseModel response = inputBoundary.createGameResponse(request);
        return response.getGameCreated();
    }
}
