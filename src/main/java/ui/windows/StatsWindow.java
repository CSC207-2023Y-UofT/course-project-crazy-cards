package ui.windows;

import javax.swing.JPanel;

/**
 * Wrapper class for StatsRenderer used in a CardLayout.
 */
public class StatsWindow implements Window {
    private StatsDisplay renderer;

    /**
     * Construct a StatsWindow holding the given StatsRenderer.
     * @param renderer the StatsRenderer to be displayed
     */
    public StatsWindow(StatsDisplay renderer) {
        this.renderer = renderer;
    }

    /**
     * Returns the identifier of this window.
     * @return the identifier as a String
     */
    @Override
    public String getIdentifier() {
        return "Stats Window";
    }

    /**
     * Returns the JPanel of this window.
     * @return the JPanel
     */
    @Override
    public JPanel getPanel() {
        return renderer;
    }
    
}

