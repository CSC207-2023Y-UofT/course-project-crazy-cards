package controllers;

import use_cases.PlayerGameInputBoundary;
import use_cases.PlayerGameRequestModel;
import use_cases.PlayerGameResponseModel;

import enums.Rank;
import enums.Suit;
import enums.TurnAction;

/**
 * The controller class for when a User is playing a Game.
 */
public class PlayerGameController {
    private final PlayerGameInputBoundary inputBoundary;

    /**
     * Construct a Controller for the interaction between a User/Player and a Game.
     * Only one should be created for any particular Game instance.
     * @param inputBoundary The inputBoundary which takes in a request and returns a response.
     */
    public PlayerGameController(PlayerGameInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Given the details of a User's request, get the response of the action taken.
     * @param playerName The name of the Player whose turn it is.
     * @param cardValue The value of the chosen Card, null if none chosen.
     * @param cardSuit The suit of the chosen Card, null if none chosen.
     * @param playCard True iff requested to play a Card.
     * @param pickCard True iff requested to pick up a Card from the Deck.
     * @param skipTurn True iff requested to skip the current turn.
     * @return A PlayerGameResponseModel containing the Game details to be eventually shown to the User.
     */
    public PlayerGameResponseModel getResponse(String playerName, Suit suit, Rank rank,
                                               TurnAction action) {
        PlayerGameRequestModel requestModel = new PlayerGameRequestModel(playerName, suit, rank, action);
        return inputBoundary.createResponse(requestModel);
    }
}
