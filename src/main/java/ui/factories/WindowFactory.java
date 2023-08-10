package ui.factories;

import ui.windows.Window;
import use_cases.DataAccess;

public interface WindowFactory {
    /**
     * Method to be used to create a Window class upon being called.
     * @return The window that will be created.
     */
    Window createWindow(DataAccess database);
}
