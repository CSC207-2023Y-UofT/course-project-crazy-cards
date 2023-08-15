package ui.windows.stats;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.data_objects.StatsDisplayData;
import controllers.interfaces.StatsUI;
import enums.WindowName;
import ui.components.NavigationButton;
import ui.components.WrappingLabel;
import ui.windows.layout_managers.PaneDelegator;

/**
 * A JPanel subclass representing the game's statistics window.
 */
public class StatsDisplay extends JPanel implements StatsUI {
    private static final String EMPTY_LABEL = "";
    private StatsDelegator delegator;
    private JLabel nameLabel;
    private JLabel gamesWonLabel;
    private JLabel gamesLostLabel;
    private WrappingLabel nextPlayer;
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
     * Update this instance's labels to reflect updated values in data.
     * @param data the data to update the labels with
     */
    @Override
    public void updateView(StatsDisplayData data) {
        if (isErrorData(data)) {
            nameLabel.setText("Error encountered. Check console for more information.");
            gamesWonLabel.setText(EMPTY_LABEL);
            gamesLostLabel.setText(EMPTY_LABEL);
            nextPlayer.setText(EMPTY_LABEL);
        } 
        else {
            nameLabel.setText("User: " + data.getName());
            gamesWonLabel.setText("Games Won: " + data.getGamesWon());
            gamesLostLabel.setText("Games Lost: " + data.getGamesLost());
            nextPlayer.setText("<br>Enter a new username to see statistics.");
        }
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
        nextPlayer = new WrappingLabel(EMPTY_LABEL);
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
     * Check if the data provided is an error data: negative wins and losses and name "ERROR".
     * @param data the data to check
     * @return whether the data is an error data
     */
    private boolean isErrorData(StatsDisplayData data) {
        return data.getGamesWon() == -1 && data.getGamesLost() == -1 && data.getName().equals("ERROR");
    }
}
