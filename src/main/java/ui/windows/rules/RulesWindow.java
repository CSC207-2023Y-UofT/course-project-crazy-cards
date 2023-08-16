package ui.windows.rules;

import enums.WindowName;
import ui.windows.Window;
import ui.windows.layout_managers.PaneDelegator;

import javax.swing.*;

public class RulesWindow implements Window {
    private final RulesDisplay display;

    /**
     * Constructor for the Window, by setting a RulesDisplay to it.
     * @param display The display to be set for this window.
     */
    public RulesWindow(RulesDisplay display) {
        this.display = display;
    }

    /**
     * Getter method for the identifier of the Rules page.
     * @return The identifier associated with the Rules window.
     */
    @Override
    public WindowName getIdentifier() {
        return WindowName.RULES;
    }

    /**
     * Getter method that returns the display of the page.
     * @return Returns the display of the Rules page.
     */
    @Override
    public JPanel getPanel() {
        return display;
    }

    /**
     * Setter method to add a given PaneDelegator to the window.
     * @param navigator The PaneDelegator that is designated to change between windows.
     */
    @Override
    public void setNavigator(PaneDelegator navigator) {
        display.setNavigator(navigator);
    }
}
