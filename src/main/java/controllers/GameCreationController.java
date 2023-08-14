package controllers;

import use_cases.*;

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

    public GameCreationInteractor getInteractor() {
        return (GameCreationInteractor) this.inputBoundary;
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
        // immediately return false if there are duplicate names
        if(requestInfo.keySet().size() != playersInfo.size() | !(validOptions(requestInfo))) {
            return false;
        }
        GameCreationRequestModel request = new GameCreationRequestModel(requestInfo);
        GameCreationResponseModel response = inputBoundary.createGameResponse(request);
        return response.getGameCreated();
    }

    /**
     * Get whether the inputted info has enough HumanPlayers to be made.
     * @param info HashMap where keys are player names to be made and values are true iff it is to be a ComputerPlayer.
     * @return True iff at least 1 HumanPlayer.
     */
    private boolean validOptions(HashMap<String, Boolean> info) {
        int numHuman = 0;
        for(String name: info.keySet()) {
            if(!info.get(name)) {
                numHuman ++;
            }
        }
        return numHuman >= 1;
    }
}
