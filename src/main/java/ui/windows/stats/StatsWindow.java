package ui.windows.stats;

import javax.swing.JPanel;

import enums.WindowName;
import ui.windows.layout_managers.PaneDelegator;
import ui.windows.Window;

/**
 * Wrapper class for StatsRenderer used in a CardLayout.
 */
public class StatsWindow implements Window {
    private StatsDisplay renderer;

    /**
     * Constructor for a StatsWindow holding the given StatsRenderer.
     * @param renderer The StatsRenderer to be displayed.
     */
    public StatsWindow(StatsDisplay renderer) {
        this.renderer = renderer;
    }

    /**
     * Returns the identifier of this window.
     * @return The identifier of the window as a WindowName enum.
     */
    @Override
    public WindowName getIdentifier() {
        return WindowName.STATS;
    }

    /**
     * Returns the JPanel of this window.
     * @return The JPanel corresponding to the current window.
     */
    @Override
    public JPanel getPanel() {
        return renderer;
    }

    /**
     * Sets the provided PaneDelegator as the navigator for the renderer.
     * @param navigator The PaneDelegator to be set as the navigator for the renderer.
     */
    @Override
    public void setNavigator(PaneDelegator navigator) {
        renderer.setNavigator(navigator);
    }

}