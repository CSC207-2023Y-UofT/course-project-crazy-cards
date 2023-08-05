package ui.windows;

import javax.swing.*;

import java.awt.CardLayout;

public class CardLayoutManager implements ICardLayoutManager {
    private JFrame frame;

    /**
     * Constructor for the CardLayoutManager, which manages transitions between windows,
     * on which information is displayed on.
     * @param window The JFrame window created in order to display information.
     */
    public CardLayoutManager(JFrame window) {
        this.frame = window;
    }

    /**
     * Gets the internal layout manager before showing the requested pane on the current screen.
     * @param pane The pane passed through as the argument to be set as the current viewing pane.
     */
    @Override
    public void setPane(String pane) {
        CardLayout layout = getLayout();
        layout.show(frame.getContentPane(), pane);
    }

    /**
     * Gets the internal layout manager before adding the given window to it.
     * This will not display the given window, but adds it to the top of the internal layout manager.
     * @param window The window being passed into the layout manager to be displayed.
     */
    @Override
    public void addPane(Window window) {
        CardLayout layout = getLayout();
        layout.addLayoutComponent(window.getPanel(), window.getIdentifier());
    }

    /**
     * Getter method that returns the current layout of the pane/window at the time.
     * @return The current internal layout manager of the window that is on screen.
     */
    private CardLayout getLayout() {
        return (CardLayout)frame.getContentPane().getLayout();
    }
}
