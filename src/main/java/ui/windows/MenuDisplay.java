package ui.windows;

import java.awt.*;

import javax.swing.*;

import enums.WindowName;
import ui.components.NavigationButton;

public class MenuDisplay extends JPanel {
    private NavigationButton playButton;
    private NavigationButton statsButton;
    private NavigationButton rulesButton;

    public MenuDisplay() {
        initializeGUIComponents();
    }

    public void setNavigator(PaneDelegator navigator) {
        playButton.addActionListener(navigator);
        statsButton.addActionListener(navigator);
        rulesButton.addActionListener(navigator);
    }

    private void initializeGUIComponents() {
        JPanel main = new JPanel();
        JPanel buttonPanel = new JPanel();
        JLabel title = new JLabel("Crazy\nCards!");
        title.setFont(new Font("serif", Font.PLAIN, 100));
        main.add(title);

        playButton = new NavigationButton(WindowName.CREATOR, "Play");
        statsButton = new NavigationButton(WindowName.STATS, "Stats");
        rulesButton = new NavigationButton(WindowName.RULES, "Rules");

        buttonPanel.add(playButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(rulesButton);

        setLayout(new BorderLayout());
        add(main);
        add(buttonPanel);
    }
}
