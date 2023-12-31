package ui.windows.layout_managers;

import ui.components.NavigationButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaneDelegator implements ActionListener {
    private final ICardLayoutManager manager;

    /**
     * Constructor for the PaneDelegator, which listens for an ActionEvent related to NavigationButtons
     * and displays the window associated with the button click.
     * @param manager The CardLayoutManager used to update the display the user sees.
     */
    public PaneDelegator(ICardLayoutManager manager) {
        this.manager = manager;
    }

    /**
     * If the user clicks a NavigationButton, the manager of the PaneDelegator will update the
     * current window the user sees on screen.
     * @param e The event to be processed.
     * @throws IllegalArgumentException If the button clicked was not a NavigationButton.
     */
    @Override
    public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
        Object button = e.getSource();
        if (button instanceof NavigationButton) {
            NavigationButton navButton = (NavigationButton)button;
            manager.setPane(navButton.getIdentifier());
        }
        else {
            throw new IllegalArgumentException("PaneDelegator can only handle NavigationButtons.");
        }
    }
}
