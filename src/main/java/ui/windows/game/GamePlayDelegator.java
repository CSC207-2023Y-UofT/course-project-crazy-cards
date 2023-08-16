package ui.windows.game;

import controllers.interfaces.GameBridge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Delegates user interaction for playing cards.
 */
public class GamePlayDelegator implements ActionListener {
    private final GameBridge bridge;

    /**
     * Construct a GamePlayDelegator with the given bridge.
     */
    public GamePlayDelegator(GameBridge bridge) {
        this.bridge = bridge;
    }
    
    /**
     * Fires when the user requests to play a card.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        bridge.requestPlay();
    }
}
