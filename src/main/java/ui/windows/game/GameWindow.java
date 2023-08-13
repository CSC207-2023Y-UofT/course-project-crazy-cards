package ui.windows.game;

import enums.WindowName;
import ui.windows.SwapEvent;
import ui.windows.SwapListener;
import ui.windows.Window;
import ui.windows.layout_managers.PaneDelegator;
import javax.swing.*;

public class GameWindow implements Window, SwapListener {
    private GameDisplay display;

    /**
     * Constructor for the window that displays the actual game.
     * @param display The GameDisplay the window is connected to.
     */
    public GameWindow(GameDisplay display) {
        this.display = display;
    }

    /**
     * Getter method for the identifier associated with the window.
     * @return The identifier for the window, as an enum.
     */
    @Override
    public WindowName getIdentifier() {
        return WindowName.GAME;
    }

    /**
     * Getter method that returns the display of the page.
     * @return Returns the display of the Game being played on.
     */
    @Override
    public JPanel getPanel() {
        return display;
    }

    @Override
    public void setNavigator(PaneDelegator navigator) {
        display.setNavigator(navigator);
    }

    /**
     * Listens for SwapEvents, and notifies controller.
     * @param event The event containing the window swap information.
     */
    @Override
    public void OnPreSwap(SwapEvent event) {
        display.onSwitch();
    }
}
