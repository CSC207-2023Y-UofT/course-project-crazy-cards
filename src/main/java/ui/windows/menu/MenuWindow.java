package ui.windows.menu;

import javax.swing.JPanel;

import enums.WindowName;
import ui.windows.Window;
import ui.windows.layout_managers.PaneDelegator;

public class MenuWindow implements Window {
    private MenuDisplay display;

    /**
     * Constructor for the Window, by setting a MenuDisplay to it.
     * @param display The display to be set for this window.
     */
    public MenuWindow(MenuDisplay display) {
        this.display = display;
    }

    /**
     * Getter method for the identifier of the main menu/title page.
     * @return The identifier associated with the main menu/title page window, as an enum.
     */
    @Override
    public WindowName getIdentifier() {
        return WindowName.MENU;
    }

    /**
     * Getter method that returns the display of the page.
     * @return Returns the display of the main menu/title page.
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
