package ui.windows.game;

import controllers.GameBridge;
import enums.Rank;
import enums.Suit;
import enums.TurnAction;
import use_cases.CardResponseModel;
import use_cases.PlayerGameResponseModel;

import java.util.ArrayList;

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
    public GameController(GameBridge bridge) { 
        this.bridge = bridge;
        this.display = null;
    }
    public void setBridge(GameBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Sets a display endpoint for the controller.
     * @param display the display to be used
     */
    public void setDisplay(GameDisplay display) {
        this.display = display;
    }

    public void setSelectedOwner(String owner) {
        this.selectedOwner = owner;
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

    // Temporary method
    public void startGame() {
        PlayerGameResponseModel response = bridge.getResponse(null, null, null, TurnAction.START);
        GameDisplayData data = getGameDisplayData(response);
        display.updateView(data);
    }

    /**
     * Fires when the user requests to play their selected card.
     * Passes request to game logic and sends response to display.
     */
    public void playCard() {
        PlayerGameResponseModel response = bridge.getResponse(selectedOwner, selectedSuit, selectedRank, TurnAction.PLAY);
        if (response.getHasWinner()) {
            // TODO: show winner in display
            System.out.println("The Game has been won, please exit the application.");
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

        return new GameDisplayData(currentPlayer, cards, new CardDisplayData(response.getCurrentCard().getSuit(), response.getCurrentCard().getRank()));
    }
}
