package ui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDrawDelegator implements ActionListener {
    private GameController controller;

    public GameDrawDelegator(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}
