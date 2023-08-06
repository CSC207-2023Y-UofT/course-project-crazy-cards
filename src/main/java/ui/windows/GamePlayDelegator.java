package ui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.components.DrawnCard;

/**
 * Delegates user interaction for playing cards.
 */
public class GamePlayDelegator implements ActionListener {
    private GameController controller;

    /**
     * Construct a GamePlayDelegator with the given controller.
     * @param controller the controller to be used
     */
    public GamePlayDelegator(GameController controller) {
        this.controller = controller;
    }

    /**
     * Fires when the user requests to play a card.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        controller.setSelectedCard(card.getSuit(), card.getRank());
    }
}
