package ui.components;

import javax.swing.JButton;

public class NavigationButton extends JButton {
    private String identifier;

    /**
     * Constructor for the navigation button that brings the user through menus in the program.
     * @param identifier The name of the menu the user will navigate to, upon clicking the button.
     */
    public NavigationButton(String identifier) {
        super(identifier);

        this.identifier = identifier;
    }

    /**
     * Getter method to return the identifier of a given NavigationButton.
     * @return The identifier associated with the NavigationButton passed as the argument to the function.
     */
    public String getIdentifier() {
        return identifier;
    }
}
