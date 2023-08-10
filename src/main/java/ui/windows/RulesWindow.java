package ui.windows;

import javax.swing.JPanel;

import enums.WindowName;

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
}
