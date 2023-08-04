package ui.windows;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ui.components.NavigationButton;

public class PaneDelegator implements ActionListener {
    private ICardLayoutManager manager;

    public PaneDelegator(ICardLayoutManager manager) {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        if (button instanceof NavigationButton) {
            NavigationButton navButton = (NavigationButton)button;
            manager.setPane(navButton.getIdentifier());
        }
        else {
            throw new IllegalArgumentException("PaneDelegator can only handle NavigationButtons");
        }
    }
}
