package ui.windows;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

public class RulesDisplay extends JPanel {
    private JLabel titleLabel;
    private JLabel bodyLabel;

    public RulesDisplay() {
        initializeGUIComponents();
    }

    private void initializeGUIComponents() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Rules");
        // Wrapping JLabel text with html tags allows for wrapping and resizing with window
        // TODO: Make wrapping labels a helper method
        bodyLabel = new JLabel("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas quis tortor ut est consectetur vehicula. Nam consequat faucibus lacus vitae aliquet. Phasellus purus urna, varius a tempor in, ullamcorper in turpis. Phasellus felis magna, dictum eu consequat et, auctor at mauris. Integer placerat ipsum sed maximus porta. Praesent sit amet ligula laoreet, commodo magna quis, consectetur dui. Maecenas risus leo, dapibus id velit sed, condimentum pulvinar nisl. Mauris quis erat gravida, suscipit odio in, efficitur quam. Quisque vulputate vestibulum hendrerit. Curabitur id mi consectetur, posuere lectus at, scelerisque massa. Quisque sit amet mollis sapien. Duis at facilisis mi. Aenean et justo scelerisque mauris bibendum rutrum.</html>");
        textPanel.add(titleLabel);
        textPanel.add(bodyLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton backButton = new JButton("Back");

        buttonPanel.add(backButton);

        setLayout(new BorderLayout());
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        RulesDisplay display = new RulesDisplay();
        JFrame frame = new JFrame("RulesDisplay");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(display);
        frame.setVisible(true);
    }
}
