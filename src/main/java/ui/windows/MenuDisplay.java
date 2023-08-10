package ui.windows;

import javax.swing.JButton;
import javax.swing.JPanel;

import enums.WindowName;
import ui.components.NavigationButton;

public class MenuDisplay extends JPanel {
    private PaneDelegator delegator;

    private NavigationButton playButton;
    private NavigationButton statsButton;
    private NavigationButton rulesButton;

    public MenuDisplay(PaneDelegator delegator) {
        this.delegator = delegator;
    }

    public void initializeGUIComponents() {
        JPanel buttonPanel = new JPanel();

        playButton = new NavigationButton(WindowName.CREATOR, "Play");
        statsButton = new NavigationButton(WindowName.STATS, "Stats");
        rulesButton = new NavigationButton(WindowName.RULES, "Rules");

        playButton.addActionListener(delegator);
        statsButton.addActionListener(delegator);
        rulesButton.addActionListener(delegator);

        buttonPanel.add(playButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(rulesButton);

        add(buttonPanel);
    }
}
