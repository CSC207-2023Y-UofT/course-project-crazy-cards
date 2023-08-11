package ui.factories;

import controllers.GameCreationController;
import ui.windows.CreationDisplay;
import ui.windows.CreationWindow;
import ui.windows.Window;
import use_cases.GameCreationInteractor;

public class CreationWindowFactory implements WindowFactory {


    /**
     * Method to be used to create a Window class upon being called.
     *
     * @return The window that will be created.
     */
    @Override
    public Window createWindow() {
        GameCreationInteractor interactor = new GameCreationInteractor();
        GameCreationController controller = new GameCreationController(interactor);
        CreationDisplay display = new CreationDisplay(controller);
        CreationWindow window = new CreationWindow(display);
        return window;
    }
}
