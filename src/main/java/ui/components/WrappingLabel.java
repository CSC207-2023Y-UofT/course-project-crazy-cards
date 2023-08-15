package ui.components;

import javax.swing.JLabel;

/**
 * Custom JLabel that automatically wraps text.
 * Standard JLabel functionality is to wrap text enclosed in HTML tags.
 * 
 * @see <a href="https://stackoverflow.com/questions/2420742/make-a-jlabel-wrap-its-text-by-setting-a-max-width">Stack Overflow</a>
 */
public class WrappingLabel extends JLabel {
    /**
     * Constructor for the WrappingLabel.
     * Wraps the text in HTML tags.
     * @param text The text to be displayed.
     */
    public WrappingLabel(String text) {
        super("<html>" + text + "</html>");
    }

    /**
     * Sets the text of the label.
     * Wraps the text in HTML tags.
     * @param text The text to be displayed.
     */
    @Override
    public void setText(String text) {
        super.setText("<html>" + text + "</html>");
    }
}
