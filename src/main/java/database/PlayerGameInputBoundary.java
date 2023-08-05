package use_cases;

/**
 * This interface serves as the InputBoundary between the Controller and the Use Case classes (interactor)
 */
public interface PlayerGameInputBoundary {

    /**
     * Create a new PlayerGameResponseModel given a PlayerGameRequestModel.
     * @param pgrm A PlayerGameRequestModel containing information on the User's request.
     * @return a PlayerGameResponseModel that contains the information necessary to update the view a User sees.
     */
    PlayerGameResponseModel createResponse(PlayerGameRequestModel pgrm);
}
