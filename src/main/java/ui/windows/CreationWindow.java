package ui.windows;

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
     * @return The identifier of the Window, as a String.
     */
    @Override
    public String getIdentifier() {
        return "Creation";
    }

    /**
     * Getter method for the panel associated with any given Window.
     * @return The JPanel of the window.
     */
    @Override
    public JPanel getPanel() {
        return this.creationDisplay;
    }
}
