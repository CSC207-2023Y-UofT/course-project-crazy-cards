package ui.windows.howto;

import enums.WindowName;
import ui.components.NavigationButton;
import ui.windows.layout_managers.PaneDelegator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HowtoDisplay extends JPanel {
    private NavigationButton backButton;

    /**
     * Constructor that initializes the display aspects of the how to play window.
     */
    public HowtoDisplay() {
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
     * Initialize the GUI components for the how to play window to be displayed. This includes window formatting and the
     * text for the actual how to play instructions.
     */
    private void initializeGUIComponents() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("<html>How to Play!</html>");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 36));
        JLabel bodyLabel = new JLabel("<html><br>Play card:<br>In order to play a card, first click on the card itself. Then, you're able to click the \"Play\" button to play the card.<br><br>"
                + "Draw card:<br>If you don't have any cards in your hand that can be played onto the current card, click the \"Draw\" button to get a new card. If the card you just picked up"
                + "can be played, follow the Play card instructions. Otherwise, you must skip your turn.<br>You can only draw one card per turn.<br><br>"
                + "Skip turn:<br>This button can only be clicked after you draw a card that cannot be played on top of the one currently in play.<br><br>");
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
