package ui.windows;

import javax.swing.JPanel;

public class GameWindow implements Window {
    private GameDisplay display;

    public GameWindow(GameDisplay display) {
        this.display = display;
    }

    @Override
    public String getIdentifier() {
        return "Game";
    }

    @Override
    public JPanel getPanel() {
        return display;
    }
    
}
