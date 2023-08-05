package ui.factories;

import ui.windows.Window;

public interface WindowFactory {
    /**
     * Method to be used to create a Window class upon being called.
     * @return The window that will be created.
     */
    Window createWindow();
}
