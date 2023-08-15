package ui.windows.stats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controllers.interfaces.StatsBridge;

/**
 * Delegates incoming user input from a StatsDisplay.
 */
public class StatsDelegator implements ActionListener {
    private StatsBridge bridge;

    /**
     * Constructs a StatsDelegator with a given controller.
     *  @param bridge the controller to be used.
     */
    public StatsDelegator(StatsBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Handle the user input from a JButton in the StatsDisplay. 
     * Fires when the user clicks the "Search" button.
     * @param e The event to be processed.
     */
    public void actionPerformed(ActionEvent e) {
        JTextField inputNameField = (JTextField)e.getSource();
        String username = inputNameField.getText();
        bridge.tryRequestUser(username);
    }
}
