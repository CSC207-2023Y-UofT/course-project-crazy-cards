package controllers;

/**
 * A class containing the direct instructions of a User regarding a Player in a Game's creation.
 */
public class PlayerCreationInformation {
    private String playerName;
    private boolean isComputerPlayer;

    /**
     * Construct a PlayerCreationInformation containing information provided by the User.
     * @param name The name of the Player requested to be created by the User.
     * @param isComp True iff the User requests the Player to be a ComputerPlayer.
     */
    public PlayerCreationInformation(String name, boolean isComp) {
        this.playerName = name;
        this.isComputerPlayer = isComp;
    }

    /**
     * Get the requested Player's name.
     * @return A String containing a potential Player's name.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Get whether the requested Player is to be a ComputerPlayer.
     * @return True iff requested to be a ComputerPlayer.
     */
    public boolean getIsComputerPlayer() {
        return this.isComputerPlayer;
    }
}
