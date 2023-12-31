package ui.windows.rules;

import enums.WindowName;
import ui.components.NavigationButton;
import ui.components.WrappingLabel;
import ui.windows.layout_managers.PaneDelegator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RulesDisplay extends JPanel {
    private NavigationButton backButton;

    /**
     * Constructor that initializes the display aspects of the rules window.
     */
    public RulesDisplay() {
        initializeGUIComponents();
    }

    /**
     * Add an ActionListener to the back button for the user to navigate between windows.
     * @param navigator The PaneDelegator that swaps between windows, upon clicking the back button.
     */
    public void setNavigator(PaneDelegator navigator) {
        backButton.addActionListener(navigator);
    }

    /**
     * Initialize the GUI components for the rules window to be displayed. This includes window formatting and the text
     * for the rules to be displayed.
     */
    private void initializeGUIComponents() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Rules for Crazy Cards!");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 36));
        // Wrapping JLabel text with html tags allows for wrapping and resizing with window
        JLabel bodyLabel = new WrappingLabel("<br>Objective: Players try to be the first to get rid of all of their cards in their hand.<br><br>" +
                "Players can play a given card onto the pile if the card has the same value OR suit as the one on the table.<br><br>" +
                "Crazy cards can be used to change up various aspects of the game. Their functions are listed below (TO BE IMPLEMENTED):<br>" +
                "1. Any '8' card can be used to change the suit of the game, which the next player must play.<br>" +
                "2. Any 'Ace' card can be used to swap hands with the player with the smallest number of cards in their hand.");
        bodyLabel.setFont(new Font("serif", Font.BOLD, 20));
        textPanel.add(titleLabel);
        textPanel.add(bodyLabel);
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        backButton = new NavigationButton(WindowName.MENU, "Back");

        buttonPanel.add(backButton);

        setLayout(new BorderLayout());
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
}
