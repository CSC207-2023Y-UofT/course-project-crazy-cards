package controllers.interfaces;

import enums.Rank;
import enums.Suit;
import ui.interfaces.GameUI;

/**
 * Defines a controller that handles the interaction between players and a game.
 */
public interface GameBridge {
    /**
     * Request the start of the game.
     */
    void requestStartData();

    /**
     * Request to play a selected card. Assumes an implementation stores the selected card.
     */
    void requestPlay();

    /**
     * Request to pick up a card. Assumes an implementation stores the selected card.
     */
    void requestDraw();

    /**
     * Request to skip a turn. Assumes an implementation stores the selected card.
     */
    void requestSkip();

    /**
     * Sets the selected card.
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    void setSelectedCard(Suit suit, Rank rank);

    /**
     * Sets the UI.
     * @param ui the UI to be set
     */
    void setUI(GameUI ui);
}
