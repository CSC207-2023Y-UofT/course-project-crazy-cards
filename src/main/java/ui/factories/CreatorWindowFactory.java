package ui.factories;

import ui.windows.Window;
import ui.windows.creator.CreatorDisplay;
import ui.windows.creator.CreatorWindow;
import controllers.GameCreationController;

public class CreatorWindowFactory implements WindowFactory {
    private GameCreationController controller;

    public CreatorWindowFactory(GameCreationController controller) {
        this.controller = controller;
    }

    @Override
    public Window createWindow() {
        CreatorDisplay display = new CreatorDisplay(controller);
        return new CreatorWindow(display);
    }
}
