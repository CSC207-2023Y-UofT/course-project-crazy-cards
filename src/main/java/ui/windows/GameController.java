package ui.windows;

import ui.enums.Rank;
import ui.enums.Suit;

/**
 * Handles delegated user interaction for a game window.
 */
public class GameController {
    private GameDisplay display;

    private Suit selectedSuit;
    private Rank selectedRank;

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

    }

    /**
     * Fires when the user requests to draw a card.
     * Passes request to game logic and sends response to display.
     */
    public void drawCard() {

    }

    /**
     * Fires when the user requests to skip their turn.
     * Passes request to game logic and sends response to display.
     */
    public void skip() {

    }
}
