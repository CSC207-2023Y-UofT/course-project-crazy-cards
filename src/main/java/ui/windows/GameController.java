package ui.windows;

import ui.enums.Rank;
import ui.enums.Suit;

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

    public void setSelectedCard(Suit suit, Rank rank) {
        selectedSuit = suit;
        selectedRank = rank;
    }
}
