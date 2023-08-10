package ui.windows;

import javax.swing.JPanel;

import enums.WindowName;

/**
 * Interface for windows in a CardLayout.
 */
public interface Window {
    /**
     * Getter method for the identifier of any given Window.
     * @return The identifier of the Window, as a WindowName enum.
     */
    WindowName getIdentifier();

    /**
     * Getter method for the panel associated with any given Window.
     * @return The JPanel of the window.
     */
    JPanel getPanel();

    void setNavigator(PaneDelegator navigator);
}