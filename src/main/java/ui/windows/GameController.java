package ui.windows;

import java.util.ArrayList;

import controllers.GameBridge;
import entities.Player;
import enums.Rank;
import enums.Suit;
import enums.TurnAction;
import use_cases.CardResponseModel;
import use_cases.PlayerGameResponseModel;

/**
 * Handles delegated user interaction for a game window.
 */
public class GameController {
    private GameBridge bridge;
    
    private GameDisplay display;

    private Suit selectedSuit;
    private Rank selectedRank;
    private String selectedOwner;

    /**
     * Construct a GameController with no endpoint. 
     */
    public GameController() { 
        this.display = null;
    }

    /**
     * Sets a display endpoint for the controller.
     * @param display the display to be used
     */
    public void setDisplay(GameDisplay display) {
        this.display = display;
    }

    /**
     * Sets the user-selected card before it is played.
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public void setSelectedCard(Suit suit, Rank rank) {
        selectedSuit = suit;
        selectedRank = rank;
    }

    /**
     * Fires when the user requests to play their selected card.
     * Passes request to game logic and sends response to display.
     */
    public void playCard() {
        PlayerGameResponseModel response = bridge.getResponse(selectedOwner, selectedSuit, selectedRank, TurnAction.PLAY);
        if (response.getHasWinner()) {
            // TODO: show winner in display
        } else {
            // Assume players can only play at most 1 card per turn.
            // Future updates may allow for multiple cards to be played.
            GameDisplayData data = getGameDisplayData(response);
            display.updateView(data);
        }
    }

    /**
     * Fires when the user requests to draw a card.
     * Passes request to game logic and sends response to display.
     */
    public void drawCard() {
        PlayerGameResponseModel response = bridge.getResponse(selectedOwner, selectedSuit, selectedRank, TurnAction.DRAW); 
        GameDisplayData data = getGameDisplayData(response);
        display.updateView(data);
    }

    /**
     * Fires when the user requests to skip their turn.
     * Passes request to game logic and sends response to display.
     */
    public void skip() {
        PlayerGameResponseModel response = bridge.getResponse(selectedOwner, selectedSuit, selectedRank, TurnAction.SKIP);
        GameDisplayData data = getGameDisplayData(response);
        display.updateView(data);
    }

    private GameDisplayData getGameDisplayData(PlayerGameResponseModel response) {
        String currentPlayer = response.getCurrentPlayerName();
        ArrayList<CardResponseModel> cardResponses = response.getPlayerCards();

        ArrayList<CardDisplayData> cards = new ArrayList<>(cardResponses.size());
        for (CardResponseModel cardResponse : cardResponses) {
            cards.add(new CardDisplayData(cardResponse.getSuit(), cardResponse.getRank()));
        }

        return new GameDisplayData(currentPlayer, cards);
    }
}
