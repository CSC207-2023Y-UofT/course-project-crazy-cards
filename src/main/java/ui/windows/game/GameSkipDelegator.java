package ui.windows.game;

import javax.swing.*;
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

    public GameController getController() {
        return controller;
    }

    /**
     * Fires when the user requests to skip their turn.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel panel = (JPanel) button.getParent().getParent().getParent().getParent();
        GameDisplay display = (GameDisplay) panel;
        String currentPlayer = display.getCurrentPlayer();
        controller.setSelectedOwner(currentPlayer);
        controller.skip();
    }
}
