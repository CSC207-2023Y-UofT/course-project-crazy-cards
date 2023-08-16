package ui.factories;

import ui.windows.menu.MenuDisplay;
import ui.windows.menu.MenuWindow;
import ui.windows.Window;

public class MenuWindowFactory implements WindowFactory {

    /**
     * Constructor that creates a window for the main menu/title page.
     * @return The MenuWindow that displays the main menu/title page.
     */
    @Override
    public Window createWindow() {
        MenuDisplay display = new MenuDisplay();

        return new MenuWindow(display);
    }
}
