package ui.windows;

import enums.WindowName;

import javax.swing.*;

/**
 * A window containing the display for game creation and identifier for what window it is.
 */
public class CreationWindow implements Window{
    private final CreationDisplay creationDisplay;

    /**
     * Construct a new CreationWindow given a CreationDisplay.
     * @param creationDisplay The CreationDisplay belonging to this CreationWindow.
     */
    public CreationWindow(CreationDisplay creationDisplay) {
        this.creationDisplay = creationDisplay;
    }
    /**
     * Getter method for the identifier of a CreationWindow.
     *
     * @return The identifier of the Window, as a String.
     */
    @Override
    public WindowName getIdentifier() {
        return WindowName.CREATOR;
    }

    /**
     * Getter method for the panel associated with any given Window.
     * @return The JPanel of the window.
     */
    @Override
    public JPanel getPanel() {
        return this.creationDisplay;
    }

    /**
     * @param navigator
     */
    @Override
    public void setNavigator(PaneDelegator navigator) {

    }
}
