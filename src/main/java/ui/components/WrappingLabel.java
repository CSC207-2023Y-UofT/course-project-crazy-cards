package ui.components;

import javax.swing.JLabel;

public class WrappingLabel extends JLabel {
    public WrappingLabel(String text) {
        super("<html>" + text + "</html>");
    }

    @Override
    public void setText(String text) {
        super.setText("<html>" + text + "</html>");
    }
}
