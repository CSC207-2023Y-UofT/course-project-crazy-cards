package ui.windows.layout_managers;

import enums.WindowName;
import ui.windows.Window;
import ui.windows.layout_managers.ICardLayoutManager;

import javax.swing.*;
import java.awt.*;

public class CardLayoutManager implements ICardLayoutManager {
    private JPanel panel;
    private WindowName currPane;

    /**
     * Constructor for the CardLayoutManager, which manages transitions between windows,
     * on which information is displayed on.
     * @param window The JFrame window created in order to display information.
     */
    public CardLayoutManager(JFrame window) {
        panel = new JPanel(new CardLayout());

        window.add(panel);
    }

    /**
     * Gets the internal layout manager before showing the requested pane on the current screen.
     * @param pane The pane passed through as the argument to be set as the current viewing pane.
     */
    @Override
    public void setPane(WindowName pane) {
        CardLayout layout = getLayout();
        layout.show(panel, pane.toString());
        currPane = pane;
    }
    public WindowName getCurrPane() {
        return currPane;
    }

    /**
     * Gets the internal layout manager before adding the given window to it.
     * This will not display the given window, but adds it to the top of the internal layout manager.
     * @param window The window being passed into the layout manager to be displayed.
     */
    @Override
    public void addPane(Window window) {
        panel.add(window.getPanel(), window.getIdentifier().toString());
    }

    /**
     * Getter method that returns the current layout of the pane/window at the time.
     * @return The current internal layout manager of the window that is on screen.
     */
    private CardLayout getLayout() {
        return (CardLayout)(panel.getLayout());
    }
}