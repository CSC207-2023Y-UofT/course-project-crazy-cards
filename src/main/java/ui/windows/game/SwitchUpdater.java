package ui.windows.game;

public class SwitchUpdater {
    private GameController controller;

    public SwitchUpdater(GameController controller) {
        this.controller = controller;
    }

    public void update() {
        controller.requestStart();
    }
}
