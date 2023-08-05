package ui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePlayDelegator implements ActionListener {
    private GameController controller;

    public GamePlayDelegator(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
