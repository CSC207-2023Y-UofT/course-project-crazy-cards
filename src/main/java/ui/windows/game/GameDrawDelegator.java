package ui.windows.game;

import controllers.interfaces.GameBridge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Delegates user interaction for drawing cards.
 */
public class GameDrawDelegator implements ActionListener {
    private final GameBridge bridge;

    /**
     * Construct a GameDrawDelegator with the given bridge.
     * @param bridge the bridge to be used
     */
    public GameDrawDelegator(GameBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Fires when the user requests to draw a card.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        bridge.requestDraw();
    }
}
