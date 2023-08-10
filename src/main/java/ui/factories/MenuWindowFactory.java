package ui.factories;

import ui.windows.MenuDisplay;
import ui.windows.MenuWindow;
import ui.windows.PaneDelegator;
import ui.windows.Window;

public class MenuWindowFactory implements WindowFactory {
    private PaneDelegator delegator;

    @Override
    public Window createWindow() {
        MenuDisplay display = new MenuDisplay(delegator);
        MenuWindow window = new MenuWindow(display);

        return window;
    }
    
}
