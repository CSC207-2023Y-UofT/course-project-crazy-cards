package ui.windows;

import javax.swing.JPanel;

/**
 * Interface for windows in a CardLayout.
 */
public interface Window {
    String getIdentifier();

    JPanel getPanel();
}