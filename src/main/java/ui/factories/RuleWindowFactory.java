package ui.factories;

import ui.windows.Window;

import ui.windows.RulesDisplay;
import ui.windows.RulesWindow;

public class RuleWindowFactory implements WindowFactory {
    
    @Override
    public Window createWindow() {
        RulesDisplay display = new RulesDisplay();
        RulesWindow window = new RulesWindow(display);

        return window;
    }
}
