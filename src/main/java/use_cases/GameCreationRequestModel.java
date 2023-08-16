package use_cases;

import java.util.HashMap;

/**
 * A request model containing User provided information on the Players to be made in a Game.
 */
public class GameCreationRequestModel {
    private final HashMap<String, Boolean> playersInfo;

    /**
     * Construct a GameCreationRequestModel
     * @param info A HashMap where keys are potential Player names and keys are booleans which are true iff the
     *             Player is to be a ComputerPlayer.
     */
    public GameCreationRequestModel(HashMap<String, Boolean> info) {
        this.playersInfo = info;
    }

    /**
     * Given a Player name, get whether this Player is to be a ComputerPlayer.
     * @param playerName The name of the Player to check
     * @return True iff the Player is to be a ComputerPlayer, null iff the name is not of a Player to be.
     */
    public Boolean isComputerPlayer(String playerName) {
        if (playersInfo.containsKey(playerName)) {
            return this.playersInfo.get(playerName);
        }
        return null;
    }

    /**
     * Get the data structure containing the requested Players' to be made information.
     * @return A HashMap with names (String) as key, and booleans as values.
     */
    public HashMap<String, Boolean> getPlayersInfo() {
        return playersInfo;
    }
}
