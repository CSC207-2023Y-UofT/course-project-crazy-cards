package ui.windows.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.interfaces.GameBridge;

/**
 * Delegates user interaction for skipping turns.
 */
public class GameSkipDelegator implements ActionListener {
    private GameBridge bridge;

    /**
     * Construct a GameSkipDelegator with the given bridge.
     * @param bridge the bridge to be used
     */
    public GameSkipDelegator(GameBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Fires when the user requests to skip their turn.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        bridge.requestSkip();
    }
}
