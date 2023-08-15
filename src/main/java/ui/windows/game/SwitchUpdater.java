package ui.windows.game;

import controllers.interfaces.GameBridge;

/**
 * Updates the game window when switched to.
 */
public class SwitchUpdater {
    private GameBridge bridge;

    /**
     * Construct a new SwitchUpdater with the given bridge
     * @param bridge the bridge to be used
     */
    public SwitchUpdater(GameBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Updates the game window when switched to.
     */
    public void update() {
        bridge.requestStartData();
    }
}
