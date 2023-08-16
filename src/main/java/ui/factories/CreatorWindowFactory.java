package ui.factories;

import ui.windows.Window;
import ui.windows.creator.CreatorDisplay;
import ui.windows.creator.CreatorWindow;
import controllers.CreatorController;

public class CreatorWindowFactory implements WindowFactory {
    private final CreatorController controller;

    /**
     * Constructor for the factory that sets the factory's controller.
     * @param controller The GameCreationController to be used in creating the window.
     */
    public CreatorWindowFactory(CreatorController controller) {
        this.controller = controller;
    }

    /**
     * Creates a window that displays a CreationDisplay.
     * @return The new CreationWindow based on a new CreationDisplay, which takes in the controller.
     */
    @Override
    public Window createWindow() {
        CreatorDisplay display = new CreatorDisplay(controller);
        return new CreatorWindow(display);
    }
}
