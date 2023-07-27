package ui.windows;

import javax.swing.*;

public class StatsRenderer extends JPanel {
    private static final String EMPTY_LABEL = "";

    private StatsRendererData data;

    private JLabel nameLabel;
    private JLabel gamesPlayedLabel;
    private JLabel gamesWonLabel;
    private JLabel longestWinStreakLabel;

    public StatsRenderer(StatsRendererData data) {
        this.data = data;

        initializeGUIComponents();
    }

    private void initializeGUIComponents() {
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        nameLabel = new JLabel("Enter a username.");
        gamesPlayedLabel = new JLabel(EMPTY_LABEL);
        gamesWonLabel = new JLabel(EMPTY_LABEL);
        longestWinStreakLabel = new JLabel(EMPTY_LABEL);

        dataPanel.add(nameLabel);
        dataPanel.add(gamesPlayedLabel);
        dataPanel.add(gamesWonLabel);
        dataPanel.add(longestWinStreakLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton backButton = new JButton("Back");
        JButton inputNameButton = new JButton("Search");
        JTextField inputNameField = new JTextField(20);
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(inputNameButton);
        buttonPanel.add(inputNameField);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(dataPanel);
        add(Box.createVerticalGlue());
        add(buttonPanel);
    }

    public void updateView() {
        nameLabel.setText(data.getName());
        gamesPlayedLabel.setText("Games Played: " + data.getGamesPlayed());
        gamesWonLabel.setText("Games Won: " + data.getGamesWon());
        longestWinStreakLabel.setText("Longest Win Streak: " + data.getLongestWinStreak());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stats Renderer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        StatsRendererData data = new StatsRendererData("Test", 10, 5, 3);
        StatsRenderer renderer = new StatsRenderer(data);

        renderer.setOpaque(true);
        frame.setContentPane(renderer);
        frame.setVisible(true);

        renderer.updateView();
}
