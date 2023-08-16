package ui.factories;

import ui.windows.Window;

import ui.windows.howto.HowtoWindow;
import ui.windows.howto.HowtoDisplay;

public class HowtoWindowFactory implements WindowFactory {

    /**
     * Calls the necessary constructors to make a window for the How to Play page.
     * @return A Window displaying the How to Play page.
     */
    @Override
    public Window createWindow() {
        HowtoDisplay display = new HowtoDisplay();

        return new HowtoWindow(display);
    }
}