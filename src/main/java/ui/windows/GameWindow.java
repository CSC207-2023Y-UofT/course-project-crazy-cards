package ui.windows;

import javax.swing.JPanel;

import enums.WindowName;

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
    
}
