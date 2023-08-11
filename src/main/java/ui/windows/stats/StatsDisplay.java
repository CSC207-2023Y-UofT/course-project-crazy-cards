package ui.windows.stats;

import enums.WindowName;
import ui.components.NavigationButton;
import ui.windows.layout_managers.PaneDelegator;

import javax.swing.*;

/**
 * A JPanel subclass representing the game's statistics window.
 */
public class StatsDisplay extends JPanel {
    private static final String EMPTY_LABEL = "";

    private StatsDelegator delegator;

    private JLabel nameLabel;
    private JLabel gamesWonLabel;
    private JLabel gamesLostLabel;

    private NavigationButton backButton;

    /**
     * Construct a StatsDisplay displaying default data.
     * @param delegator the StatsDelegator this display links to
     */
    public StatsDisplay(StatsDelegator delegator) {
        this.delegator = delegator;

        initializeGUIComponents();
    }

    public StatsDelegator getDelegator() {
        return delegator;
    }

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
        dataPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        // Default labels
        nameLabel = new JLabel("Enter a username.");
        gamesWonLabel = new JLabel(EMPTY_LABEL);
        gamesLostLabel = new JLabel(EMPTY_LABEL);

        dataPanel.add(nameLabel);
        dataPanel.add(gamesWonLabel);
        dataPanel.add(gamesLostLabel);

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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(dataPanel);
        add(Box.createVerticalGlue());
        add(buttonPanel);
    }

    /**
     * Update this instance's labels to reflect updated values in data.
     */
    public void updateView(StatsDisplayData data) {
        nameLabel.setText(data.getName());
        gamesWonLabel.setText("Games Won: " + data.getGamesWon());
        gamesLostLabel.setText("Games Lost: " + data.getGamesLost());
    }
}
