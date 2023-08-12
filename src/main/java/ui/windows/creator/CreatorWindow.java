package ui.windows.creator;

import enums.WindowName;
import ui.windows.layout_managers.PaneDelegator;
import ui.windows.Window;

import javax.swing.*;

/**
 * A window containing the display for game creation and identifier for what window it is.
 */
public class CreatorWindow implements Window {
    private final CreatorDisplay creationDisplay;

    /**
     * Construct a new CreatorWindow given a CreatorDisplay.
     * @param creationDisplay The CreatorDisplay belonging to this CreatorWindow.
     */
    public CreatorWindow(CreatorDisplay creationDisplay) {
        this.creationDisplay = creationDisplay;
    }

    /**
     * Getter method for the identifier of a CreatorWindow.
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
     * Setter method for the PaneDelegator of the display.
     * @param navigator The PaneDelegator for the window's CreatorDisplay.
     */
    @Override
    public void setNavigator(PaneDelegator navigator) {
        creationDisplay.setNavigator(navigator);
    }
}
