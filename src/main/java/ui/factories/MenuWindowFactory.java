package ui.factories;

import ui.windows.MenuDisplay;
import ui.windows.MenuWindow;
import ui.windows.Window;

public class MenuWindowFactory implements WindowFactory {

    @Override
    public Window createWindow() {
        MenuDisplay display = new MenuDisplay();
        MenuWindow window = new MenuWindow(display);

        return window;
    }
}
