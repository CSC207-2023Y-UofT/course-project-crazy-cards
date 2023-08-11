package ui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Delegates user interaction for drawing cards.
 */
public class GameDrawDelegator implements ActionListener {
    private GameController controller;

    /**
     * Construct a GameDrawDelegator with the given controller.
     * @param controller the controller to be used
     */
    public GameDrawDelegator(GameController controller) {
        this.controller = controller;
    }

    public GameController getController() {
        return controller;
    }

    /**
     * Fires when the user requests to draw a card.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.drawCard();
    }
}
