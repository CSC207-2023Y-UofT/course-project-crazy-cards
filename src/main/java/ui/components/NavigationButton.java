package ui.components;

import javax.swing.JButton;

public class NavigationButton extends JButton {
    private String identifier;

    public NavigationButton(String identifier) {
        super(identifier);

        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
