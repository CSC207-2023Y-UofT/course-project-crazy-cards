package ui.windows.howto;

import javax.swing.JPanel;

import enums.WindowName;
import ui.windows.layout_managers.PaneDelegator;
import ui.windows.Window;

public class HowtoWindow implements Window {
    private HowtoDisplay display;

    /**
     * Constructor for the Window, by setting a HowtoDisplay to it.
     * @param display The display to be set for this window.
     */
    public HowtoWindow(HowtoDisplay display) {
        this.display = display;
    }

    /**
     * Getter method for the identifier of the How to Play page.
     * @return The identifier associated with the How to Play window.
     */
    @Override
    public WindowName getIdentifier() {
        return WindowName.HOWTO;
    }

    /**
     * Getter method that returns the display of the page.
     * @return Returns the display of the How to Play page.
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