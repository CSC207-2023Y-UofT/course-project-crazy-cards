package use_cases;

/**
 * The input boundary for the Game Creation.
 */
public interface GameCreationInputBoundary {

    /**
     * Created a response model, and Game if the request is valid.
     * @param request a GameCreationRequestModel containing details from the User.
     * @return a GameCreationResponseModel containing whether a Game was created (if User input was valid or not).
     */
    GameCreationResponseModel createGameResponse(GameCreationRequestModel request);
}
