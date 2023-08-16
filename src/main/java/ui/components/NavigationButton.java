package ui.components;

import enums.WindowName;

import javax.swing.*;
import java.awt.*;

public class NavigationButton extends JButton {
    private final WindowName identifier;

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
        setPreferredSize(new Dimension(100, 50));
        setFont(new Font("serif", Font.BOLD, 20));
    }

    /**
     * Getter method to return the identifier of a given NavigationButton.
     * @return The identifier associated with the NavigationButton passed as the argument to the function.
     */
    public WindowName getIdentifier() {
        return identifier;
    }
}
