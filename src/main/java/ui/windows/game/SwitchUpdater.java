package ui.windows.game;

/**
 * Updates the game window when switched to.
 */
public class SwitchUpdater {
    private GameController controller;

    /**
     * Construct a new SwitchUpdater given a GameController.
     * @param controller The GameController belonging to this SwitchUpdater.
     */
    public SwitchUpdater(GameController controller) {
        this.controller = controller;
    }

    /**
     * Updates the game window when switched to.
     */
    public void update() {
        controller.requestStart();
    }
}
