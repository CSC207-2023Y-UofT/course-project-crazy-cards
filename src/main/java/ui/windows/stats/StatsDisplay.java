package ui.windows.stats;

import enums.WindowName;
import ui.components.NavigationButton;
import ui.windows.layout_managers.PaneDelegator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A JPanel subclass representing the game's statistics window.
 */
public class StatsDisplay extends JPanel {
    private static final String EMPTY_LABEL = "";
    private StatsDelegator delegator;
    private JLabel nameLabel;
    private JLabel gamesWonLabel;
    private JLabel gamesLostLabel;
    private JLabel nextPlayer;
    private NavigationButton backButton;

    /**
     * Construct a StatsDisplay displaying default data.
     * @param delegator the StatsDelegator this display links to
     */
    public StatsDisplay(StatsDelegator delegator) {
        this.delegator = delegator;

        initializeGUIComponents();
    }

    /**
     * Sets the action listener for the back button using the provided PaneDelegator.
     * @param delegator The PaneDelegator (button click) to be set as the action listener.
     */
    public void setNavigator(PaneDelegator delegator) {
        backButton.addActionListener(delegator);
    }

    /**
     * Create the GUI components for this instance.
     */
    private void initializeGUIComponents() {
        // The data panel displaying user statistics
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        Font font = new Font("Calibri", Font.BOLD, 20);

        // Default labels
        nameLabel = new JLabel("Enter a username.");
        nameLabel.setFont(font);
        gamesWonLabel = new JLabel(EMPTY_LABEL);
        gamesWonLabel.setFont(font);
        gamesLostLabel = new JLabel(EMPTY_LABEL);
        gamesLostLabel.setFont(font);
        nextPlayer = new JLabel(EMPTY_LABEL);
        nextPlayer.setFont(font);

        dataPanel.add(nameLabel);
        dataPanel.add(gamesWonLabel);
        dataPanel.add(gamesLostLabel);
        dataPanel.add(nextPlayer);

        // The button panel containing the back button and search bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        backButton = new NavigationButton(WindowName.MENU, "Back");
        JTextField inputNameField = new JTextField(20);

        // Link the search bar to the delegator
        inputNameField.addActionListener(delegator);

        // Formatting/spacing TBD
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(inputNameField);

        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    /**
     * Update this instance's labels to reflect updated values in data.
     */
    public void updateView(StatsDisplayData data) {
        nameLabel.setText("User: " + data.getName());
        gamesWonLabel.setText("Games Won: " + data.getGamesWon());
        gamesLostLabel.setText("Games Lost: " + data.getGamesLost());
        nextPlayer.setText("<html><br>Enter a new username to see statistics.</html>");
    }
}
