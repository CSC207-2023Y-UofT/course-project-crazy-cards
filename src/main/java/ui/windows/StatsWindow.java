package ui.windows;

import javax.swing.JPanel;

import enums.WindowName;

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
    
}

