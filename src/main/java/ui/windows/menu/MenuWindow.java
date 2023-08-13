package ui.windows.menu;

import javax.swing.JPanel;

import enums.WindowName;
import ui.windows.Window;
import ui.windows.layout_managers.PaneDelegator;

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

    @Override
    public void setNavigator(PaneDelegator navigator) {
        display.setNavigator(navigator);
    }
}
