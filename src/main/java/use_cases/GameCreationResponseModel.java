package use_cases;

/**
 * A simple response model. It contains a boolean on whether a Game was created or not when a
 * User attempts to create a Game.
 */
public class GameCreationResponseModel {
    private final boolean gameCreated;

    /**
     * Create a GameCreationResponseModel with the given parameter.
     * @param created True iff a Game was created in the input boundary/interactor.
     */
    public GameCreationResponseModel(boolean created) {
        this.gameCreated = created;
    }

    /**
     * Get whether a Game was created.
     * @return True iff a Game was created.
     */
    public boolean getGameCreated() {
        return this.gameCreated;
    }
}
