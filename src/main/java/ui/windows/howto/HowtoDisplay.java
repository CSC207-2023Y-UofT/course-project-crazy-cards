package ui.windows.howto;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import enums.WindowName;
import ui.components.NavigationButton;
import ui.windows.layout_managers.PaneDelegator;

public class HowtoDisplay extends JPanel {
    private JLabel titleLabel;
    private JLabel bodyLabel;
    private NavigationButton backButton;

    public HowtoDisplay() {
        initializeGUIComponents();
    }

    public void setNavigator(PaneDelegator navigator) {
        backButton.addActionListener(navigator);
    }

    private void initializeGUIComponents() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("<html>How to Play!</html>");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 36));
        bodyLabel = new JLabel("<html><br>Play card:<br>In order to play a card, first click on the card itself. Then, you're able to click the \"Play\" button to play the card.<br><br>"
                +"Draw card:<br>If you don't have any cards in your hand that can be played onto the current card, click the \"Draw\" button to get a new card. If the card you just picked up"
                + "can be played, follow the Play card instructions. Otherwise, you must skip your turn.<br>You can only draw one card per turn.<br><br>"
                +"Skip turn:<br>This button can only be clicked after you draw a card that cannot be played on top of the one currently in play.<br><br>"
                +"Help button (TO BE IMPLEMENTED):<br>If you need to see these instructions again, press the \"Help\" button during gameplay.</html>");
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
