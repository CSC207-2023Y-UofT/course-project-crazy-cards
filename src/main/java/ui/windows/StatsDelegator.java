package ui.windows;

import database.CSVDatabase;
import usecases.PlayerInformation;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Delegates incoming user input from a StatsDisplay.
 */
public class StatsDelegator implements ActionListener {
    private StatsController controller;
    private CSVDatabase database = new CSVDatabase();

    /**
     * Construct a StatsDelegator with a given controller.
     *  @param controller the controller to be used
     */
    public StatsDelegator(StatsController controller) {
        this.controller = controller;
    } 

    /**
     * Handle the user input from a JButton in the StatsDisplay. 
     * Fires when the user clicks the "Search" button.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        JTextField inputNameField = (JTextField)e.getSource();
        String username = inputNameField.getText();
        try {
            PlayerInformation player = database.loadPlayer(username);
            controller.tryRequestUser(player, username);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
