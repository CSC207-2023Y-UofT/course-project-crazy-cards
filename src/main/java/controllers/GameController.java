package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import controllers.data_objects.CardDisplayData;
import controllers.data_objects.GameDisplayData;
import controllers.interfaces.GameBridge;
import controllers.interfaces.GameUI;
import enums.Rank;
import enums.Suit;
import enums.TurnAction;
import use_cases.CardResponseModel;
import use_cases.PlayerGameInputBoundary;
import use_cases.PlayerGameRequestModel;
import use_cases.PlayerGameResponseModel;

/**
 * The controller class for when a User is playing a Game.
 */
public class GameController implements GameBridge {
    private final PlayerGameInputBoundary inputBoundary;

    private GameUI UI;
    private Suit selectedSuit;
    private Rank selectedRank;
    private String currentPlayer;
    private GameDisplayData data;

    /**
     * Construct a Controller for the interaction between a User/Player and a Game.
     * Only one should be created for any particular Game instance.
     * @param inputBoundary The inputBoundary which takes in a request and returns a response.
     */
    public GameController(PlayerGameInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Requests the state of the initial game.
     */
    @Override
    public void requestStartData() {
        PlayerGameResponseModel response = getResponse(null, null, null, TurnAction.START);
        data = getGameDisplayData(response);
        updateCurrentPlayer(response);
        UI.updateView(data);
    }

    /**
     * Requests to play the selected card.
     */
    @Override
    public void requestPlay() {
        PlayerGameResponseModel response = getResponse(currentPlayer, selectedSuit, selectedRank, TurnAction.PLAY);
        data = getGameDisplayData(response);
        updateCurrentPlayer(response);
        UI.updateView(data);
    }

    /**
     * Requests to draw a card.
     */
    @Override
    public void requestDraw() {
        PlayerGameResponseModel response = getResponse(currentPlayer, null, null, TurnAction.DRAW);
        data = getGameDisplayData(response);
        updateCurrentPlayer(response);
        UI.updateView(data);
    }

    /**
     * Requests to skip a turn.
     */
    @Override
    public void requestSkip() {
        PlayerGameResponseModel response = getResponse(currentPlayer, null, null, TurnAction.SKIP);
        data = getGameDisplayData(response);
        updateCurrentPlayer(response);
        UI.updateView(data);
    }

    /**
     * Sets the selected card.
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    @Override
    public void setSelectedCard(Suit suit, Rank rank) {
        selectedSuit = suit;
        selectedRank = rank;
        data.resetFeedback();
        UI.updateView(data);
    }

    /**
     * Sets the UI.
     * @param UI the UI to be set
     */
    @Override
    public void setUI(GameUI UI) {
        this.UI = UI;
    }

    /**
     * Given the details of a User's request, get the response of the action taken.
     * @param playerName The name of the Player whose turn it is.
     * @param suit The suit of the chosen Card, null if none chosen.
     * @param rank The value of the card (i.e. 2 through 10, or J, Q, K or A).
     * @param action The action made by the user.
     * @return A PlayerGameResponseModel containing the Game details to be eventually shown to the User.
     */
    private PlayerGameResponseModel getResponse(String playerName, Suit suit, Rank rank,
                                               TurnAction action) {
        PlayerGameRequestModel requestModel = new PlayerGameRequestModel(playerName, suit, rank, action);
        return inputBoundary.createResponse(requestModel);
    }

    /**
     * Helper method to convert a PlayerGameResponseModel to a GameDisplayData.
     */
    private GameDisplayData getGameDisplayData(PlayerGameResponseModel response) {
        String currentPlayer = response.getCurrentPlayerName();
        ArrayList<CardResponseModel> cardResponses = response.getPlayerCards();
        boolean hasWinner = response.getHasWinner();
        ArrayList<CardDisplayData> cards = new ArrayList<>(cardResponses.size());
        for (CardResponseModel cardResponse : cardResponses) {
            cards.add(new CardDisplayData(cardResponse.getSuit(), cardResponse.getRank()));
        }
        CardResponseModel responseCard = response.getCurrentCard();
        CardDisplayData topCard = new CardDisplayData(responseCard.getSuit(), responseCard.getRank());

        HashMap<String, Integer> playersAndNumCards = response.getPlayersAndNumCards();
        GameDisplayData data = new GameDisplayData();
        data.setCurrentPlayer(currentPlayer);
        data.setCurrentCard(topCard);
        data.setCards(cards);
        data.setHasWinner(hasWinner);
        data.setPlayersAndNumCards(playersAndNumCards);
        data.setLastAction(response.getLastMove(), response.getSuccess());
        return data;
    }

    /**
     * Helper method to update the current player of the game.
     */
    private void updateCurrentPlayer(PlayerGameResponseModel response) {
        currentPlayer = response.getCurrentPlayerName();
    }
}
