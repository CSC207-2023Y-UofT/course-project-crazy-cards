package ui.windows.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.interfaces.GameBridge;

/**
 * Delegates user interaction for playing cards.
 */
public class GamePlayDelegator implements ActionListener {
    private GameBridge bridge;

    /**
     * Construct a GamePlayDelegator with the given bridge.
     * @param controller the bridge to be used
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
