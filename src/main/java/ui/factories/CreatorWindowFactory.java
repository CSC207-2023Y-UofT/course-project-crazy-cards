package ui.factories;

import ui.windows.Window;
import ui.windows.creation.CreationDisplay;
import ui.windows.creation.CreationWindow;
import controllers.GameCreationController;

public class CreatorWindowFactory implements WindowFactory {
    private GameCreationController controller;

    /**
     * Constructor for the factory that sets the factory's controller.
     * @param controller The GameCreationController to be used in creating the window.
     */
    public CreatorWindowFactory(GameCreationController controller) {
        this.controller = controller;
    }

    /**
     * Creates a window that displays a CreationDisplay.
     * @return The new CreationWindow based on a new CreationDisplay, which takes in the controller.
     */
    @Override
    public Window createWindow() {
        CreationDisplay display = new CreationDisplay(controller);
        return new CreationWindow(display);
    }
}
