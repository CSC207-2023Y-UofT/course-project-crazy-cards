package ui.windows.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Delegates user interaction for skipping turns.
 */
public class GameSkipDelegator implements ActionListener {
    private GameController controller;

    /**
     * Construct a GameSkipDelegator with the given controller.
     * @param controller the controller to be used
     */
    public GameSkipDelegator(GameController controller) {
        this.controller = controller;
    }

    /**
     * Fires when the user requests to skip their turn.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.skip();
    }
}
