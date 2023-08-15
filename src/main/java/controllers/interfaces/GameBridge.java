package controllers.interfaces;

import enums.Rank;
import enums.Suit;
import ui.interfaces.GameUI;

/**
 * Defines a controller that handles the interaction between players and a game.
 */
public interface GameBridge {
    void requestStartData();

    void requestPlay();

    void requestDraw();

    void requestSkip();

    void setSelectedCard(Suit suit, Rank rank);

    void setUI(GameUI ui);
}
