package ui.factories;

import ui.windows.Window;

import ui.windows.rules.RulesDisplay;
import ui.windows.rules.RulesWindow;

public class RuleWindowFactory implements WindowFactory {

    /**
     * Constructor that creates a window for the rules page.
     * @return The RulesWindow that displays the rules page.
     */
    @Override
    public Window createWindow() {
        RulesDisplay display = new RulesDisplay();
        RulesWindow window = new RulesWindow(display);

        return window;
    }
}
