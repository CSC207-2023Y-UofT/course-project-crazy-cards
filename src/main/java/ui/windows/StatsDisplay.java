package ui.windows;

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
    private JLabel gamesPlayedLabel;
    private JLabel gamesWonLabel;
    private JLabel longestWinStreakLabel;
    private JLabel nextPlayer;

    /**
     * Construct a StatsDisplay displaying default data.
     * @param delegator the StatsDelegator this display links to
     */
    public StatsDisplay(StatsDelegator delegator) {
        this.delegator = delegator;

        initializeGUIComponents();
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
        // dataPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        // Default labels
        nameLabel = new JLabel("Enter a username.");
        nameLabel.setFont(font);
        gamesPlayedLabel = new JLabel(EMPTY_LABEL);
        gamesPlayedLabel.setFont(font);
        gamesWonLabel = new JLabel(EMPTY_LABEL);
        gamesWonLabel.setFont(font);
        longestWinStreakLabel = new JLabel(EMPTY_LABEL);
        longestWinStreakLabel.setFont(font);
        nextPlayer = new JLabel(EMPTY_LABEL);
        nextPlayer.setFont(font);

        dataPanel.add(nameLabel);
        dataPanel.add(gamesPlayedLabel);
        dataPanel.add(gamesWonLabel);
        dataPanel.add(longestWinStreakLabel);
        dataPanel.add(nextPlayer);

        // The button panel containing the back button and search bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton backButton = new JButton("Back");
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
        gamesPlayedLabel.setText("Games Played: " + data.getGamesPlayed());
        gamesWonLabel.setText("Games Won: " + data.getGamesWon());
        longestWinStreakLabel.setText("Longest Win Streak: " + data.getLongestWinStreak());
        nextPlayer.setText("<html><br>Enter a new username to see statistics.</html>");
    }

    /**
     * Tests creation of a Stats window.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Statistics Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        StatsController controller = new StatsController();
        StatsDelegator delegator = new StatsDelegator(controller);
        StatsDisplay display = new StatsDisplay(delegator);
        controller.setDisplay(display);

        display.setOpaque(true);
        frame.setContentPane(display);
        frame.setVisible(true);
    }
}