package ui.windows.game;

import enums.WindowName;
import ui.windows.Window;
import ui.windows.layout_managers.PaneDelegator;

import javax.swing.*;

public class GameWindow implements Window {
    private GameDisplay display;

    public GameWindow(GameDisplay display) {
        this.display = display;
    }

    @Override
    public WindowName getIdentifier() {
        return WindowName.GAME;
    }

    @Override
    public JPanel getPanel() {
        return display;
    }

    @Override
    public void setNavigator(PaneDelegator navigator) {
        // add when game window has navigation, if ever
        // if not needed, extract setNavigator into Navigable interface or smth
        display.setNavigator(navigator);
    }
}
