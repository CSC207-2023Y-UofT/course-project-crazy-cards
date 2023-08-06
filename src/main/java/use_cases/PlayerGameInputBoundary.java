package use_cases;

/**
 * This interface serves as the InputBoundary between the Controller and the use case classes (interactor).
 */
public interface PlayerGameInputBoundary {

    /**
     * Create a new PlayerGameResponseModel given a PlayerGameRequestModel.
     * @param pgrm A PlayerGameRequestModel containing information on the User's request.
     * @return A PlayerGameResponseModel that contains the information necessary to update the view a User sees.
     */
    PlayerGameResponseModel createResponse(PlayerGameRequestModel pgrm);
}
