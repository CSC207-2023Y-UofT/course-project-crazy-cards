package ui.windows;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Delegates incoming user input from a StatsRenderer.
 */
public class StatsDelegator implements ActionListener {
    private StatsController controller;

    /**
     * Construct a StatsDelegator with a given controller.
     *  @param controller the controller to be used
     */
    public StatsDelegator(StatsController controller) {
        this.controller = controller;
    } 

    /**
     * Handle the user input from a JButton in the StatsRenderer. 
     * Fires when the user clicks the "Search" button.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        JTextField inputNameField = (JTextField)e.getSource();
        String username = inputNameField.getText();
        controller.tryRequestUser(username);
    }
}
