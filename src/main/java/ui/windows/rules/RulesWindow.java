package ui.windows.rules;

import javax.swing.JPanel;

import enums.WindowName;
import ui.windows.layout_managers.PaneDelegator;
import ui.windows.Window;

public class RulesWindow implements Window {
    private RulesDisplay display;

    public RulesWindow(RulesDisplay display) {
        this.display = display;
    }

    @Override
    public WindowName getIdentifier() {
        return WindowName.RULES;
    }

    @Override
    public JPanel getPanel() {
        return display;
    }

    @Override
    public void setNavigator(PaneDelegator navigator) {
        display.setNavigator(navigator);
    }
}
