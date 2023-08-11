package ui.windows;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Delegates incoming user input from a StatsDisplay.
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

    public StatsController getController() {
        return controller;
    }

    /**
     * Handle the user input from a JButton in the StatsDisplay. 
     * Fires when the user clicks the "Search" button.
     * @param e The event to be processed.
     */
    public void actionPerformed(ActionEvent e) {
        JTextField inputNameField = (JTextField)e.getSource();
        String username = inputNameField.getText();
        try {
            controller.tryRequestUser(username);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
