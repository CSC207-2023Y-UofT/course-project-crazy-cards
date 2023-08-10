package ui.windows;

import javax.swing.JPanel;

import enums.WindowName;

public class MenuWindow implements Window {
    private MenuDisplay display;

    public MenuWindow(MenuDisplay display) {
        this.display = display;
    }

    @Override
    public WindowName getIdentifier() {
        return WindowName.MENU;
    }

    @Override
    public JPanel getPanel() {
        return display;
    }
    
}
