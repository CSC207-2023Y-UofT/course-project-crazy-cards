package ui.factories;

import ui.windows.Window;
import ui.windows.creation.CreationDisplay;
import ui.windows.creation.CreationWindow;
import controllers.GameCreationController;

public class CreatorWindowFactory implements WindowFactory {
    private GameCreationController controller;

    public CreatorWindowFactory(GameCreationController controller) {
        this.controller = controller;
    }

    @Override
    public Window createWindow() {
        CreationDisplay display = new CreationDisplay(controller);
        return new CreationWindow(display);
    }
}
