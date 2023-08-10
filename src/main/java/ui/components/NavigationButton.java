package ui.components;

import javax.swing.JButton;

import enums.WindowName;
import javafx.stage.Window;

public class NavigationButton extends JButton {
    private WindowName identifier;

    /**
     * Constructor for the navigation button that brings the user through menus in the program.
     * @param identifier The name of the menu the user will navigate to, upon clicking the button.
     */
    public NavigationButton(WindowName identifier) {
        super(identifier.toString());

        this.identifier = identifier;
    }

    /**
     * Constructor for the navigation button that brings the user through menus in the program.
     * @param identifier The name of the menu the user will navigate to, upon clicking the button.
     * @param text The text that will be displayed on the button.
     */
    public NavigationButton(WindowName identifier, String text) {
        super(text);

        this.identifier = identifier;
    }

    /**
     * Getter method to return the identifier of a given NavigationButton.
     * @return The identifier associated with the NavigationButton passed as the argument to the function.
     */
    public WindowName getIdentifier() {
        return identifier;
    }
}
