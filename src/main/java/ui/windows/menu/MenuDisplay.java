package ui.windows.menu;

import java.awt.*;

import javax.swing.*;

import enums.WindowName;
import ui.components.NavigationButton;
import ui.windows.layout_managers.PaneDelegator;

public class MenuDisplay extends JPanel {
    private NavigationButton playButton;
    private NavigationButton statsButton;
    private NavigationButton rulesButton;
    private NavigationButton howtoButton;

    /**
     * Constructor to create the necessary GUI components to display the main menu/title screen.
     */
    public MenuDisplay() {
        initializeGUIComponents();
    }

    /**
     * Add ActionListener to each button on the main menu.
     * @param navigator The PaneDelegator to be added to each button.
     */
    public void setNavigator(PaneDelegator navigator) {
        playButton.addActionListener(navigator);
        statsButton.addActionListener(navigator);
        rulesButton.addActionListener(navigator);
        howtoButton.addActionListener(navigator);
    }

    /**
     * Initialize the GUI components required to display the GUI components, including the title, logo, and buttons to
     * navigate between screens/windows.
     */
    private void initializeGUIComponents() {
        JPanel main = new JPanel();
        JPanel buttonPanel = new JPanel();
        JLayeredPane logo = new JLayeredPane();
        JLabel title = new JLabel("Crazy Cards!");
        title.setFont(new Font("serif", Font.PLAIN, 100));
        main.add(title);

        JLabel c1 = new JLabel("C");
        c1.setFont(new Font("serif", Font.PLAIN, 350));
        c1.setForeground(new Color(180, 20, 20));
        c1.setBounds(250, 10, 300, 300);

        JLabel c2 = new JLabel("C");
        c2.setFont(new Font("serif", Font.PLAIN, 350));
        c2.setForeground(Color.BLACK);
        c2.setBounds(280, 30, 300, 300);

        logo.add(c1, new Integer(1));
        logo.add(c2, new Integer(2));

        playButton = new NavigationButton(WindowName.CREATOR, "Play");
        statsButton = new NavigationButton(WindowName.STATS, "Stats");
        rulesButton = new NavigationButton(WindowName.RULES, "Rules");
        howtoButton = new NavigationButton(WindowName.HOWTO, "How to Play");

        buttonPanel.add(playButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(rulesButton);
        buttonPanel.add(howtoButton);

        setLayout(new BorderLayout());
        add(main, BorderLayout.NORTH);
        add(logo, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
